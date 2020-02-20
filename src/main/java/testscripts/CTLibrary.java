package testscripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.interactions.MoveToOffsetAction;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import jxl.JXLException;
import jxl.read.biff.BiffException;

public class CTLibrary extends  CT_DriverScript {
	
	static WebDriver wbdv;
	
	//To store testdata values for flight booking
	static String From = null;
	static String To = null;
	static String flight_Check_In_Date = null;
	
	//To store testdata values for hotel searching
	static String Locality =  null;
	static String hotel_Check_In_Date =  null;
	static String hotel_Check_Out_Date =  null;
	
	//Locators for SignIn and check for error message when data is missing
	static By LocatorLinkText = By.id("userAccountLink");
	static By LocatorSignIn = By.id("SignIn");
	static By LocatorSignInButton = By.id("signInButton") ;
	static By LocatorUsername = By.id("email") ;
	static By LocatorPassword = By.id("password") ;
	static By LocatorError = By.id("errors1");
	static String errorToBeShown = "There were errors in your submission";
	static String expectedError= null;
	
	//Locators for Flight Booking
	static By LocatorOneWay = By.id("OneWay");
	static By LocatorFromTag = By.id("FromTag");
	static By LocatorToTag = By.id("ToTag");
	static By LocatorCalendar = By.id("DepartDate");
	static By LocatorDepartOn = By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[6]");
	static By LocatorSearch = By.id("SearchBtn");
	static By LocatorSearchResult = By.className("searchSummary");
	static By LocatorAutoCompleteTag = By.xpath("//*[@id='ui-id-1']//descendant::a[1]");
	static By LocatorAutoCompleteToTag = By.xpath("//*[@id='ui-id-2']//descendant::a[1]");
	
