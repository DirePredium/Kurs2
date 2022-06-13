package com.my.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.DataSource;

import com.my.db.Abstract.DBEntity;
import com.my.db.entity.User;

public class DBManager<T> {

	private Mapper<T, PreparedStatement> mapperToDB;
    private Mapper<ResultSet, T> mapperFromDB;
    
    protected void setMapperToDB(Mapper<T, PreparedStatement> mapperToDB) {
        this.mapperToDB = mapperToDB;
    }

    protected void setMapperFromDB(Mapper<ResultSet, T> mapperFromDB) {
        this.mapperFromDB = mapperFromDB;
    }
	
	protected DBManager() {
	}
	
	
	public void insert(Connection con, User user, String SQL_statemant) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(SQL_statemant,
					Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setString(k++, user.getEmail());
			pstmt.setString(k++, user.getPassword());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
				}
			}
		} finally {
			close(rs);
			close(pstmt);
		}
	}
	
	protected boolean add(Connection connection, T item, String SQL_addNew) {
        boolean result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_addNew);
            mapperToDB.map(item, preparedStatement);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
        	sqle.printStackTrace();
            return false;
        }
        return result;
    }

	public List<User> findAllUsers(Connection con, String SQL_statemant) throws SQLException {
		List<User> users = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_statemant);
			while (rs.next()) {
				users.add(extractUser(rs));
			}
		} finally {
			close(rs);
			close(stmt);
		}
		return users;
	}
	
	protected <V> T findBy(Connection connection, Class t, String SQL_selectByParameter, V value)
            throws SQLException {
        T item = null;
        try {
        	item = getItemInstance(t);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_selectByParameter);
            addParameterToPreparedStatement(preparedStatement, 1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                mapperFromDB.map(resultSet, item);
            else
                throw new SQLException();
        } catch (SQLException sqle) {
        	sqle.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
        return item;
    }
	
	protected <V> List<T> findAsListBy(Connection connection, Class t, String SQL_selectByParameter, V value)
            throws SQLException {
        List<T> items = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_selectByParameter);
            addParameterToPreparedStatement(preparedStatement, 1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T item = getItemInstance(t);
                mapperFromDB.map(resultSet, item);
                items.add(item);
            }
        } catch (SQLException sqle) {
        	sqle.printStackTrace();
            throw new SQLException();
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new SQLException();
		}
        return items;
    }
	
	protected <V> boolean update(Connection connection, T item, String SQL_update, Integer paramNum, V value) {
        boolean result;
        try {
        	PreparedStatement preparedStatement = connection.prepareStatement(SQL_update);
            mapperToDB.map(item, preparedStatement);
            addParameterToPreparedStatement(preparedStatement, paramNum, value);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
        	sqle.printStackTrace();
            return false;
        }
        return result;
    }
	
	protected <V> boolean delete(Connection connection, String SQL_delete, V value) {
        boolean result;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_delete);
            addParameterToPreparedStatement(preparedStatement, 1, value);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqle) {
        	sqle.printStackTrace();
            return false;
        }
        return result;
    }
	
	protected List<T> findAll(Connection connection, Class t, String SQL_getAll) throws SQLException {
        List<T> items = new LinkedList<>();
        try {
        	
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_getAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	T item = getItemInstance(t);
                mapperFromDB.map(resultSet, item);
                items.add(item);
            }
        } catch (SQLException sqle) {
        	sqle.printStackTrace();
            throw new SQLException();
        }catch (Exception e) {
        	e.printStackTrace();
        	throw new SQLException();
		}
        return items;
    }
	
	private <V> void addParameterToPreparedStatement(PreparedStatement preparedStatement, Integer paramNum, V value)
            throws SQLException {
        if (value instanceof String)
            preparedStatement.setString(paramNum, (String) value);
        if (value instanceof Integer)
            preparedStatement.setInt(paramNum, (Integer) value);
        if (value instanceof Long)
            preparedStatement.setLong(paramNum, (Long) value);
    }
	
	private T getItemInstance(Class t) throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        T item = null;
        try {
            item = (T) t.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException ie) {
            
        }
        return item;
    }
	///////////////////////////////////////////

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		return user;
	}


	public static void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

	public static void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}

	public static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e) {
				// write to log
				// log.error("Cannot rollback a connection", e);
				e.printStackTrace();
			}
		}
	}


	public static void close(Connection con) {
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

}
