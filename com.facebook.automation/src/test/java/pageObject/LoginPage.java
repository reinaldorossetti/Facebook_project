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
	String delay_w = functions.getConfig("delay_wait");
	int delay_wait = Integer.parseInt(delay_w);
	private String senha;
	protected String email;
	
	Map<String, String> map = ImmutableMap.of(
	    "email", "email",
	    "password", "#pass",
	    "button", "#loginbutton",
	    "menu", "//*[@id='fb-timeline-cover-name']"
	    );
	
	public  LoginPage login(String email) throws InterruptedException {
	
		System.out.println(email);
		WebElement inputBox = functions.find_element(By.id(map.get("email")), delay_wait);
		String textInsideInputBox = inputBox.getAttribute("value");
		

			if(textInsideInputBox.isEmpty())
			{
				loginContainer.login.sendKeys(email);
			}else{
				loginContainer.login.clear();
				loginContainer.login.sendKeys(email);
			}

	
		
		return new LoginPage(driver);
		
	}
	
	public LoginPage password(String senha) throws Throwable {

		senha = functions.verifiedPassword(senha);
			
		try{ 
			loginContainer.password.sendKeys(senha);
		}
		catch(Exception e){
			
				System.out.println("Error in login try again");
				functions.sendKeys(By.cssSelector(map.get("password")), delay_wait, senha);
			}	
		return new LoginPage(driver);
		
	}
	
	public LoginPage loginbutton() throws Throwable {

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
				functions.searchAndClick(By.cssSelector(map.get("button")), delay_wait);
			}	
		return new LoginPage(driver);
	}
	
	
	public LoginPage validate_login(String user_name) throws Exception {

		driver.switchTo().defaultContent();
		String user_name_site = null;
		System.out.println("Step getText User");
		
		try{
			user_name_site =  loginContainer.user_text_css.getText();
		}catch(Exception e){
			user_name_site = functions.refleshPage(By.xpath(map.get("menu")), delay_wait);;
		}
		
		System.out.println("user logado: " + user_name_site);
		Assert.assertEquals(user_name, user_name_site);
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