	//Locators for Hotel Searching
	static By LocatorHotelLinks = By.xpath("//*[@class='hotelApp ']");
	static By LocatorPlaceSearch = By.id("Tags");
	static By LocatorTravellers = By.id("travellersOnhome");
	static By LocatorHotelsSearch = By.id("SearchHotelsButton");
	static By LocatorCheckInDate = By.id("CheckInDate");
	static By LocatorCheckIndatePick = By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[5]/td[5]");
	static By LocatorCheckOutDate = By.id("CheckOutDate");
	static By LocatorCheckOutdatePick = By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[2]/td[3]");
	static By LocatorAutoCompleteWhereTag =  By.xpath("//*[@id='ui-id-1']//descendant::a[1]");
	
	
	
//To navigate to the testing URL with the test browser
public static void navigate() throws InterruptedException {
        
	if(wbdv==null) {

		if(Config.getProperty("test_browser").equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\drivers\\ChromeDriver\\chromedriver.exe");
			wbdv = new ChromeDriver();
		}
		else if(Config.getProperty("test_browser").equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\drivers\\geckodriver\\geckodriver.exe");
			wbdv = new FirefoxDriver();
		}

	}
	
	    driver = new EventFiringWebDriver(wbdv);
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to(Config.getProperty("test_URL_site"));
		driver.manage().window().maximize();
}

//To check if signIn error shows when data is missing
public static void SignInError() {
	
	//Click on "Your trips"
	CT_FunctionLibrary.click(LocatorLinkText);
	System.out.println("Clicked on the text your trip");
	
	//Click on Sign In tab
	CT_FunctionLibrary.click(LocatorSignIn);
	System.out.println("Clicked on sign in");
	
	//Swith to small iframe
	CT_FunctionLibrary.switchFrames("modal_window");
	CT_FunctionLibrary.waitUntil(LocatorSignInButton);
	//Click on  Sign button without entering any data
	CT_FunctionLibrary.click(LocatorSignInButton);
	System.out.println("Error message shown");
	
    //Check if the error message shown is correct
	expectedError = errorToBeShown;
	System.out.println("Expected error is  : " + expectedError);
	CT_FunctionLibrary.assertTrue(LocatorError, expectedError);
	
}

//Flight Booking method
public static void flightBooking(int data_row_num) throws BiffException, IOException, JXLException {
	
	//Get cell testdata for input
	From = testdata.getCellData("FlightBooking", "From", data_row_num);
	To = testdata.getCellData("FlightBooking","To",data_row_num);
	
	//To select One Way Journey
	System.out.println("Select oneway ticket");
	CT_FunctionLibrary.radio(LocatorOneWay);

	//To enter the Source City
	System.out.println("Enter source city");
	CT_FunctionLibrary.clearField(LocatorFromTag);
	CT_FunctionLibrary.input(LocatorFromTag, From);
	CT_FunctionLibrary.waitUntil(LocatorFromTag);
	CT_FunctionLibrary.click(LocatorAutoCompleteTag);
	//CT_FunctionLibrary.selectValueByIndex(LocatorAutoCompleteTag, 0);

	//To enter the Destination City
	System.out.println("Enter destination city");
	CT_FunctionLibrary.clearField(LocatorToTag);
	CT_FunctionLibrary.input(LocatorToTag, To);
	CT_FunctionLibrary.waitUntil(LocatorAutoCompleteToTag);
	CT_FunctionLibrary.click(LocatorAutoCompleteToTag);
	//CT_FunctionLibrary.selectValueByIndex(LocatorAutoCompleteToTag, 0);

	//To Enter the Date Of Journey
	System.out.println("Enter D.O.J");
	CT_FunctionLibrary.clearField(LocatorCalendar);
	CT_FunctionLibrary.click(LocatorDepartOn);

	//To search for a flight according to the entered data
	System.out.println("Clicked on Search button");
	CT_FunctionLibrary.click(LocatorSearch);

	//To check if the search result is showing
	System.out.println("Flight Booking search's result is shown");
	CT_FunctionLibrary.isElementPresent(LocatorSearchResult);
	CT_FunctionLibrary.assertTrueboolean(LocatorSearchResult);

}

//Hotel Booking method
public static void hotelBooking(int data_row_num) throws BiffException, IOException, JXLException {

	//Get cell testdata for input
	Locality = testdata.getCellData("HotelBooking","Locality", data_row_num);
	
	//Move to Hotel Search page
	System.out.println("Clicking for hotel's link");
	CT_FunctionLibrary.click(LocatorHotelLinks);
	
    //To enter location for Hotel Search
	CT_FunctionLibrary.waitUntil(LocatorPlaceSearch);
    System.out.println("Enter location");
	CT_FunctionLibrary.input(LocatorPlaceSearch, Locality);
	CT_FunctionLibrary.waitUntil(LocatorAutoCompleteWhereTag);
	CT_FunctionLibrary.click(LocatorAutoCompleteWhereTag);

	//To enter check in date of the Hotel
	/*CT_FunctionLibrary.waitUntil(LocatorCheckInDate);
	CT_FunctionLibrary.click(LocatorCheckInDate);*/
	System.out.println("Enter check-in date");
	CT_FunctionLibrary.waitUntil(LocatorCheckIndatePick);
	CT_FunctionLibrary.click(LocatorCheckIndatePick);
	
	
	//****To enter check out date of the Hotel
    //CT_FunctionLibrary.waitUntil(LocatorCheckOutdatePick);
	System.out.println("Enter check-out date");
	//CT_FunctionLibrary.click(LocatorCheckOutDate);
	CT_FunctionLibrary.waitUntil(LocatorCheckOutdatePick);
	CT_FunctionLibrary.click(LocatorCheckOutdatePick);
	
	
	//To select number of people travelling
	CT_FunctionLibrary.waitUntil(LocatorTravellers);
	CT_FunctionLibrary.click(LocatorTravellers);
    System.out.println("Select no. of travellers");
	CT_FunctionLibrary.selectValueByText(LocatorTravellers, "1 room, 1 adult");
	
	//To click on Search button, so that searching of Hotel starts according to the details entered
	System.out.println("Search button is clicked");
	CT_FunctionLibrary.waitUntil(LocatorHotelsSearch);
	CT_FunctionLibrary.click(LocatorHotelsSearch);
	System.out.println("Hotel search result is shown");
}

public static String navigateAndSignIn() throws InterruptedException {
	
	CTLibrary.navigate();
	
	CTLibrary.SignInError();
	
	return "SignIn error shown Successfully";
}

public static String navigatAndFlightBooking() throws InterruptedException, BiffException, IOException, JXLException {
	
	CTLibrary.navigate();
	
	CTLibrary.flightBooking(1);
	
	return "Hotel Booking Done Successfully";
	
}

public static String navigateAndHotelBooking() throws InterruptedException, BiffException, IOException, JXLException {
	
	CTLibrary.navigate();
	
	CTLibrary.hotelBooking(1);
	
	return "Hotel Booking Done Successfully";
}
}



	
	
	
			
		


