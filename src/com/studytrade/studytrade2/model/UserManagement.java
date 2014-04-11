package com.studytrade.studytrade2.model;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserManagement {

	public UserManagement() {

	}

	public boolean LogMeIn(String username, String password){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			GetDatabaseConnection databasecon = new GetDatabaseConnection();
			con = databasecon.getConnection();
			stmt = con.createStatement();
			String query = "select count(*) as CNT from table_user where nickname='"
					+ username + "' and password='" + password + "'";
			rs = stmt.executeQuery(query);
			int CNT = 0;
			while (rs.next()) {
				CNT = rs.getInt(1);
			}
			con.close();
			if (CNT == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean RegisterMe(String forename, String lastname,
			String nickname, String city, Object university,
			String studydirection, String mail, String password1) {
		Connection con = null;
		PreparedStatement ps = null;

		if (!CheckIfUserAlreadyExists(nickname, mail)) {
			try {
				GetDatabaseConnection databasecon = new GetDatabaseConnection();
				con = databasecon.getConnection();
				String query = "insert into table_user (`id`, `forename`,`lastname`,`nickname`,`city`,`university`,`studydirection`,`mail`,`password`,`activated`) VALUES (default,?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(query);
				ps.setString(1, forename);
				ps.setString(2, lastname);
				ps.setString(3, nickname);
				ps.setString(4, city);
				ps.setString(5, (String) university);
				ps.setString(6, studydirection);
				ps.setString(7, mail);
				ps.setString(8, password1);
				ps.setInt(9, '0');
				ps.executeUpdate();

				con.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean CheckIfUserAlreadyExists(String nickname, String mail) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			GetDatabaseConnection databasecon = new GetDatabaseConnection();
			con = databasecon.getConnection();
			stmt = con.createStatement();
			String query = "select count(*) as CNT from table_user where  nickname='"
					+ nickname + "' or mail='" + mail + "'";
			rs = stmt.executeQuery(query);
			int CNT = 0;
			while (rs.next()) {
				CNT = rs.getInt(1);
			}
			con.close();
			if (CNT == 0) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
}