package pageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import useful.functions;

public class LogOutPage extends AbstractPage {
	
	public LogOutPage(WebDriver driver) {
		super(driver);
	}

	public final HomePage loginContainer = PageFactory.initElements(driver, HomePage.class);

	// delay set on properties file.
	public functions functions = new functions();
	public String delay_w = functions.getConfig("delay_wait");
	public int delay_wait = Integer.parseInt(delay_w);
	
	Map<String, String> map = ImmutableMap.of(
	    "navigation_label_id", "userNavigationLabel",
	    "navigation_label_css", "#userNavigationLabel",
	    "submenu", ".navSubmenu.__MenuItem:nth-last-of-type(5)",
	    "button", "#loginbutton",
	    "menu", "fb-timeline-cover-name"
);

	public LogOutPage logout() throws InterruptedException {
	
		//driver.switchTo().frame("iframeLogin");
		System.out.println("Doing LogOut!");

		try{ 
			// Open menu Log Out
			functions.searchAndClick(By.cssSelector(map.get("navigation_label_id")), delay_wait);
			// select five option of submenu, starting of the end. Because another language should be different of log out/sair. 
			functions.searchAndClick(By.cssSelector(map.get("submenu")), delay_wait);
			
		}
		catch(Exception e){
			System.out.println("error not found element, try again!");
			functions.searchAndClick(By.cssSelector(map.get("navigation_label_css")), delay_wait);
			// select five option of submenu, starting of the end. Because another language should be different of log out/sair. 
			functions.searchAndClick(By.cssSelector(map.get("submenu")), delay_wait);
		}	
		
		return new LogOutPage(driver);
		
	}
	
	public LogOutPage validate_logOut(String text) throws Throwable {

		driver.switchTo().defaultContent();
		String product_title =  driver.getTitle();
		System.out.println("title: " +product_title);
		String text_result = (text);
		Boolean Test;
		
		if (product_title.contains(text_result)){
			System.out.println("Result OK: " + text_result);
			Test = true;
			
		}else{
			System.out.println("\nResult Failed - Expected:" + product_title + "\nResult:" + text_result);
	        System.out.print("\n ******* Assert Falhou!!! ********\n");
	        Test = false;
		}
		Assert.assertTrue(Test);
		return new LogOutPage(driver);
		
	}
}
