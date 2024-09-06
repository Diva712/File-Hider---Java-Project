package com.diva.file_hider_project.views;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import com.diva.file_hider_project.dao.UserDAO;
import com.diva.file_hider_project.model.User;
import com.diva.file_hider_project.service.GeneratedOTP;
import com.diva.file_hider_project.service.SendOTPService;
import com.diva.file_hider_project.service.UserService;


public class Welcome {
	
	
	public void welcomeScreen() throws SQLException, InterruptedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Welcome to the Application:--------");
		
		System.out.println("Press 1 to Login");
		System.out.println("Press 2 to Register");
		System.out.println("Press 0 to Exit");
		int choice = 0;
		
		try {
			choice = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid input! Please enter a number.");
			//e.printStackTrace();
		}
		
		switch(choice) {
			case 1:
				login();
				break;
			case 2:
				signUp();
				break;
			case 0:
				System.out.println("Existing from the Application !!");
				System.exit(0);
			default:
				System.out.println("Please Enter Valid Choice !! ");
		}

	}

	private void login() throws InterruptedException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		try {
			if(UserDAO.isExists(email)) {
				String generatedOTP = GeneratedOTP.getOTP();
				SendOTPService.sendOTP(email, generatedOTP);
				System.out.println("Enter the OTP: ");
				String otp = sc.nextLine();
				if(otp.equals(generatedOTP)) {
					
					String username = UserService.findUser(email);
					
					UserView userview = new UserView(email , username);
					userview.home();
				}
				else {
					System.out.println("OTP is Invalid !!");
				}
			}
			else {
				System.out.println("User Not Found !! Please Register");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 private void signUp() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter name");
	        String name = sc.nextLine();
	        System.out.println("Enter email");
	        String email = sc.nextLine();
	        String genOTP = GeneratedOTP.getOTP();
	        SendOTPService.sendOTP(email, genOTP);
	        System.out.println("Enter the otp");
	        String otp = sc.nextLine();
	        if(otp.equals(genOTP)) {
	            User user = new User(name, email);
	            int response = UserService.saveUser(user);
	            switch (response) {
	                case 0 -> System.out.println("User already exist");
	                case 1 -> System.out.println("User registered Successfully !!");
	            }
	        } else {
	            System.out.println("Wrong OTP");
	        }

	    }




}
