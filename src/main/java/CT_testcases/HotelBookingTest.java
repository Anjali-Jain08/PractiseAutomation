package CT_testcases;

import java.io.IOException;


import jxl.JXLException;
import jxl.read.biff.BiffException;
import testscripts.CTLibrary;
import testscripts.CT_DriverScript;

//This testcase belongs to the Hotel Search.
public class HotelBookingTest extends CT_DriverScript {

public static String searchForHotels() throws InterruptedException, BiffException, IOException, JXLException {

		//System.out.println("Reached HotelBookingTest class");
		return CTLibrary.navigateAndHotelBooking();
}	

}






















/*@FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        //setDriverPath();

        driver.get("https://www.cleartrip.com/");
        hotelLink.click();

        localityTextBox.sendKeys("Indiranagar, Bangalore");

        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();

        driver.quit();

    }*/


