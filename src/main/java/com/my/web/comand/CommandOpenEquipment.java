package com.my.web.comand;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.db.AuxiliaryTables;
import com.my.db.entity.AuxiliaryTable;
import com.my.db.entity.Equipment;
import com.my.db.entity.User;
import com.my.web.Direction;
import com.my.web.ExecutionResult;

public class CommandOpenEquipment extends AbstractCommand implements ICommand{

	@Override
	public ExecutionResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ExecutionResult executionResult = new ExecutionResult();
		
		String currentUserRole = ((User)req.getSession().getAttribute("currentUser")).getRole().toString();
		boolean isAuth = Arrays.stream(managerRoles).anyMatch(currentUserRole::equals);
		if(!isAuth) {
			System.out.println("Not contains user role");
			executionResult.setDirection(Direction.REDIRECT);
			executionResult.setPage("login.jsp");
			return executionResult;
		}
		
		executionResult.setDirection(Direction.FORWARD);
		List<AuxiliaryTable> equipments = daoFactory.getAuxiliaryTableManager(AuxiliaryTables.USER_EQUIPMENT).findAllRecords();
		
		//Map<User, Equipment> userEquipmentTemp = new HashMap<>();
		Map<Integer, Entry<User, Equipment>> userEquipment = new HashMap<>();
		
		for (AuxiliaryTable auxiliaryTable : equipments) {
			User user = daoFactory.getUserManager().findRecordById(auxiliaryTable.t1);
			Equipment equipment = daoFactory.getEquipmentManager().findRecordById(auxiliaryTable.t2);
			//userEquipmentTemp.put(user, equipment);
			Map.Entry<User, Equipment> entry = new AbstractMap.SimpleEntry<User, Equipment>(user, equipment);
			userEquipment.put(auxiliaryTable.getId(), entry);
		}
		
		
		req.setAttribute("userEquipment", userEquipment);
		System.out.println("userEquipment ==> " + req.getAttribute("userEquipment").getClass());
		
		req.setAttribute("users", daoFactory.getUserManager().findAllRecords());
		req.setAttribute("equipments", daoFactory.getEquipmentManager().findAllRecords());
		executionResult.setPage("/META-INF/pages/equipment.jsp");
		return executionResult;
	}

}
