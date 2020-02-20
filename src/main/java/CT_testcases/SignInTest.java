package CT_testcases;


import testscripts.CTLibrary;
import testscripts.CT_DriverScript;


import org.openqa.selenium.By;

//This testcase belongs to SignIn when the data is missing
public class SignInTest extends CT_DriverScript {

	static By LocatorLinkText = By.id("userAccountLink");
	static By LocatorSignIn = By.id("SignIn");
	static By LocatorSignInButton = By.id("signInButton") ;

	public static String shouldThrowAnError() throws InterruptedException {

		//System.out.println("Reached SignInTest class");
		return CTLibrary.navigateAndSignIn();
		
	}

}
