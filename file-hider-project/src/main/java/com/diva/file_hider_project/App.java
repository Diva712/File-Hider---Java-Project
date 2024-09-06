package com.diva.file_hider_project;

import java.sql.SQLException;

import com.diva.file_hider_project.db.MyConnection;
import com.diva.file_hider_project.views.Welcome;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException, InterruptedException {
        //System.out.println("Hello World!");
        
        Welcome welcome = new Welcome();
        
        do {
        	welcome.welcomeScreen();
        }while(true);
    }
}
