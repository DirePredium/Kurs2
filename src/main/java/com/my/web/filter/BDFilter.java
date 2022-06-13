package com.my.web.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.db.DaoFactory;
import com.my.db.DataBaseNotSupportedException;
import com.my.db.DataBaseSelector;
import com.my.db.MySQLDaoFactory;
import com.my.db.entity.User;

@WebFilter
public class BDFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
	    HttpServletResponse httpResp = (HttpServletResponse) response;
		
		MySQLDaoFactory mySQLDaoFactory = getMySQLDaoFactory();
		try {
			mySQLDaoFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			httpReq.setAttribute("errorMessage", "Отсутствует подключение к базе даных");
			httpReq.getRequestDispatcher("error.jsp").forward(httpReq, httpResp);
			return;
		} finally {
			mySQLDaoFactory.close();
		}
		chain.doFilter(request, response);
	}
	
	private MySQLDaoFactory getMySQLDaoFactory() {
		DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getDaoFactory(DataBaseSelector.MY_SQL);
			daoFactory.open();
		} catch (DataBaseNotSupportedException e) {
			e.printStackTrace();
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return (MySQLDaoFactory) daoFactory;
	}
}
