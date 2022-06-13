package com.my.db.Abstract;

import java.sql.SQLException;
import java.util.List;

import com.my.db.entity.User;
import com.my.db.entity.UserRole;

public interface IUserDao extends IDao{
	List<User> findUserByRole(UserRole role) throws SQLException;
	
    User findUserByEmail(String email) throws SQLException;
}
