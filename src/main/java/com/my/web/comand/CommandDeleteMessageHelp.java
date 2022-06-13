package com.my.web.comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.entity.MessageHelp;
import com.my.db.entity.User;
import com.my.web.ExecutionResult;

public class CommandDeleteMessageHelp extends AbstractCommand  implements ICommand{

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("~~~");
		ExecutionResult executionResult = new ExecutionResult();
		System.out.println("id ==> "+ req.getParameter("id"));
		
		MessageHelp messageHelp = new MessageHelp();
		messageHelp.setId(Integer.valueOf(req.getParameter("id")));
		System.out.println("messageHelp for delete -- " + messageHelp.toString());
		
		boolean isDeleted = daoFactory.getMessageHelpManager().deleteRecord(messageHelp);
		if(!isDeleted) {
			resp.setContentType("text/plain;charset=UTF-8");
			resp.getWriter().write("Error delete.");
		}
		return executionResult;
	}

}
