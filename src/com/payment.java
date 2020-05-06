package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {
	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/patient","root","");
	 
		 //For testing
		 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }

	 return con;
	
	}
	
public String readPayment() {
		
		String output = "";
		
		try {
		Connection con = connect(); 
			 
			if (con == null) { 
				return "Error while connecting to the database for reading."; 
			} 
			
			//html table
			
				output = "<table border='1'><tr><th>Patient Name</th>"
					 +"<th>Appointment NO</th>"
					 + "<th>Appointment Date</th>"
					 + "<th>Amount</th>"
					 + "<th>Card Name</th>"
					 + "<th>Card NO</th>"
                     + "<th>Expire Month</th>"
					 + "<th>Expire Year</th>"
					 + "<th>CVV</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
		
			
			 
			String query = "select * from payment"; 
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) 
			{   
				String pid = Integer.toString(rs.getInt("pid")); 
				String pname = rs.getString("pname");  
				String ano = rs.getString("ano");  
				String adate = rs.getString("adate"); 
				String amount = Double.toString(rs.getDouble("amount"));
				String cname = rs.getString("cname");
				String cardno = rs.getString("cardno");
				String expmonth = rs.getString("expmonth");
				String expyear = rs.getString("expyear");
				String cvv = rs.getString("cvv");
				
				
				//add into html table
				
				output += "<tr><td><input id='hidpidUpdate' name='hidpidUpdate' type='hidden' value='" + pid + "'>"+ pname+ "</td>"; 
				
				output += "<td>" + ano + "</td>"; 
				output += "<td>" + adate + "</td>"; 
				output += "<td>" + amount + "</td>"; 
				output += "<td>" + cname + "</td>";
				output += "<td>" + cardno + "</td>";
                output += "<td>" + expmonth + "</td>"; 
				output += "<td>" + expyear + "</td>";
				output += "<td>" + cvv+ "</td>";
                

				
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'</td>"
						+ "<td><input name='btnRemove' type='button'"
						+ "value='Remove' class='btnRemove btn btn-danger'  data-pid='"
						+ pid + "'>" + "</td></tr>"; 
			  } 
		  
			  con.close();  
			  
			  // Complete the html table 
			  output += "</table>";   
		
		}
		catch (Exception e) {  
			output = "Error while reading the payment.";  
			System.err.println(e.getMessage()); 
		}
		return output;
		
	}
	
	public String insertPayment(String pname, String ano, String adate, String amount,String cname, String cardno, String expmonth, String expyear,String cvv) {
		
		String output = ""; 
		
		try {
		Connection con = connect(); 
	
		if (con == null) 
		{ 
			return "Error while connecting to the database"; 
		} 
	
	
		String query = " insert into payment(pid,pname,ano,adate,amount,cname,cardno,expmonth,expyear,cvv)" + " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?)"; 
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, pname);
		preparedStmt.setString(3, ano);
		preparedStmt.setString(4, adate);
		preparedStmt.setDouble(5, Double.parseDouble(amount));
		preparedStmt.setString(6, cname);
		preparedStmt.setString(7, cardno);
		preparedStmt.setString(8, expmonth);
		preparedStmt.setString(9, expyear);
		preparedStmt.setString(10, cvv);
		preparedStmt.execute();
		con.close(); 
		
		// execute the statement

		String newpayment = readPayment();
		 output = "{\"status\":\"success\", \"data\": \"" +newpayment + "\"}"; 
		
		
		}
		catch (Exception e)
		 {
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}";
		 System.err.println(e.getMessage());
		 } 
		return output; 
	}
	
	
	
	public String updatePayment(String pid,String pname, String ano, String adate, String amount,String cname, String cardno, String expmonth, String expyear,String cvv)
	{
		String output ="";
		
		try
		{
			Connection con = connect();
			
			if(con==null)
			{
				return "Error while connecting to the database for updating";
			}
			
			String query = "UPDATE payment SET pname=?,ano=?,adate=?,amount=?,cname=?,cardno=?,expmonth=?,expyear=?,cvv=? WHERE pid=?";
			
			PreparedStatement preparedStmt =con.prepareStatement(query);
			
			preparedStmt.setString(1, pname);
			preparedStmt.setString(2, ano);
			preparedStmt.setString(3, adate);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setString(5, cname);
			preparedStmt.setString(6, cardno);
			preparedStmt.setString(7, expmonth);
			preparedStmt.setString(8, expyear);
			preparedStmt.setString(9, cvv);
			preparedStmt.setInt(10,Integer.parseInt(pid));
			
			preparedStmt.execute();
			con.close();
			System.out.println("updated");
			String newpayment = readPayment();
			 output = "{\"status\":\"success\", \"data\": \"" + newpayment + "\"}"; 
			
		}
		catch(Exception e)
		{
			// TODO: handle exception
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}"; 
				System.out.println("not updated");
	
				System.err.println(e.getMessage());
			
		}
		return output;
	}
	
	
	public String deletePayment(String pid)
	{
	 String output = "";
	 
	try
	{
	Connection con = connect();
	
	 	if (con == null)
	 	{
	 		return "Error while connecting to the database for deleting.";
	 	}
	 
	 String query = "delete from payment where pid=?";
	 java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 preparedStmt.setInt(1, Integer.parseInt(pid));

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newpayment = readPayment();
	 output = "{\"status\":\"success\", \"data\": \"" +newpayment + "\"}"; 
	 }
	 catch (Exception e)
	 {

		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment.\"}";
		 System.err.println(e.getMessage()); 
	 }
	return output;
	}

}

