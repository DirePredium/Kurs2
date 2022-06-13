package com.my.web.comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.ExecutionResult;

public class CommandDeleteUser extends AbstractCommand  implements ICommand {

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("~~~");
		ExecutionResult executionResult = new ExecutionResult();
		System.out.println("id ==> "+ req.getParameter("id"));
		
		User user = new User();
		user.setId(Integer.valueOf(req.getParameter("id")));
		System.out.println("user for delete -- " + user.toString());
		
		boolean isDeleted = daoFactory.getUserManager().deleteRecord(user);
		if(!isDeleted) {
			resp.setContentType("text/plain;charset=UTF-8");
			resp.getWriter().write("Error delete.");
		}
		return executionResult;
	}
}
