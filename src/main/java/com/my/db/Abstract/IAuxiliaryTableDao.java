package com.my.db.Abstract;

import java.sql.SQLException;

import com.my.db.entity.AuxiliaryTable;

public interface IAuxiliaryTableDao extends IDao {
	AuxiliaryTable findUserByField1(Integer id) throws SQLException;
	AuxiliaryTable findUserByField2(Integer id) throws SQLException;
}
