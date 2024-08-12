package busClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetails {
	public static void displayTickets(String name,String busNo,java.util.Date date) throws SQLException {
		String query="Select * from bookings where bus_No=? and bus_date=? and passenger_name=?;";
		Connection con=DataCon.dataCon();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,busNo);
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		pst.setDate(2,sqlDate);
		pst.setString(3,name);
		ResultSet res=pst.executeQuery();
		while(res.next()) {
			System.out.println(" Name : "+ res.getString(2)+"\n BusNo : "+res.getString(1) +"\n Date : "+res.getDate(3) + "\n No.Of.Seats : " + res.getInt(4)+"\n From : "+res.getString(5)+"\n To : "+res.getString(6));
		}
	}
	public static void cancelTickets(String name,String busNo,java.util.Date date) throws SQLException {
		String query="delete from bookings where bus_No=? and bus_date=? and passenger_name=?;";
		Connection con=DataCon.dataCon();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,busNo);
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		pst.setDate(2,sqlDate);
		pst.setString(3,name);
		pst.executeUpdate();
		
	}
	
	
}
