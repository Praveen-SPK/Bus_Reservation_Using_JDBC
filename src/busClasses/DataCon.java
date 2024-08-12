package busClasses;
import java.sql.*;

public class DataCon {
	private static final String url="jdbc:mysql://localhost:3306/BusDatabase";
	private static final String name="root";
	private static final String password="Spk@2003";
	
public static Connection dataCon() throws SQLException {
	return DriverManager.getConnection(url,name,password);
}
public static ResultSet displayBuses() throws SQLException {
	String query="select * from bus";
	Connection con=DataCon.dataCon();
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery(query);
	
	while(rs.next()) {
		System.out.println("Bus No : "+rs.getString(1)+" --->>> "+"Bus Capacity : "+rs.getInt(3));
	}
	return rs;	
}
	
	
public static ResultSet bookingDatabase() throws SQLException {
	Connection con=DataCon.dataCon();
	String query="select * from bookings;";
	Statement st=con.createStatement();
	return st.executeQuery(query);
}

public static void addBooking(String busNo,String name,java.util.Date date,int seatCount,String start,String end) throws SQLException {
	String query="Insert into bookings values(?,?,?,?,?,?); ";
	Connection con=DataCon.dataCon();
	PreparedStatement pst=con.prepareStatement(query);
	pst.setString(1,busNo);
	pst.setString(2,name);
	java.sql.Date sqlDate=new java.sql.Date(date.getTime());
	pst.setDate(3,sqlDate);
	pst.setInt(4,seatCount);
	pst.setString(5,start);
	pst.setString(6,end);
	pst.executeUpdate();
	System.out.println("Tickets Booked SuccessFully...");
	
}

public static int countSeats(String busNo,java.util.Date date) throws SQLException {
	String query="Select sum(seat_Count) from bookings where bus_No=? and bus_date=?;";
	Connection con=DataCon.dataCon();
	PreparedStatement pst=con.prepareStatement(query);
	pst.setString(1,busNo);
	java.sql.Date sqlDate=new java.sql.Date(date.getTime());
	pst.setDate(2,sqlDate);
	
	ResultSet res=pst.executeQuery();
	res.next();
	return res.getInt(1);
}

public static ResultSet BusInfo() throws SQLException {
	String query="select * from bus";
	Connection con=DataCon.dataCon();
	Statement st=con.createStatement();
	return st.executeQuery(query);
}



}