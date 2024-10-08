package com.diva.file_hider_project.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.diva.file_hider_project.db.MyConnection;
import com.diva.file_hider_project.model.Data;

public class DataDao {
	
	private static Connection connection = MyConnection.getConnection();
	public static List<Data> getAllFiles(String email) throws SQLException{
		String query = "Select * from data where email = ?";
		List<Data>list = new ArrayList<>();
		
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String path = rs.getString(3);
				list.add(new Data(id , name , path));
			
			}
			if(list.size() == 0) {
				System.out.println("There is no hidden files !!");
			}
			return list;	
		
	}
	
	public static int hideFile(Data file) throws SQLException, IOException {
		
		String query = "insert into data (name , path , email , bin_data) values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(query);
		
		ps.setString(1, file.getFileName());
		ps.setString(2, file.getPath());
		ps.setString(3,file.getEmail());
		
		File f = new File(file.getPath());
		FileReader fr = new FileReader(f);
		ps.setCharacterStream(4, fr , f.length());
		int ans = ps.executeUpdate();
		
		fr.close();
		f.delete();
		
		System.out.println("File Hidden Successfully !!");
		
		return ans;
		
	}
	
	public static void unhideFile(int id) throws SQLException, IOException {
		String query = "select path , bin_data from data where id = ?";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String path = rs.getString("path");
		Clob clob = rs.getClob("bin_data");
		
		Reader reader = clob.getCharacterStream();
		FileWriter fw = new FileWriter(path);
		int i ;
		while((i = reader.read())!=-1) {
			fw.write((char)i);
		}
		fw.close();
		ps = connection.prepareStatement("delete from data where id = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
		
		System.out.println("File Un-hidden Successfully !!");
		
		
		
	}

}
