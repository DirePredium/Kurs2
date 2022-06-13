package com.my.web.comand;

import java.sql.SQLException;

import com.my.db.DaoFactory;
import com.my.db.DataBaseNotSupportedException;
import com.my.db.DataBaseSelector;

public class AbstractCommand {
	protected static DaoFactory daoFactory;
	protected static String adminRole = "ADMIN";
	protected static String[] managerRoles = {"ADMIN","MANAGER"};
	protected static String[] executorRoles = {"ADMIN","MANAGER", "USER"};
	protected static String[] clientRoleStrings = {"ADMIN","MANAGER", "USER", "CLIENT_VIP"};
	
	static {
		setDaoFactory();
	}
	
	private static void setDaoFactory() {
		DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getDaoFactory(DataBaseSelector.MY_SQL);
			daoFactory.open();
		} catch (DataBaseNotSupportedException e) {
			e.printStackTrace();
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		AbstractCommand.daoFactory = daoFactory;
	}
}
