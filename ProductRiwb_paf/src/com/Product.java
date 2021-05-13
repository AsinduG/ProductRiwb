package com;

import java.sql.*;   
import java.text.DateFormat;
import java.text.SimpleDateFormat;
 
public class Product {

	//create database connection
	private Connection connect(){
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadget2","root", ""); 
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
		System.out.print(con);
		return con;
	}

	//implement insert method to the order
	public String insertOderProduct(String pid,String researcherId,String date,String time,String totAmount,String status) 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for inserting.";
		 } 
	 // create a prepared statement
	 String query = " insert into `order`(`orderId`,`pid`,`researcherId`,`date`,`time`,`totAmount`,`status`)"
	 				+ "values(?,?,?,STR_TO_DATE(?,'%Y/%m/%d'),?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	  
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setInt(2, Integer.parseInt(pid)); 
	 preparedStmt.setInt(3, Integer.parseInt(researcherId)); 
	 preparedStmt.setString(4, date);
	 preparedStmt.setString(5,time); 
	 preparedStmt.setFloat(6, Float.parseFloat(totAmount));
	 preparedStmt.setString(7, status);
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newOrders = viewOrder();
	 output = "{\"status\":\"success\", \"data\": \"" + newOrders + "\"}";
	 
	 } 
	 catch (Exception e) 
	 { 
		 //print error messages if exist
		 output = "{\"status\":\"error\", \"data\":\"Error while inserting the order.\"}";
		 System.err.println(e.getMessage());
	 } 
	 	return output; 
	 }
	
	//implement display all ordered products 
	public String viewOrder() {
		
		String output = "";
		try {
			Connection con =  connect();
			System.out.print(con);
			if(con == null) {
				System.out.print("hello");
				return "Error while connecting to the database for reading.";
				
			}
			System.out.print("hellofirst");
			//create table view structure
			output = "<table border ='1'><tr><th>projectID</th>" +
					"<th>ResearcherID</th>" + 
					"<th>date</th>" +
					 "<th>time</th>" +
					 "<th>TotalAmount</th>" +
					 "<th>status</th>" +
					 "<th>Update</th>" +
					 "<th>Remove</th></tr>"; 
			
			String query = "SELECT * FROM `order`";
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			//iterate through the rows which taking from the table using result set
			while(rs.next()) {
				String orderId = Integer.toString(rs.getInt("orderId"));
				String pid = Integer.toString(rs.getInt("pid"));
				String researcherId = Integer.toString(rs.getInt("researcherId"));
				String date = rs.getString("date");
				String time = rs.getString("time");
			    String totAmount = Float.toString(rs.getFloat("totAmount"));
			    String status = rs.getString("status");
			
			    //fill table using 
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate'type='hidden'value='" + orderId+ "'>"+ pid + "</td>";
				output += "<td>"+ researcherId + "</td>";
				output += "<td>"+ date + "</td>";
				output += "<td>"+ time + "</td>";
				output += "<td>"+ totAmount + "</td>";
				output += "<td>"+ status + "</td>";
				
				//implement buttons for the view also bind table's id
				output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
						 + "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"
						 + orderId + "'>" + "</td></tr>";
			}
				con.close();
				output += "</table>";			
		}
		catch(Exception e) {
			output = "Occure error while reading products";
			System.err.println(e.getMessage()); 
		}
		
		return output;
		 
	}
	
	//implement update ordered products in the order table
	public String updateOrderProduct(String orderId, String pid,String researcherId,String date,String time,String totAmount,String status) 
	{
		String output = "";
		
		try 
		{
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database";
			}
			//create prepared statement
			String query = "Update `order` SET pid=? ,researcherId=? ,date=STR_TO_DATE(?,'%Y/%m/%d') ,time=?, ,totAmount=? ,status=? Where orderId=?";
			PreparedStatement stmt = con.prepareStatement(query);
			
			stmt.setInt(1,Integer.parseInt(pid));
			stmt.setInt(2,Integer.parseInt(researcherId));
			stmt.setString(3,date);
			stmt.setString(4,time);
			stmt.setFloat(5,Float.parseFloat(totAmount));
			stmt.setString(6,status);
			stmt.setInt(7,Integer.parseInt(orderId));
			//execution statement
			stmt.execute();
			con.close();
			String newOrders = viewOrder();
			output = "{\"status\":\"success\", \"data\": \"" + newOrders + "\"}";
			
		}
		catch(Exception e) {
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the order.\"}";
			 System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	//implement delete ordered products from cart
	public String deleteOrder(String orderId) 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for deleting."; } 
	 
	 // create a prepared statement
	 String query = "delete from `order` where orderId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(orderId)); 
	
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newOrders = viewOrder();
	 output = "{\"status\":\"success\", \"data\": \"" + newOrders + "\"}";
	 }
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\":\"Error while deleting the order.\"}";
		 System.err.println(e.getMessage());
	 } 
	 	return output; 
	 }
	
	//implement display order method for the relevant user
	public String viewSelectOrder(int orderId) {
			
			String output = "";
			try {
				Connection con =  connect();
				System.out.print(con);
				if(con == null) { 
					return "Error while connecting to the database for reading.";
					
				}
				//create html table's view structure
				output = "<table border ='1'><tr><th>projectID</th>" +
						 "<th>date</th>" +
						 "<th>time</th>" +
						 "<th>TotalAmount</th>" +
						 "<th>status</th>" +
						 "<th>Update</th>" +
						 "<th>Remove</th></tr>"; 
				//create prepared statement
				String query = "SELECT * FROM `order` where orderId=" +orderId ;
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				
				
				while(rs.next()) {
					String orderId1 = Integer.toString(rs.getInt("orderId"));
					String pid = Integer.toString(rs.getInt("pid"));
					String researcherId = Integer.toString(rs.getInt("researcherId"));
					String date = rs.getString("date");
					String time = rs.getString("time");
				    String totAmount = Float.toString(rs.getFloat("totAmount"));
				    String status = rs.getString("status");
				
				    //fill table using 
					output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate'type='hidden'value='" + orderId1+ "'>"+ pid + "</td>";
					output += "<td>"+ researcherId + "</td>";
					output += "<td>"+ date + "</td>";
					output += "<td>"+ time + "</td>";
					output += "<td>"+ totAmount + "</td>";
					output += "<td>"+ status + "</td>";
					
					//implement buttons for the view also bind table's id
					output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
							 + "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid='"
							 + orderId1 + "'>" + "</td></tr>";
				}
					con.close();
					output += "</table>";			
			}
			catch(Exception e) {
				output = "Occure error while reading products";
				System.err.println(e.getMessage()); 
			}
			
			return output;
			 
		}
}
