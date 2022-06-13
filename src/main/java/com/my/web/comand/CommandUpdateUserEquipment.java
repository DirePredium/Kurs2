package com.my.web.comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.AuxiliaryTables;
import com.my.db.entity.AuxiliaryTable;
import com.my.db.entity.Equipment;
import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.ExecutionResult;

public class CommandUpdateUserEquipment extends AbstractCommand  implements ICommand {

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("~~~");
		ExecutionResult executionResult = new ExecutionResult();
		System.out.println("id ==> "+ req.getParameter("id"));
		System.out.println("userId ==> "+ req.getParameter("userId"));
		System.out.println("equipmentId ==> "+ req.getParameter("equipmentId"));
		
		AuxiliaryTable auxiliaryTable = new AuxiliaryTable();
		User user = daoFactory.getUserManager().findRecordById(Integer.valueOf(req.getParameter("userId")));
		Equipment equipment = daoFactory.getEquipmentManager().findRecordById(Integer.valueOf(req.getParameter("equipmentId")));
		auxiliaryTable.setId(Integer.valueOf(req.getParameter("id")));
		auxiliaryTable.setT1(user.getId());
		auxiliaryTable.setT2(equipment.getId());
		
		System.out.println("auxiliaryTable ==> "+auxiliaryTable.toString());
		boolean isUpdated = daoFactory.getAuxiliaryTableManager(AuxiliaryTables.USER_EQUIPMENT).updateRecord(auxiliaryTable);
		if(!isUpdated) {
			resp.setContentType("text/plain;charset=UTF-8");
			resp.getWriter().write("Error update.");
		}
		return executionResult;
	}

}
