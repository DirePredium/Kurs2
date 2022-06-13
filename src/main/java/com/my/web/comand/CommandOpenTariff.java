package com.my.web.comand;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.entity.Tariff;
import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.Direction;
import com.my.web.ExecutionResult;

public class CommandOpenTariff extends AbstractCommand implements ICommand{

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ExecutionResult executionResult = new ExecutionResult();
		executionResult.setDirection(Direction.FORWARD);
		
		List<Tariff> tariffs = daoFactory.getTarifManager().findAllRecords();
		System.out.println("tariff ==> "+ tariffs);
		
		req.setAttribute("tariffs", tariffs);
		System.out.println("tariffs ==> " + req.getAttribute("tariffs"));
		
		executionResult.setPage("/META-INF/pages/tariff.jsp");
		return executionResult;
	}

}
