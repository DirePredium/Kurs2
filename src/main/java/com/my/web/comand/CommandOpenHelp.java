package com.my.web.comand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.entity.MessageHelp;
import com.my.db.entity.MessageHelpStatus;
import com.my.web.Direction;
import com.my.web.ExecutionResult;

public class CommandOpenHelp implements ICommand {

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ExecutionResult executionResult = new ExecutionResult();
		

		executionResult.setDirection(Direction.FORWARD);
		executionResult.setPage("/META-INF/pages/help.jsp");
		return executionResult;
	}

}
