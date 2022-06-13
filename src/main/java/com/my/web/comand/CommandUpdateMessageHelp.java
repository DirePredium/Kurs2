package com.my.web.comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.entity.MessageHelp;
import com.my.db.entity.MessageHelpStatus;
import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.ExecutionResult;

public class CommandUpdateMessageHelp extends AbstractCommand  implements ICommand {

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("~~~");
		ExecutionResult executionResult = new ExecutionResult();
		System.out.println("id ==> "+ req.getParameter("id"));
		System.out.println("email ==> "+ req.getParameter("email"));
		System.out.println("status ==> "+ req.getParameter("status"));
		System.out.println("message ==> "+ req.getParameter("message"));
		
		MessageHelp messageHelp = new MessageHelp();
		messageHelp.setId(Integer.valueOf(req.getParameter("id")));
		messageHelp.setEmail(req.getParameter("email"));
		messageHelp.setStatus(MessageHelpStatus.valueOf(req.getParameter("status")));
		messageHelp.setMessage(req.getParameter("message"));
		
		System.out.println("messageHelp ==> "+messageHelp.toString());
		boolean isUpdated = daoFactory.getMessageHelpManager().updateRecord(messageHelp);
		if(!isUpdated) {
			resp.setContentType("text/plain;charset=UTF-8");
			resp.getWriter().write("Error update.");
		}
		return executionResult;
	}


}
