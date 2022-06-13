package com.my.web.comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.AuxiliaryTables;
import com.my.db.entity.AuxiliaryTable;
import com.my.db.entity.Tariff;
import com.my.db.entity.User;
import com.my.web.Direction;
import com.my.web.ExecutionResult;

public class CommandUpdateUserTariff extends AbstractCommand implements ICommand {

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("~~~");
		ExecutionResult executionResult = new ExecutionResult();
		executionResult.setDirection(Direction.FORWARD);
		//executionResult.setPage("/META-INF/pages/tariff.jsp");
		executionResult.setPage("controller?command=openTariff");
		
		System.out.println("email ==> "+ req.getParameter("email"));
		System.out.println("id_tariff ==> "+ req.getParameter("id_tariff"));
		
		User user = daoFactory.getUserSManager().findUserByEmail(req.getParameter("email"));
		System.out.println("user ==> "+user.toString());
		if(user == null || user.getEmail() == null) {
			req.setAttribute("errorTariffUpdate", "Почта не найдена");
			System.out.println(req.getAttribute("errorTariffUpdate"));
			return executionResult;
		}
		AuxiliaryTable userTarif = daoFactory.getAuxiliaryTableSManager(AuxiliaryTables.USER_TARIFF).findUserByField1(user.getId());
		System.out.println("isUserTarif"+(userTarif != null)+userTarif.toString());
		Tariff tariff = daoFactory.getTarifManager().findRecordById(Integer.valueOf(req.getParameter("id_tariff")));
		System.out.println("tariff ==> "+tariff.toString());
		if(userTarif == null || userTarif.getId() == 0) {
			userTarif = new AuxiliaryTable();
			userTarif.setT1(user.getId());
			userTarif.setT2(tariff.getId());
			System.out.println("userTariff is null ==> "+ userTarif.toString());
			daoFactory.getAuxiliaryTableManager(AuxiliaryTables.USER_TARIFF).addRecord(userTarif);
			return executionResult;
		};
		userTarif.setT1(user.getId());
		userTarif.setT2(tariff.getId());
		daoFactory.getAuxiliaryTableManager(AuxiliaryTables.USER_TARIFF).updateRecord(userTarif);
		
		return executionResult;
	}
}
