package busClasses;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Reservation {
	static Scanner sc=new Scanner(System.in);
	
	public static void ticketGenerator() throws SQLException {
		while(true) {
			DataCon.displayBuses();
			System.out.println("======== Enter 1 To Book Or 2 To Exit !!! ========");
			int answer=sc.nextInt();
			if(answer==1) {
				Booking book=new Booking();
				book.userDetails();
				
				if(book.isEmptyBooking()) {
					DataCon.addBooking(book.busNo,book.name,book.date ,book.seats,book.startPoint, book.destination);
				}
				else if(book.isAvailable()) {
					DataCon.addBooking(book.busNo,book.name,book.date ,book.seats,book.startPoint, book.destination);
				}
				else {
					int availableSeats=book.seatsAvailable();
					System.out.println("Sorry !!! Only "+ availableSeats +" Seats Available ...");
					System.out.println("Try Different Buses or Date... Thank You");
				}
			}
			else if(answer>=2) {
				
				break;
				}
		}
}
	
	public static void userInformation() throws SQLException{
		
		System.out.println("Enter 1 To View Tickets or 2 To Cancel Tickets...");
		int userInput=sc.nextInt();
		java.util.Date date;
		System.out.println("==============================================================");
		System.out.println("Enter Your Name :");
		String name=sc.next();
		System.out.println("==============================================================");
		System.out.println("Enter Your Bus Number :");
		String busNo=sc.next();
		System.out.println("==============================================================");
		while(true) {
			System.out.println("Enter the Date : ");
			String date_str=sc.next();
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(date_str);
				System.out.println("==============================================================");
				break;
			} catch (ParseException e) {
				System.err.println(e+"Date Should Be In DD/MM/YYYY Format");
				System.out.println("==============================================================");
			}
			}
		
		
		if(userInput==1) {
			System.out.println("======== Ticket Details ========");
			UserDetails.displayTickets(name,busNo,date);
			System.out.println("==============================================================");
		}
		else if(userInput==2){
			System.out.println("Do You want To Cancel Your Tickets ...\n 1.Yes 2.No ");
			int choice=sc.nextInt();
			
			if(choice==1) {
				UserDetails.cancelTickets(name,busNo,date);
				System.out.println("Tickets Cancelled SuccessFully..");
				System.out.println("==============================================================");
			}
		}
	
	}
}
