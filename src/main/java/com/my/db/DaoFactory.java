package com.my.db;

import java.sql.SQLException;

import com.my.db.Abstract.IAuxiliaryTableDao;
import com.my.db.Abstract.IEntityDao;
import com.my.db.Abstract.IUserDao;
import com.my.db.entity.AuxiliaryTable;
import com.my.db.entity.Equipment;
import com.my.db.entity.MessageHelp;
import com.my.db.entity.Tariff;
import com.my.db.entity.User;

public abstract class DaoFactory {
	
    public abstract void open() throws SQLException;

    public abstract void close();

    public static DaoFactory getDaoFactory(DataBaseSelector dataBase) throws DataBaseNotSupportedException{
        switch (dataBase) {
            case MY_SQL:
                return new MySQLDaoFactory();
            case MS_SQL:
                throw new DataBaseNotSupportedException(dataBase);
            case ORACLE:
                throw new DataBaseNotSupportedException(dataBase);
            case POSTGRESS:
                throw new DataBaseNotSupportedException(dataBase);
            default:
                throw new DataBaseNotSupportedException("Database type not set");
        }
    }

	public abstract IEntityDao<Tariff> getTarifManager();
		
	public abstract IEntityDao<User>  getUserManager();
	
	public abstract IUserDao getUserSManager();
    
    public abstract IEntityDao<MessageHelp>  getMessageHelpManager();

	public abstract IEntityDao<Equipment> getEquipmentManager();

	public abstract IEntityDao<AuxiliaryTable> getAuxiliaryTableManager(AuxiliaryTables auxiliaryTables);
	
	public abstract IAuxiliaryTableDao getAuxiliaryTableSManager(AuxiliaryTables auxiliaryTables);
}
