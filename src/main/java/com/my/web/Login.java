package com.my.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.DaoFactory;
import com.my.db.DataBaseNotSupportedException;
import com.my.db.DataBaseSelector;
import com.my.db.entity.User;
import com.my.web.comand.AbstractCommand;

@WebServlet("/login")
public class Login extends HttpServlet{
	private static String errorPage = "error.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			handleRequest(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("errorMessage", e.getMessage());
			resp.sendRedirect(errorPage);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			handleRequest(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("errorMessage", e.getMessage());
			resp.sendRedirect(errorPage);
		}
	}
	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		DaoFactory daoFactory = getDaoFactory();
		String email = req.getParameter("email");
		System.out.println("email ==> " + email);

		String password = req.getParameter("password");
		System.out.println("password ==> " + password);
		
		
		User user = daoFactory.getUserSManager().findUserByEmail(email);
		System.out.println("user ==> " + user);
		
		if (user != null && user.getPassword().equals(password)) {
			System.out.println("set current user " + user);
			req.getSession().setAttribute("currentUser", user);
			System.out.println(req.getSession().getAttribute("currentUser"));
			resp.sendRedirect("/Kurs2/");
			return;
		}
		req.getSession().setAttribute("errorMessage", "Illegal login or password!");
		resp.sendRedirect("login.jsp");
	}
	
	private DaoFactory getDaoFactory() {
		DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getDaoFactory(DataBaseSelector.MY_SQL);
			daoFactory.open();
		} catch (DataBaseNotSupportedException e) {
			e.printStackTrace();
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return daoFactory;
	}
}
