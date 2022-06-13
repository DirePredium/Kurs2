package com.my.db.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.my.db.DBManager;
import com.my.db.Mapper;
import com.my.db.Abstract.IEntityDao;
import com.my.db.entity.Equipment;


public class EquipmentManager extends DBManager<Equipment> implements IEntityDao<Equipment>{
	private Connection connection;
	
	private static String SQL_selectAllEquipments = "SELECT * FROM equipment";
	private static String SQL_selectById = "SELECT * FROM equipment " +
            "WHERE id_equipment=?;";
	private static String SQL_addNew = "INSERT INTO mydb.equipment " +
            "(name) " +
            "VALUES (?)";
	private static String SQL_updateById = "UPDATE mydb.equipment SET " +
            "name=?" +
            "WHERE id_equipment=?;";
	private static String SQL_deleteEquipment = "DELETE FROM mydb.equipment WHERE id_equipment=?;";
	
	private Mapper<ResultSet, Equipment> mapperFromDB = (ResultSet resultSet, Equipment equipment) -> {
		equipment.setId(resultSet.getInt("id_equipment"));
		equipment.setName(resultSet.getString("name"));
    };
    
    private Mapper<Equipment, PreparedStatement> mapperToDB = (Equipment equipment, PreparedStatement preparedStatement) -> {
        int k = 1;
        preparedStatement.setString(k++, equipment.getName());
    };
	
	public EquipmentManager(Connection connection) {
		super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
	    this.connection = connection;
	}

	@Override
	public List<Equipment> findAllRecords() throws SQLException {
		return findAll(connection, Equipment.class, SQL_selectAllEquipments);
	}
	
	@Override
    public Equipment findRecordById(Integer id) throws SQLException {
        return findBy(connection, Equipment.class, SQL_selectById, id);
    }

	@Override
	public boolean addRecord(Equipment record) {
		return add(connection, record, SQL_addNew);
	}

	@Override
	public boolean updateRecord(Equipment record) {
		return update(connection, record, SQL_updateById, 4, record.getId());
	}

	@Override
	public boolean deleteRecord(Equipment record) {
		return delete(connection, SQL_deleteEquipment, record.getId());
	}
}
