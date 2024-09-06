package com.diva.file_hider_project.service;

import java.sql.SQLException;

import com.diva.file_hider_project.dao.UserDAO;
import com.diva.file_hider_project.model.User;

public class UserService {
	
	  public static Integer saveUser(User user){
	        try {
	            if(UserDAO.isExists(user.getEmail())) {
	                return 0;
	            } else {
	                return UserDAO.saveUser(user);

	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }
	  
	  public static String findUser(String email) throws SQLException {
		  String username = UserDAO.findUsernameByEmail(email);
		  return username;
	  }

}
