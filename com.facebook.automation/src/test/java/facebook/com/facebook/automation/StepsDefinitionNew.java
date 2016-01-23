package facebook.com.facebook.automation;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.LandingPage;
import pageObject.LogOutPage;
import pageObject.LoginPage;
import useful.Browsers;
import java.io.IOException;


public class StepsDefinitionNew {
	
	LandingPage landingPage;
	LoginPage loginPage;
	LogOutPage logOutPage;
	WebDriver driver;
	
	@Given("^the \"([^\"]*)\" and navigate to the web site www\\.facebook\\.com\\.br$")
	public void navigate_web_site(String browser) throws Throwable {
		// Types of browsers Chrome, Firefox and IE, set in feature.
		driver = (WebDriver) Browsers.setBrowser(browser);
	}

	@Given("^Make the \"([^\"]*)\" and \"([^\"]*)\"$")
	public void Login(String email, String password) throws Throwable {
		
		// Doing Login
		loginPage = new LoginPage(driver);
		loginPage.login(email).
				password(password).loginbutton();
	}

	@When("^Validate the \"([^\"]*)\" in Main menu$")
	public void validate_Main_menu(String user_name) throws Throwable {
		// Validate the Login
		loginPage = new LoginPage(driver);
		loginPage.validate_login(user_name);
	}

	@Then("^Make the logout on facebook and validate the text \"([^\"]*)\"\\.$")
	public void logoutAndValidate(String text) throws Throwable {
		
		logOutPage = new LogOutPage(driver);
		logOutPage.logout().validate_logOut(text);
		
	}
	
    @After
    public void tearDown(Scenario scenario) throws IOException {
	  
		try{
		    // if the scenario fails, it will take a picture.
	        if (scenario.isFailed()) {
	                final byte[] screenshot = ((TakesScreenshot) driver)
	                        .getScreenshotAs(OutputType.BYTES);
	                scenario.embed(screenshot, "image/png");
		            }
	 
		}finally {
		
			/// close the browser.
			landingPage = new LandingPage(driver);
			landingPage.closeDriver();
	  	}
  
  }
}