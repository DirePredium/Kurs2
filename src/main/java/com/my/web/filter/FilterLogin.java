package com.my.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.db.entity.User;
import com.my.db.entity.UserRole;

@WebFilter
public class FilterLogin implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
	    HttpServletResponse httpResp = (HttpServletResponse) response;
	    HttpSession session = httpReq.getSession();
	    
	    User user = (User) session.getAttribute("currentUser");

	    if (user == null || user.getEmail() == null) {
	        httpResp.sendRedirect("login.jsp");
	        return;
	    }
	    chain.doFilter(request, response);
	}
}