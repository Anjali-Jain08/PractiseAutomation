package CT_testcases;

import java.io.IOException;

import jxl.JXLException;
import jxl.read.biff.BiffException;
import testscripts.CTLibrary;
import testscripts.CT_DriverScript;

//This testcase belongs to the flight booking for One way journey.
public class FlightBookingTest extends CT_DriverScript {



	public static String aOneWayJourney() throws InterruptedException, BiffException, IOException, JXLException {

		
		return CTLibrary.navigatAndFlightBooking();
	
		}
}

