package com.my.web.comand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.entity.MessageHelp;
import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.Direction;
import com.my.web.ExecutionResult;

public class CommandOpenUserPage extends AbstractCommand implements ICommand {
	
	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ExecutionResult executionResult = new ExecutionResult();
		String currentUserRole = ((User)req.getSession().getAttribute("currentUser")).getRole().toString();
		if(!adminRole.contains(currentUserRole)) {
			System.out.println("Not contains user role");
			executionResult.setDirection(Direction.REDIRECT);
			executionResult.setPage("login.jsp");
			return executionResult;
		}
		executionResult.setDirection(Direction.FORWARD);
		
		List<User> users = daoFactory.getUserManager().findAllRecords();
		System.out.println("users ==> "+ users);
		
		
		req.setAttribute("users", users);
		System.out.println("users ==> " + req.getAttribute("users"));
		req.setAttribute("userRoles", UserRole.values());
		
		executionResult.setPage("/META-INF/pages/users.jsp");
		return executionResult;
	}
}
