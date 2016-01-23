package pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends AbstractPage {


		public HomePage(WebDriver driver) {
			super(driver);
		}
		
		// **********************************************************************************************************************	
		//Login elements. 
		// **********************************************************************************************************************
		
		@FindBy(how=How.ID, using="email")
		@CacheLookup
		public WebElement login;
		
		@FindBy(how=How.ID, using="pass")
		@CacheLookup
		public WebElement password;
		
		@FindBy(how=How.ID, using="loginbutton")
		@CacheLookup
		public WebElement loginbutton;		
		
		// **********************************************************************************************************************
		// POST elements.
		// **********************************************************************************************************************

		@FindBy(how=How.XPATH, using="//*[@id=\"navItem_100002054849465\"]/a/div/span")
		@CacheLookup
		public WebElement user_text_xpath;
		

		// **********************************************************************************************************************
		// Default elements.
		// **********************************************************************************************************************

		@FindBy(how=How.CSS, using="div.linkWrap.noCount>span")
		@CacheLookup
		public WebElement user_text_css;
		
		
		// **********************************************************************************************************************
		// TimeLine elements.
		// **********************************************************************************************************************
		
		@FindBy(how=How.CSS, using="#fb-timeline-cover-name")
		@CacheLookup
		public WebElement user_name_css;
		
		@FindBy(how=How.NAME, using="xhpc_message_text")
		@CacheLookup
		public WebElement fieldText;		

		@FindBy(how=How.CSS, using="._1mf7._4jy0._4jy3._4jy1._51sy.selected._42ft")
		@CacheLookup
		public WebElement buttonPost;
		
		@FindBy(how=How.XPATH, using="(.//a[contains(@class,'UFILikeLink')])[1]")
		@CacheLookup
		public WebElement likePost;		
		

	}
