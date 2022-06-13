package com.my.web;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.web.*;
import com.my.web.comand.AbstractCommand;
import com.my.web.comand.CommandContainer;
import com.my.web.comand.ICommand;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static String errorPage = "error.jsp";
	// http://localhost:8080/Kurs2/controller?command=
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			handleRequest(req, resp);
		} catch (Exception e) {
			System.out.println("Get exeption controller!");
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
			System.out.println("Post exeption controller!");
			e.printStackTrace();
			req.getSession().setAttribute("errorMessage", e.getMessage());
			resp.sendRedirect(errorPage);
		}
		
	}
	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		System.out.println("~~~");
		System.out.println("command ==> " + req.getParameter("command"));
		String commandName = req.getParameter("command");
		ICommand command = CommandContainer.getCommand(commandName);
		ExecutionResult result = command.execute(req, resp);
        System.out.println("ExecutionResult is ok, maybe");
        System.out.println("updateRequest");
        if (result.getDirection() == Direction.FORWARD) {
        	System.out.println("controller forward to -> " + result.getPage());
            req.getRequestDispatcher(result.getPage()).forward(req, resp);
            return;
        }
        if (result.getDirection() == Direction.REDIRECT) {
        	System.out.println("controller REDIRECT -> " + result.getPage());
            resp.sendRedirect(result.getPage());
            return;
        }
        System.out.print("controller -> handleRequest is ok");
    }
}