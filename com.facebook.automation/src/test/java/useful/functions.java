package useful;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.AbstractPage;


public class functions extends AbstractPage{
	
	public functions() {
		super(driver);
	}
	
	// delay set on properties file.
	public String delay_s = getConfig("sleep");
	public int delay_sleep = Integer.parseInt(delay_s);
	
	public boolean sendKeys(By by, int seconds, String text) throws InterruptedException{
		Boolean test = false;
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		
		try{
			
			WebElement myDynamicElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			myDynamicElement.findElement(by).sendKeys(text);
			test = true;
			
		}catch (Exception e){
			
			System.out.println("Element not found!"); 
		
		}
		return test;
	}
	
public String getConfig(String name)  {
		
		InputStream inputStream = null;
		String value = null;
		try {
			Properties prop = new Properties();
			String propFileName = "conf.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			// get the property value and print it out
			value = prop.getProperty(name);
 			//System.out.println(value);
			return value;

		} catch (Exception e) {
			
			System.out.println("Exception: " + e);
		
		} 
		
		return value;
		
	}
	
	public void setPropertiesKey(String publicKey ) throws IOException, URISyntaxException {
		
		InputStream inputStream = null;
		Properties prop = new Properties();
		String propFileName = "conf.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
				inputStream.close();
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			prop.setProperty("publicKey", publicKey);
		URL url = getClass().getClassLoader().getResource(propFileName);
		prop.store(new FileOutputStream(new File(url.toURI())), null);

	}
	
	public static String alertConfirm() {


		String alertText = "";
		try {
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
			alert.dismiss();
			// or accept as below
			//alert.accept();

		} catch (NoAlertPresentException nape) {
			// nothing to do, because we only want to close it when pop up
			System.out.println("Alert not found!");

		}
		return alertText;
		
	}
	public String getText(By locator, int seconds) {
		
		String product_text = "Not Found";
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		WebElement search = null;
		try{
			
			search = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
			if (search.isEnabled()){
				product_text = search.getText();
		
			};
			
		}catch(Exception e){
			System.out.println("\nElement not found! " + e);
		}
		return product_text;
	}
	public String verifiedPassword(String senha) {

		if (StringUtils.isNullOrBlank(senha)){
			System.out.println("senha em branco: "+ senha);
			JPasswordField pf = new JPasswordField();
			int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (okCxl == JOptionPane.OK_OPTION) {
			  senha = new String(pf.getPassword());
			}
	}
		return senha;
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
	
	public boolean searchAndClick(By locator, int seconds) {
		
		boolean Product = false;
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		WebElement search = null;

		try{	
			search = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
			
			//click one element javascript
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", search);
			Product = true;

		}catch(Exception e){
			System.out.println("Element not found");
		}
		return Product;
	}
	public String refleshPage(By locator, Integer seconds) throws InterruptedException{
		
		String text = "Element not found";
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		
		try{
			driver.navigate().refresh();
			Thread.sleep(delay_sleep);
			driver.switchTo().alert().accept();
			text = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
			
		}catch (Exception e){
			
			System.out.println("Element not found, try again!"); 
			driver.navigate().refresh();
			Thread.sleep(delay_sleep);
			alertConfirm();
			text = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
		
		}
		return text;
	}
}
