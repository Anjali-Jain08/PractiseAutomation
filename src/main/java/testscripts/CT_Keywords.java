package testscripts;

import java.io.IOException;

import CT_testcases.FlightBookingTest;
import CT_testcases.HotelBookingTest;
import CT_testcases.SignInTest;
import jxl.JXLException;
import jxl.read.biff.BiffException;

//Stores all the keyword of the Testcases
public class CT_Keywords extends CT_DriverScript {


	public static String shouldThrowAnErrorIfSignInDetailsAreMissing() throws InterruptedException,
	IOException, BiffException{

		System.out.println("Reached SignIn method");
		
		return SignInTest.shouldThrowAnError();
	}

	public static String testThatResultsAppearForAOneWayJourney() throws IOException, InterruptedException, JXLException {

		System.out.println("Reached flight booking method");
		
		return FlightBookingTest.aOneWayJourney();
	}

	public static String shouldBeAbleToSearchForHotels() throws InterruptedException, BiffException, IOException, JXLException {

		System.out.println("Reached hotel booking method");
		
		return HotelBookingTest.searchForHotels();
	}

}
