package com.my.db.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.my.db.DBManager;
import com.my.db.Mapper;
import com.my.db.Abstract.IEntityDao;
import com.my.db.entity.MessageHelp;
import com.my.db.entity.MessageHelpStatus;
import com.my.db.entity.User;

public class MessageHelpManager extends DBManager<MessageHelp> implements IEntityDao<MessageHelp>{
	private Connection connection;
	
	private static String SQL_selectAllMessageHelp = "SELECT * FROM message_help ORDER BY STATUS ASC";
	private static String SQL_selectById = "SELECT * FROM message_help " +
            "WHERE id_user=?;";
	private static String SQL_addNew = "INSERT INTO mydb.message_help " +
            "(status, email, message) " +
            "VALUES (?,?,?)";
	private static String SQL_updateById = "UPDATE mydb.message_help SET " +
            "status =?, email=?, message=? " +
            "WHERE id_message_help=?;";
	private static String SQL_deleteMessageHelp = "DELETE FROM mydb.message_help WHERE id_message_help=?;";
	
	private Mapper<ResultSet, MessageHelp> mapperFromDB = (ResultSet resultSet, MessageHelp messageHelp) -> {
		messageHelp.setId(resultSet.getInt("id_message_help"));
		messageHelp.setStatus(MessageHelpStatus.valueOf(resultSet.getString("status").toUpperCase()));
		messageHelp.setEmail(resultSet.getString("email"));
		messageHelp.setMessage(resultSet.getString("message"));
    };
    
    private Mapper<MessageHelp, PreparedStatement> mapperToDB = (MessageHelp messageHelp, PreparedStatement preparedStatement) -> {
        int k = 1;
        preparedStatement.setString(k++, messageHelp.getStatus().toString());
        preparedStatement.setString(k++, messageHelp.getEmail());
        preparedStatement.setString(k++, messageHelp.getMessage());
    };
	
	public MessageHelpManager(Connection connection) {
		super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
	    this.connection = connection;
	}

	@Override
	public List<MessageHelp> findAllRecords() throws SQLException {
		return findAll(connection, MessageHelp.class, SQL_selectAllMessageHelp);
	}
	
	@Override
    public MessageHelp findRecordById(Integer id) throws SQLException {
        return findBy(connection, User.class, SQL_selectById, id);
    }

	@Override
	public boolean addRecord(MessageHelp record) {
		return add(connection, record, SQL_addNew);
	}

	@Override
	public boolean updateRecord(MessageHelp record) {
		return update(connection, record, SQL_updateById, 4, record.getId());
	}

	@Override
	public boolean deleteRecord(MessageHelp record) {
		return delete(connection, SQL_deleteMessageHelp, record.getId());
	}
}
