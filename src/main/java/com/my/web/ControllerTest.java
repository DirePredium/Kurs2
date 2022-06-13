package com.my.web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.*;
import com.my.web.comand.CommandContainer;
import com.my.web.comand.ICommand;

@WebServlet("/controllerTest")
public class ControllerTest extends HttpServlet {
	
	// http://localhost:8080/Test/controller?command=login
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// address to go to
		String address = "error.jsp";

		// (1) obtain a command name
		String commandName = req.getParameter("command");
		
		// (2) obtain a command
		ICommand command = CommandContainer.getCommand(commandName);
		
		// (3) do action
		try {
			address = command.execute(req, resp).getPage();
		} catch (Exception ex) {
			req.setAttribute("errorMessage", ex.getMessage());
		}
		
		// (4) go to a view layer
		req.getRequestDispatcher(address).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String address = "error.jsp";
		String commandName = req.getParameter("command");
		System.out.println("commandName ==> " + commandName);
		
		ICommand command = CommandContainer.getCommand(commandName);
		System.out.println("command ==> " + command);

		try {
			address = command.execute(req, resp).getPage();
		} catch (Exception ex) {
			ex.printStackTrace();
			req.getSession().setAttribute("errorMessage", ex.getMessage());
		}
		resp.sendRedirect(address);
	}
	
	private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String address = "error.jsp";
		String commandName = req.getParameter("command");
		ICommand command = CommandContainer.getCommand(commandName);
        ExecutionResult result = command.execute(req, resp);
        if (result.isInvalidated())
            req.getSession(false).invalidate();
        result.updateRequest(req);
        if (result.getDirection() == Direction.FORWARD)
            req.getRequestDispatcher(result.getPage()).forward(req, resp);
        if (result.getDirection() == Direction.REDIRECT) {
            resp.sendRedirect(result.getPage());
        }
    }
}