package com.my.web.comand;

import javax.servlet.http.*;

import com.my.web.ExecutionResult;

public interface ICommand {

	ExecutionResult execute(HttpServletRequest req,
			HttpServletResponse resp) throws Exception;

}
