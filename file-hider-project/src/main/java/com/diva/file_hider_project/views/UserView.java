package com.diva.file_hider_project.views;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.diva.file_hider_project.dao.DataDao;
import com.diva.file_hider_project.model.Data;

public class UserView {
	
	private String email;
	private String name;
	
	UserView(String email , String name){
		this.email = email;
		this.name = name;
	}
	
	public void home() throws InterruptedException {
		do {
			
			 System.out.println("Welcome " + this.name);
	            System.out.println("Press 1 to show hidden files");
	            System.out.println("Press 2 to hide a new file");
	            System.out.println("Press 3 to unhide a file");
	            System.out.println("Press 0 to exit");
	            Scanner sc = new Scanner(System.in);
	            int ch = Integer.parseInt(sc.nextLine());
	            switch(ch) {
	            	case 1 ->{
	            		try {
							List<Data> files = DataDao.getAllFiles(this.email);
							  System.out.println("ID - File Name");
		                        for (Data file : files) {
		                            System.out.println(file.getId() + " - " + file.getFileName());
		                        }

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}
	            	case 2 ->{
	            		System.out.println("Enter File Path: ");
	            		 String path = sc.nextLine();
	                     File f = new File(path);
	                     Data file = new Data(f.getName(), path, this.email);
	                     try {
							DataDao.hideFile(file);
						} catch (SQLException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

	            	}
	            	case 3 ->{
	            		
						
	            		 List<Data> files = null;
						try {
							files = DataDao.getAllFiles(this.email);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						  System.out.println("ID - File Name");
	                        for (Data file : files) {
	                            System.out.println(file.getId() + " - " + file.getFileName());
	                        }

	                        
	                        System.out.println("Enter the id of file to unhide");
	                        int id = Integer.parseInt(sc.nextLine());
	                        
	                        boolean isValid = false;
	                        for(Data file : files) {
	                        	if(file.getId() == id) {
	                        		isValid = true;
	                        		break;
	                        	}
	                        }
	                        
	                        if(isValid == true) {
	                        	try {
									DataDao.unhideFile(id);
								} catch (SQLException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                        }
	                        else {
	                        	System.out.println("Please Give Valid Id !!");
	                        }
	            		
	            	}
	            	case 0 ->{
	            		System.out.println("Exiting....");
	            		Thread.sleep(2000);
	            		System.out.println("Exited From Application");
	            		System.exit(0);
	            	}
	            }
			
		}while(true);
	}

}
