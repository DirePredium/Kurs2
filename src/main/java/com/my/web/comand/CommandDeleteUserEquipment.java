package com.my.web.comand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.AuxiliaryTables;
import com.my.db.entity.AuxiliaryTable;
import com.my.web.ExecutionResult;

public class CommandDeleteUserEquipment  extends AbstractCommand  implements ICommand{

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println("~~~");
		ExecutionResult executionResult = new ExecutionResult();
		System.out.println("id ==> "+ req.getParameter("id"));
		
		AuxiliaryTable userEquipment = new AuxiliaryTable();
		userEquipment.setId(Integer.valueOf(req.getParameter("id")));
		System.out.println("userEquipment for delete -- " + userEquipment.toString());
		
		boolean isDeleted = daoFactory.getAuxiliaryTableManager(AuxiliaryTables.USER_EQUIPMENT).deleteRecord(userEquipment);
		if(!isDeleted) {
			resp.setContentType("text/plain;charset=UTF-8");
			resp.getWriter().write("Error delete.");
		}
		return executionResult;
	}

}