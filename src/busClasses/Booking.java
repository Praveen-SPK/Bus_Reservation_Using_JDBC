package busClasses;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
public class Booking {
	
	String busNo;
	String name;
	Date date;
	String destination;
	String startPoint;
	int seats;
	Scanner sc=new Scanner(System.in);
	
	public void userDetails() throws SQLException {
		System.out.println("==============================================================");
		while(true) {
		System.out.println("Enter the Bus No : ");
		this.busNo=sc.nextLine();
		if(check()==true) { 
			break;
		}else {
			System.err.println("Enter Valid Bus No");
		}}
		System.out.println("==============================================================");
		System.out.println("Enter Your Name : ");
		this.name=sc.nextLine();
		System.out.println("==============================================================");
		while(true) {
		System.out.println("Enter the Date : ");
		String date_str=sc.nextLine();
		try {
			this.date = new SimpleDateFormat("dd/MM/yyyy").parse(date_str);
			break;
		} catch (ParseException e) {
			System.err.println(e+"Date Should Be In DD/MM/YYYY Format");
		}
		}
		System.out.println("==============================================================");
		
		System.out.println("Enter Your Location  : ");
		this.startPoint=sc.nextLine();
		System.out.println("==============================================================");
		System.out.println("Enter Your Destination  : ");
		this.destination=sc.nextLine();
		System.out.println("==============================================================");
		while(true) {
			System.out.println("No of Seats to Book : ");
			this.seats=sc.nextInt();
			if(seats>20) {
				System.err.println("Maximum 20 Seats Allowed to Book ");
			}
			else {
				break;
			}
			}
		System.out.println("==============================================================");
	}
	
	private boolean check() throws SQLException {
		ResultSet res=DataCon.BusInfo();
		while (res.next()) {
			if(res.getString(1).equalsIgnoreCase(busNo)) {
				return true;
			}
		}
		return false;
	}

	public boolean isAvailable() throws SQLException {
		ResultSet res=DataCon.bookingDatabase();
		ResultSet rs=DataCon.BusInfo();
		int capacity=0;
		while(rs.next()) {
			if(rs.getString(1).equalsIgnoreCase(busNo)) {
				capacity=rs.getInt(3);
			}
		}		
		res.next();	
		int value=DataCon.countSeats(busNo,date);
		capacity -= value;
		return seats<=capacity;
	}
	
	public boolean isEmptyBooking() throws SQLException {

		ResultSet res=DataCon.bookingDatabase();
		if(res.next()) {
			return false;
		}
		return true;
	}
	
	public int seatsAvailable() throws SQLException {
		int seatCount=DataCon.countSeats(busNo,date);
		int totalSeats=0;
		ResultSet rs=DataCon.BusInfo();
		while(rs.next()) {
			if(busNo.equalsIgnoreCase(rs.getString(1))) {
				totalSeats=rs.getInt(3);
			}
		}
		
		int seat=totalSeats-seatCount;
		
		return seat;
	}
}


