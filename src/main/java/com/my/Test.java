package com.my;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.my.db.AuxiliaryTables;
import com.my.db.DBException;
import com.my.db.DaoFactory;
import com.my.db.DataBaseNotSupportedException;
import com.my.db.DataBaseSelector;
import com.my.db.entity.AuxiliaryTable;
import com.my.db.entity.Equipment;
import com.my.db.entity.MessageHelp;
import com.my.db.entity.Tariff;
import com.my.db.entity.User;
import com.my.db.entity.UserRole;

@WebServlet("/test")
public class Test extends HttpServlet {
	//static Logger logger = Logger.getLogger();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		DaoFactory daoFactory = getDaoFactory();
		AuxiliaryTable userTarif;
		try {
			userTarif = daoFactory.getAuxiliaryTableSManager(AuxiliaryTables.USER_TARIFF).findUserByField1(2);
			
			//daoFactory.getEquipmentManager().addRecord(new Equipment());
		} catch (SQLException ex) {
			out.println(ex.getMessage());
			ex.printStackTrace();
			return;
		}
		//messageHelps.forEach(message -> out.append(message.toString()).append(System.lineSeparator()));
		//out.append(userTarif.toString() + " -- userTariff");
	}
	
	private DaoFactory getDaoFactory() {
		DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getDaoFactory(DataBaseSelector.MY_SQL);
			daoFactory.open();
		} catch (DataBaseNotSupportedException e) {
			e.printStackTrace();
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return daoFactory;
	}
//	List<User> messageHelps = null;
//	PrintWriter out = resp.getWriter();
//	DaoFactory daoFactory = getDaoFactory();
//	String searchStr = "t@msail.com";
//	User testUser = null;
//	try {
//		testUser = daoFactory.getUserSManager().findUserByEmail(searchStr);
//		
//		testUser.setEmail("updateTest@asd");
//		daoFactory.getUserManager().updateRecord(testUser);
//		
//		messageHelps = daoFactory.getUserManager().findAllRecords();
//		//daoFactory.getEquipmentManager().addRecord(new Equipment());
//	} catch (SQLException ex) {
//		out.println(ex.getMessage());
//		ex.printStackTrace();
//		return;
//	}
//	messageHelps.forEach(message -> out.append(message.toString()).append(System.lineSeparator()));
//	out.append(testUser.toString() + " -- "+searchStr);
}