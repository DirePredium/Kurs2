package com.my.web.comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.Direction;
import com.my.web.ExecutionResult;

public class CommandLogout implements ICommand {
	
    @Override
    public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        ExecutionResult result = new ExecutionResult();
        result.setDirection(Direction.REDIRECT);
        try {
            req.getSession().invalidate();
            result.setPage("/Kurs2/");
        }
        catch (Exception e) {
        	e.printStackTrace();
            result.setPage("error.jsp");
        }
        return result;
    }
}