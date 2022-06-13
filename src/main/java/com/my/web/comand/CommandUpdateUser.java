package com.my.web.comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.ExecutionResult;

public class CommandUpdateUser extends AbstractCommand  implements ICommand {

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("~~~");
		ExecutionResult executionResult = new ExecutionResult();
		System.out.println("id ==> "+ req.getParameter("id"));
		System.out.println("email ==> "+ req.getParameter("email"));
		System.out.println("role ==> "+ req.getParameter("role"));
		System.out.println("password ==> "+ req.getParameter("password"));
		
		User user = new User();
		user.setId(Integer.valueOf(req.getParameter("id")));
		user.setEmail(req.getParameter("email"));
		user.setRole(UserRole.valueOf(req.getParameter("role")));
		user.setPassword(req.getParameter("password"));
		
		System.out.println("user ==> "+user.toString());
		boolean isUpdated = daoFactory.getUserManager().updateRecord(user);
		if(!isUpdated) {
			resp.setContentType("text/plain;charset=UTF-8");
			resp.getWriter().write("Error update.");
		}
		return executionResult;
	}

}
