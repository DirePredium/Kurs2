package com.my.web.comand;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.*;

import com.my.db.entity.MessageHelp;
import com.my.db.entity.MessageHelpStatus;
import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.Direction;
import com.my.web.ExecutionResult;

public class CommandOpenMessageHelp extends AbstractCommand implements ICommand {
	
	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ExecutionResult executionResult = new ExecutionResult();
		String currentUserRole = ((User)req.getSession().getAttribute("currentUser")).getRole().toString();
		boolean isAuth = Arrays.stream(executorRoles).anyMatch(currentUserRole::equals);
		if(!isAuth) {
			System.out.println("Not contains user role");
			executionResult.setDirection(Direction.REDIRECT);
			executionResult.setPage("login.jsp");
			return executionResult;
		}
		executionResult.setDirection(Direction.FORWARD);
		
		List<MessageHelp> messagesHelp = daoFactory.getMessageHelpManager().findAllRecords();
		System.out.println("messagesHelp ==> " + messagesHelp.isEmpty());
		
		req.getSession().setAttribute("messagesHelp", messagesHelp);
		System.out.println("messagesHelp ==> " + req.getSession().getAttribute("messagesHelp"));
		req.setAttribute("messageHelpStatus", MessageHelpStatus.values());
		
		executionResult.setPage("/META-INF/pages/MessageHelp.jsp");
		return executionResult;
	}

}

