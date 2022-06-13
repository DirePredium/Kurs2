package com.my.db.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.my.db.AuxiliaryTables;
import com.my.db.DBManager;
import com.my.db.Mapper;
import com.my.db.Abstract.IAuxiliaryTableDao;
import com.my.db.Abstract.IEntityDao;
import com.my.db.entity.AuxiliaryTable;
import com.mysql.cj.util.StringUtils;

public class AuxiliaryTableManager extends DBManager<AuxiliaryTable> implements IEntityDao<AuxiliaryTable>,  IAuxiliaryTableDao{

	private Connection connection;
	private String tableName;
	private String field1;
	private String field2;
	
	
	private static String SQL_selectAllAuxiliary = "SELECT * FROM AuxiliaryTable";
	private static String SQL_selectById = "SELECT * FROM AuxiliaryTable " +
            "WHERE id_AuxiliaryTable=?;";
	private static String SQL_selectByField = "SELECT * FROM AuxiliaryTable " +
            "WHERE Field=?;";
	private static String SQL_addNew = "INSERT INTO mydb.AuxiliaryTable " +
            "(Field1, Field2) " +
            "VALUES (?, ?)";
	private static String SQL_updateById = "UPDATE mydb.AuxiliaryTable SET " +
            "Field1=?, Field2=? " +
            "WHERE id_AuxiliaryTable=?;";
	private static String SQL_deleteAuxiliary = "DELETE FROM mydb.AuxiliaryTable WHERE id_AuxiliaryTable=?;";
	
	private Mapper<ResultSet, AuxiliaryTable> mapperFromDB = (ResultSet resultSet, AuxiliaryTable auxiliaryTable) -> {
		System.out.println("id_"+tableName +" "+field1 +" "+field2);
		auxiliaryTable.setId(resultSet.getInt("id_"+tableName));
		auxiliaryTable.setT1(resultSet.getInt(field1));
		auxiliaryTable.setT2(resultSet.getInt(field2));
    };
    
    private Mapper<AuxiliaryTable, PreparedStatement> mapperToDB = (AuxiliaryTable auxiliaryTable, PreparedStatement preparedStatement) -> {
        int k = 1;
        preparedStatement.setInt(k++, auxiliaryTable.getT1());
        preparedStatement.setInt(k++, auxiliaryTable.getT2());
    };
	
	public AuxiliaryTableManager(Connection connection, AuxiliaryTables auxiliaryTables) {
		super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
	    this.connection = connection;
	    this.tableName = auxiliaryTables.getTable();
	    this.field1 = auxiliaryTables.getField1();
	    this.field2 = auxiliaryTables.getField2();
	    System.out.println("AuxiliaryTableManager constuctor ==> "+tableName+" "+field1+" " +field2);
	}

	@Override
	public List<AuxiliaryTable> findAllRecords() throws SQLException {
		System.out.println(SQL_selectAllAuxiliary.replaceAll("AuxiliaryTable", tableName));
		return findAll(connection, AuxiliaryTable.class, SQL_selectAllAuxiliary.replaceAll("AuxiliaryTable", tableName));
	}
	
	@Override
    public AuxiliaryTable findRecordById(Integer id) throws SQLException {
        return findBy(connection, AuxiliaryTable.class, SQL_selectById.replaceAll("AuxiliaryTable", tableName), id);
    }
	@Override
	public AuxiliaryTable findUserByField1(Integer id) throws SQLException {
		return findBy(connection, AuxiliaryTable.class,SQL_selectByField.replaceAll("AuxiliaryTable", tableName).replaceAll("Field", field1) , id);
	}
	@Override
	public AuxiliaryTable findUserByField2(Integer id) throws SQLException {
		return findBy(connection, AuxiliaryTable.class, SQL_selectByField.replaceAll("AuxiliaryTable", tableName).replaceAll("Field", field2), id);
	}

	@Override
	public boolean addRecord(AuxiliaryTable record) {
		return add(connection, record, SQL_addNew
				.replaceAll("AuxiliaryTable", tableName)
				.replaceAll("Field1", field1)
				.replaceAll("Field2", field2));
	}

	@Override
	public boolean updateRecord(AuxiliaryTable record) {;
		String string = SQL_updateById
				.replaceAll("AuxiliaryTable", tableName)
				.replaceAll("Field1", field1)
				.replaceAll("Field2", field2);

		System.out.println("AuxiliaryTableManager updateRecord table"+tableName+"\n"+string);
		return update(connection, record, SQL_updateById
												.replaceAll("AuxiliaryTable", tableName)
												.replaceAll("Field1", field1)
												.replaceAll("Field2", field2), 
														3, record.getId());
	}

	@Override
	public boolean deleteRecord(AuxiliaryTable record) {
		return delete(connection, SQL_deleteAuxiliary.replaceAll("AuxiliaryTable", tableName), record.getId());
	}
}
