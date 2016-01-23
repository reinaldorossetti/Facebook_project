package useful;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import pageObject.AbstractPage;
import pageObject.LandingPage;

public class Browsers extends AbstractPage {

	
	public Browsers(WebDriver driver) {
		super(driver);
	}


	public static WebDriver setBrowser(String Browser) throws IOException, InterruptedException{
		
		// Testes feitos no Firefox e Chrome
		
		if(Browser.equalsIgnoreCase("Chrome"))
		{	
			driver = new ChromeDriver();
			
		} else if (Browser.equalsIgnoreCase("Firefox"))
		{
			
			driver = new FirefoxDriver();
			
		} 

		else if (Browser.equalsIgnoreCase("IE"))
		{
			driver = new InternetExplorerDriver();
		}
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.navigateToWebSite();
		Thread.sleep(3000);
		return driver;
		
	}
	
		
	}