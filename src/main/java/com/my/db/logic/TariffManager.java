package com.my.db.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.my.db.DBManager;
import com.my.db.Mapper;
import com.my.db.Abstract.IEntityDao;
import com.my.db.entity.Tariff;

public class TariffManager extends DBManager<Tariff> implements IEntityDao<Tariff> {
	private Connection connection;

	private static String SQL_selectAllTariffs = "SELECT * FROM tariff  ORDER BY price ASC";
	private static String SQL_selectById = "SELECT * FROM tariff " + "WHERE id_tariff=?;";
	private static String SQL_addNew = "INSERT INTO mydb.tariff " + "(title) " + "VALUES (?)";
	private static String SQL_updateById = "UPDATE mydb.tariff SET " + "title=? " + "WHERE id_tariff=?;";
	private static String SQL_deleteTariff = "DELETE FROM mydb.tariff WHERE id_tariff=?;";

	private Mapper<ResultSet, Tariff> mapperFromDB = (ResultSet resultSet, Tariff tariff) -> {
		tariff.setId(resultSet.getInt("id_tariff"));
		tariff.setTitle(resultSet.getString("title"));
		tariff.setPrice(Float.valueOf(resultSet.getString("price")));
		tariff.setSpeed(Float.valueOf(resultSet.getString("speed")));
		tariff.setImg_link(resultSet.getString("img_link"));
	};

	private Mapper<Tariff, PreparedStatement> mapperToDB = (Tariff tariff, PreparedStatement preparedStatement) -> {
		int k = 1;
		preparedStatement.setString(k++, tariff.getTitle());
	};

	public TariffManager(Connection connection) {
		super.setMapperToDB(mapperToDB);
		super.setMapperFromDB(mapperFromDB);
		this.connection = connection;
	}

	@Override
	public List<Tariff> findAllRecords() throws SQLException {
		return findAll(connection, Tariff.class, SQL_selectAllTariffs);
	}

	@Override
	public Tariff findRecordById(Integer id) throws SQLException {
		return findBy(connection, Tariff.class, SQL_selectById, id);
	}

	@Override
	public boolean addRecord(Tariff record) {
		return add(connection, record, SQL_addNew);
	}

	@Override
	public boolean updateRecord(Tariff record) {
		return update(connection, record, SQL_updateById, 2, record.getId());
	}

	@Override
	public boolean deleteRecord(Tariff record) {
		return delete(connection, SQL_deleteTariff, record.getId());
	}
}