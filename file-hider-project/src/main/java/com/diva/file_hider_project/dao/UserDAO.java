package com.diva.file_hider_project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diva.file_hider_project.db.MyConnection;
import com.diva.file_hider_project.model.User;

public class UserDAO {
	
	private static Connection connection = MyConnection.getConnection();
	private static String query = "select email from users";
	public static boolean isExists (String email) throws SQLException {
		
		
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet rs =  ps.executeQuery();
		while(rs.next()) {
			String e = rs.getString(1);
			if(e.equals(email)) {
				return true;
			}
		}
		return false;
		
	}
	
	public static int saveUser(User user) throws SQLException {
       // Connection connection = MyConnection.getConnection();
        PreparedStatement ps =connection.prepareStatement("insert into users values(default, ?, ?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        return ps.executeUpdate();
    }

	public static String findUsernameByEmail(String email) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select name from users where email = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String username = rs.getString("name");
		
		return username;
		
	}



}
