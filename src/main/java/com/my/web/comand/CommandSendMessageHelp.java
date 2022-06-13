package com.my.web.comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.entity.MessageHelp;
import com.my.db.entity.MessageHelpStatus;
import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.Direction;
import com.my.web.ExecutionResult;

public class CommandSendMessageHelp extends AbstractCommand implements ICommand {

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ExecutionResult executionResult = new ExecutionResult();
		req.setCharacterEncoding("UTF-8");
		System.out.println("request pr ==> "+req.getCharacterEncoding());
		
		System.out.println("email ==> "+ req.getParameter("email"));
		System.out.println("status ==> "+ req.getParameter("status"));
		System.out.println("message ==> "+ req.getParameter("message"));
		
		MessageHelp messageHelp = new MessageHelp();
		messageHelp.setEmail(req.getParameter("email"));
		messageHelp.setStatus(MessageHelpStatus.NEW);
		messageHelp.setMessage(req.getParameter("message"));
		
		
		System.out.println("messageHelp for insert -- " + messageHelp.toString());
		
		boolean isSend = daoFactory.getMessageHelpManager().addRecord(messageHelp);
		if(!isSend) {
			resp.setContentType("text/plain;charset=UTF-8");
			resp.getWriter().write("Error insert.");
		}
		
		executionResult.setDirection(Direction.FORWARD);
		executionResult.setPage("/META-INF/pages/help.jsp");
		return executionResult;
	}

}
