package com.my.db;

import java.sql.SQLException;

public class DBException extends Exception {
	public DBException(String message, SQLException cause) {
		super(message, cause);
	}
}
