package com.my.db.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.my.db.DBManager;
import com.my.db.Mapper;
import com.my.db.Abstract.IEntityDao;
import com.my.db.Abstract.IUserDao;
import com.my.db.entity.User;
import com.my.db.entity.UserRole;

public class UserManager extends DBManager<User> implements IEntityDao<User>, IUserDao {

	private Connection connection;
	
	private static String SQL_selectAllUsers = "SELECT * FROM user";
	private static String SQL_selectById = "SELECT * FROM user " +
            "WHERE id_user=?;";
	private static String SQL_selectByRole = "SELECT * FROM user " +
            "WHERE role=?;";
	private static String SQL_selectByLogin = "SELECT * FROM user " +
            "WHERE email=?;";
	private static String SQL_addNew = "INSERT INTO mydb.user " +
            "(email, role, password) " +
            "VALUES (?,?,?)";
	private static String SQL_updateById = "UPDATE mydb.user SET " +
            "email=?, role=?, password=? " +
            "WHERE id_user=?;";
	private static String SQL_deleteUser = "DELETE FROM mydb.user WHERE id_user=?;";
	
	private Mapper<ResultSet, User> mapperFromDB = (ResultSet resultSet, User user) -> {
		user.setId(resultSet.getInt("id_user"));
		user.setEmail(resultSet.getString("email"));
		user.setRole(UserRole.valueOf(resultSet.getString("role").toUpperCase()));
		user.setPassword(resultSet.getString("password"));
    };
    
    private Mapper<User, PreparedStatement> mapperToDB = (User user, PreparedStatement preparedStatement) -> {
        int k = 1;
        preparedStatement.setString(k++, user.getEmail());
        preparedStatement.setString(k++, user.getRole().toString());
        preparedStatement.setString(k++, user.getPassword());
    };
	
	public UserManager(Connection connection) {
		super.setMapperToDB(mapperToDB);
        super.setMapperFromDB(mapperFromDB);
	    this.connection = connection;
	}

	@Override
	public List<User> findAllRecords() throws SQLException {
		return findAll(connection, User.class, SQL_selectAllUsers);
	}
	
	@Override
    public List<User> findUserByRole(UserRole role) throws SQLException {
        return findAsListBy(connection, User.class, SQL_selectByRole, role.toString());
    }
	
	@Override
    public User findRecordById(Integer id) throws SQLException {
        return findBy(connection, User.class, SQL_selectById, id);
    }
	
	@Override
	public User findUserByEmail(String email) throws SQLException {
		return findBy(connection, User.class, SQL_selectByLogin, email);
	}

	@Override
	public boolean addRecord(User record) {
		return add(connection, record, SQL_addNew);
	}

	@Override
	public boolean updateRecord(User record) {
		return update(connection, record, SQL_updateById, 4, record.getId());
	}

	@Override
	public boolean deleteRecord(User record) {
		return delete(connection, SQL_deleteUser, record.getId());
	}
}
