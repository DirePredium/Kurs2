package com.my.web.comand;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.AuxiliaryTables;
import com.my.db.entity.AuxiliaryTable;
import com.my.db.entity.User;
import com.my.db.entity.UserRole;
import com.my.web.ExecutionResult;

public class CommandInsertUserEquipment  extends AbstractCommand  implements ICommand{

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("~~~");
		ExecutionResult executionResult = new ExecutionResult();
		System.out.println("userId ==> "+ req.getParameter("userId"));
		System.out.println("equipmentId ==> "+ req.getParameter("equipmentId"));
		
		AuxiliaryTable auxiliaryTable = new AuxiliaryTable();
		auxiliaryTable.setT1(Integer.valueOf(req.getParameter("userId")));
		auxiliaryTable.setT2(Integer.valueOf(req.getParameter("equipmentId")));
		
		System.out.println("auxiliaryTable for insert -- " + auxiliaryTable.toString());
		
		//AuxiliaryTable auxiliaryInSqlTable = daoFactory.getAuxiliaryTableSManager(AuxiliaryTables.USER_EQUIPMENT).findUserByField1(auxiliaryTable.getT1());
		boolean isUpdated = daoFactory.getAuxiliaryTableManager(AuxiliaryTables.USER_EQUIPMENT).addRecord(auxiliaryTable);
		
		//boolean isUpdated = false;
//		if(auxiliaryInSqlTable != null || auxiliaryTable.getT2() == auxiliaryInSqlTable.getT2()) {
//			isUpdated = daoFactory.getAuxiliaryTableManager(AuxiliaryTables.USER_EQUIPMENT).addRecord(auxiliaryTable);
//		}
		if(!isUpdated) {
			resp.setContentType("text/plain;charset=UTF-8");
			resp.getWriter().write("Error insert.");
		}
		return executionResult;
	}

}