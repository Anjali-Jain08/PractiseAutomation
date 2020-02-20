package testscripts;


import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CT_FunctionLibrary extends CT_DriverScript {

	//To check if the element is present or not
	public static boolean isElementPresent(By Locator) {

		waitUntil(Locator);
		highlightElement(driver, Locator);

		try {
			driver.findElement(Locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	//To click on the element
	public static void click(By Locator) {

		waitUntil(Locator);

		highlightElement(driver, Locator);

		driver.findElement(Locator).click();

	}

	//Highlight the element on which action is to be performed
	public static void highlightElement(WebDriver driver, By Locator) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1])", driver.findElement(Locator), "background-color: yellow; outline: 1px solid rgb(136, 255, 136)");
	}

	//Wait for the element to be loaded on the webpage
	public static void waitUntil(By Locator) {

		WebDriverWait wait = new WebDriverWait(driver, 45);
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
	}

	//Enter value to the element
	public static void input(By Locator, String Value) {

		waitUntil(Locator);
		highlightElement(driver, Locator);

		driver.findElement(Locator).sendKeys(Value);
	}

	//Switch to iframes from the main window
	public static void switchFrames(String framename) {


		driver.switchTo().frame(framename);
	}
	
	//Check if the string values are same
	public static boolean assertTrue(By Locator, String expectedError) {

		//waitUntil(Locator);
		//highlightElement(driver, Locator);
		
		try {

			String actualError = driver.findElement(Locator).getText();
			System.out.println("Actual Error msg is : " + actualError);
			Assert.assertTrue(actualError.contains(expectedError));
			return true;
			
		}catch(Exception asserTrue)
		{

			return false;
		}

	}
	//Click the radio button
	public static void radio(By Locator) {


		waitUntil(Locator);
		highlightElement(driver, Locator);

		driver.findElement(Locator).click();
	}

	//Clear the field (if there is any data) before doing any action on the elements
	public static void clearField(By Locator) {


		waitUntil(Locator);
		highlightElement(driver, Locator);

		driver.findElement(Locator).clear();
	}

	//Select from the list according to the index number of the values
	public static void selectValueByIndex(By Locator, int index) 
	{

		try 
		{

			// Wait for drop-down element to load on the page

			waitUntil(Locator);

			// Highlight the drop-down
			highlightElement(driver, Locator);

			// Locate drop-down field
			Select select = new Select(driver.findElement(Locator));

			// Select value from drop-down
			select.selectByIndex(index);

		} 

		catch(Throwable selectValueException) 
		{

			System.out.println(selectValueException.getMessage());
			selectValueException.printStackTrace();
		}
	}

	//Check any condition which returns in boolean
	public static boolean assertTrueboolean(By Locator) {

		//waitUntil(Locator);
		highlightElement(driver, Locator);

		try {
			Assert.assertTrue(isElementPresent(Locator));

			return true;
		} catch (NoSuchElementException e) {

			return false;
		}
	}
	//Select from the list according to the values
	public static void selectByValue(By Locator, String value) 
	{

		try 
		{

			// Wait for drop-down element to load on the page
			System.out.println("wait time is started");
			waitUntil(Locator);

			// Highlight the drop-down
			highlightElement(driver, Locator);

			// Locate drop-down field
			Select select = new Select(driver.findElement(Locator));

			// Select value from drop-down
			select.selectByValue(value);
		} 

		catch(Throwable selectValueException) 
		{

			System.out.println(selectValueException.getMessage());
			selectValueException.printStackTrace();
		}
	}

	//For pressing Enter from the keyboard
	public static void sendKeys(By Locator) {

		waitUntil(Locator);
		highlightElement(driver, Locator);

		driver.findElement(Locator).sendKeys(Keys.ENTER);
	}

	//Select from the list according to the visible text for the Values
	public static void selectValueByText(By Locator, String text) {

		waitUntil(Locator);
		highlightElement(driver, Locator);

		Select option = new Select(driver.findElement(Locator));
		option.selectByVisibleText(text);
	}

	//To close the window which got opened while automating the testing site
	public static void windowClose() {

		driver.close();
	}
}


