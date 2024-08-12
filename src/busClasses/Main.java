package busClasses;
import java.util.*;

import java.sql.SQLException;



public class Main {
	public static void main(String[] args) throws SQLException {
		Scanner sc=new Scanner(System.in);
		while(true) {
		System.out.println(" 1.Book Ticket  \n 2.Show My Ticket \n 3.Exit");
		int input=sc.nextInt();
		System.out.println("==============================================================");
		if(input==1) {
		Reservation.ticketGenerator();
		}
		else if(input==2) {
		Reservation.userInformation();
		}
		else {
			System.out.println("Exited");
			break;
		}
		
	}
		System.out.println("==============================================================");
	sc.close();	
	}

}
