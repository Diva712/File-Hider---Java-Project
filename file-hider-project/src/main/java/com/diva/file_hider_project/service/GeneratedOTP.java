package com.diva.file_hider_project.service;

import java.util.Random;

public class GeneratedOTP {
	
	public static String getOTP() {
		Random randomNumber = new Random();
		return String.format("%04d", randomNumber.nextInt(1000));
	}
}
