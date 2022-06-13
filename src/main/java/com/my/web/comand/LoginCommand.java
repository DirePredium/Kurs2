package com.my.web.comand;

import javax.servlet.http.*;

import com.my.db.DaoFactory;
import com.my.db.entity.User;
import com.my.web.Direction;
import com.my.web.ExecutionResult;

public class LoginCommand extends AbstractCommand implements ICommand {
	
	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ExecutionResult executionResult = new ExecutionResult();
		executionResult.setDirection(Direction.REDIRECT);
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
			executionResult.setPage("/Kurs2/");
			return executionResult;
		}
		req.getSession().setAttribute("errorMessage", "Illegal login or password!");
		executionResult.setPage("login.jsp");
		return executionResult;
	}

}
