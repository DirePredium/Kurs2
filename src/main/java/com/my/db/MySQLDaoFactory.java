package com.my.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.my.db.Abstract.IAuxiliaryTableDao;
import com.my.db.Abstract.IEntityDao;
import com.my.db.Abstract.IUserDao;
import com.my.db.entity.AuxiliaryTable;
import com.my.db.entity.Equipment;
import com.my.db.entity.MessageHelp;
import com.my.db.entity.Tariff;
import com.my.db.entity.User;
import com.my.db.logic.AuxiliaryTableManager;
import com.my.db.logic.EquipmentManager;
import com.my.db.logic.MessageHelpManager;
import com.my.db.logic.TariffManager;
import com.my.db.logic.UserManager;

public class MySQLDaoFactory extends DaoFactory {

   

	private static DataSource dataSource;
    
    private Connection connection;

    MySQLDaoFactory() {
    	try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
		} catch (NamingException e) {
			throw new IllegalStateException("Cannot obtain a data source object!", e);
		}
    }

    public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
    
    @Override
    public void open() throws SQLException{
        connection = getConnection();
    }
   
    @Override
    public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// write to log
				// log.error("Cannot close a connection", e);
				e.printStackTrace();
			}
		}
	}
    
   

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// write to log
				// log.error("Cannot close a connection", e);
				e.printStackTrace();
			}
		}
	}

    @Override
    public IEntityDao<User> getUserManager() {
        return new UserManager(connection);
    }
    
    @Override
   	public IUserDao getUserSManager() {
   		return new UserManager(connection);
   	}
    
    @Override
   	public IEntityDao<MessageHelp> getMessageHelpManager() {
    	return new MessageHelpManager(connection);
   	}
    
    @Override
   	public IEntityDao<Tariff> getTarifManager() {
    	return new TariffManager(connection);
   	}
    
    @Override
    public IEntityDao<Equipment> getEquipmentManager() {
        return new EquipmentManager(connection);
    }
    
    @Override
    public IEntityDao<AuxiliaryTable> getAuxiliaryTableManager(AuxiliaryTables auxiliaryTables) {
        return new AuxiliaryTableManager(connection,auxiliaryTables );
    }
    
    @Override
    public IAuxiliaryTableDao getAuxiliaryTableSManager(AuxiliaryTables auxiliaryTables) {
        return new AuxiliaryTableManager(connection,auxiliaryTables );
    }
}
