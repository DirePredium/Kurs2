package com.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.Direction;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Direction typeRequ = Direction.REDIRECT;
        
        int intforrequest = 310;
        User user1 = new User();
        user1.setEmail("asd");
        user1.setId(40);
        user1.setPassword("888");
        user1.setRole(UserRole.CLIENT);
        req.setAttribute("testUser", user1);
        
        resp.sendRedirect("pages/MessageHelp.jsp");
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html");
//        PrintWriter writer = response.getWriter();
//        try {
//            writer.println("<h2>Welcome to servlets</h2>");
//        } finally {
//            writer.close();  
//        }
        Direction typeRequ = Direction.REDIRECT;
        
        int intforrequest = 310;
        User user1 = new User();
        user1.setEmail("asd");
        user1.setId(40);
        user1.setPassword("888");
        user1.setRole(UserRole.CLIENT);
        request.setAttribute("testUser", user1);
        
        if (typeRequ == Direction.FORWARD)
        	request.getRequestDispatcher("pages/MessageHelp.jsp").forward(request, response);
        if (typeRequ == Direction.REDIRECT) {
        	response.sendRedirect("pages/MessageHelp.jsp");
        }
	}

}
