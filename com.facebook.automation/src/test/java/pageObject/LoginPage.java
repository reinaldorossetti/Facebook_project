package pageObject;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import useful.functions;

public class LoginPage extends AbstractPage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	public final HomePage loginContainer = PageFactory.initElements(driver, HomePage.class);

	// delay set on properties file.
	public functions functions = new functions();
	String delay_s = functions.getConfig("sleep");
	String delay_w = functions.getConfig("delay_wait");
	int delay_sleep = Integer.parseInt(delay_s);
	int delay_wait = Integer.parseInt(delay_w);
	private String senha;
	protected String email;

	
	public  LoginPage login(String email) throws InterruptedException {
	
		System.out.println(email);
		WebElement inputBox = driver.findElement(By.id("email"));
		String textInsideInputBox = inputBox.getAttribute("value");
		
		try{ 
			// Check whether input field is blank
			// bug in IE.
			if(textInsideInputBox.isEmpty())
			{
				loginContainer.login.sendKeys(email);
				Thread.sleep(delay_sleep);
			}

		}
		catch(Exception e){
			System.out.println("Error Email try again");
			functions.sendKeys(By.cssSelector("#email"), delay_wait, email);
		}	
		
		return new LoginPage(driver);
		
	}
	
	public LoginPage password(String senha) throws Throwable {

		senha = functions.verifiedPassword(senha);
			
		try{ 
			loginContainer.password.sendKeys(senha);
			Thread.sleep(delay_sleep);
		}
		catch(Exception e){
			
				System.out.println("Error in login try again");
				Thread.sleep(delay_sleep);
				functions.sendKeys(By.cssSelector("#pass"), delay_wait, senha);
			}	
		return new LoginPage(driver);
		
	}
	
	public LoginPage loginbutton() throws Throwable {

		Thread.sleep(delay_sleep);
	
		try{ 
			String text_login = loginContainer.login.getText();
			senha = loginContainer.password.getText();
			if(text_login.isEmpty() || senha.isEmpty()){
				
				System.out.println("error when filling out the login and password");
				
			}else{
				
				loginContainer.loginbutton.click();
			}
			
		}
		catch(Exception e){
			
				System.out.println("error in loginbutton");
				functions.searchAndClick(By.cssSelector("#loginbutton"), delay_wait);
			}	
		return new LoginPage(driver);
		
	}
	
	
	public LoginPage validate_login(String user_name) throws Exception {

		driver.switchTo().defaultContent();
		Thread.sleep(delay_sleep);
		System.out.println("validate_login");
		String user_name_site = null;
		System.out.println("Step getText User");
		
		
		try{
			user_name_site =  loginContainer.user_text_css.getText();
			
		}catch(Exception e){
			
			Thread.sleep(delay_sleep);
			user_name_site = functions.refleshPage(By.xpath(".//*[@id='fb-timeline-cover-name']"), delay_wait);;
		}
		
		System.out.println("user logado: " + user_name_site);
		Assert.assertEquals(user_name, user_name_site);
		Thread.sleep(delay_sleep);
		return new LoginPage(driver);
		
	}
	
	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static class StringUtils {

	    // Checks the string is null or empty or only have blanks
	    public static boolean isNullOrBlank(String s) {
	        return (s == null || s.trim().equals(""));
	    }

	    // Checks the string is null or empty
	    public static boolean isNullOrEmpty(String s) {
	        return (s == null || s.equals(""));
	    }
	}
}
