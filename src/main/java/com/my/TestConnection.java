package com.my;

import java.io.IOException;
import java.sql.*;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.my.db.DaoFactory;
import com.my.db.DataBaseSelector;
import com.my.db.MySQLDaoFactory;

@WebServlet("/testConnection")
public class TestConnection extends HttpServlet {
	
	/*
	private static final String CONNECTION_URL = 
			"jdbc:mysql://localhost:3306/testdb?"
			+ "user=test&password=test";
	*/
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB");
			Connection conn = ds.getConnection();
			System.out.println(conn);
			conn.close();
			System.out.println("~~~");
			MySQLDaoFactory daoSql = (MySQLDaoFactory)DaoFactory.getDaoFactory(DataBaseSelector.MY_SQL);
			System.out.println(daoSql.getConnection());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("Test#doGet");

		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION_URL);
			System.out.println(con);
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	*/
	
}