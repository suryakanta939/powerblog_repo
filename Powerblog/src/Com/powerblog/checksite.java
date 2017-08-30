package Com.powerblog;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class checksite {
	WebDriver driver;
	String baseUrl="https://www.mycompanyadmin.com/";
	String Un="Y375";
	String Pwd="31029779";
	int propagate;
	int notpropagate;
  @Test
  public void f() throws InterruptedException
  {
	  driver=new FirefoxDriver();
	  driver.get(baseUrl);
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  driver.findElement(By.id("name")).sendKeys(Un);
	  driver.findElement(By.id("pass")).sendKeys(Pwd);
	  driver.findElement(By.id("Submit")).click();
	  //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  WebElement promote=driver.findElement(By.xpath("//a[@id='link5']"));
	  Actions act=new Actions(driver);
	  act.moveToElement(promote).build().perform();

	  driver.findElement(By.xpath("html/body/span/div[17]/div/div/div[4]")).click();
	  driver.findElement(By.xpath("//input[@type='submit']")).click();
	  Alert alertOK = driver.switchTo().alert();
	  alertOK.accept();
	  Thread.sleep(1000);
	  alertOK.accept();
	  //click on create site & check the status of the domain
	  
	  driver.findElement(By.xpath("//h3[contains(text(),'Create Site')]")).click();
	  
	  WebElement domains=driver.findElement(By.xpath("//select[@id='domain']"));
	  //read all the domain name
	  Select sel=new Select(domains);
	     List<WebElement> names=sel.getOptions();
	     int total=names.size()-1;
	     System.out.println("the total no of domain "+total);
	     for(int i=1;i<names.size();i++){
	    	 String domain=names.get(i).getText();
	    	 
	    	 //select the domain
	    	 sel.selectByIndex(i);
	    	 
	    	 //wait for the domian status
	    	 try{
	    	 WebDriverWait wait=new WebDriverWait(driver,10);
	    	 
	    	 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Status: Propagated']"))));
	    	 String msg=driver.findElement(By.xpath("//span[text()='Status: Propagated']")).getText();
	    	 System.out.println("The status of the "+domain+"is "+msg);
	    	 if(msg.contains("Propagated")){
	    		 propagate++;
	    	 }
	     }
	    	 catch(Throwable t){
	    		 WebDriverWait wait=new WebDriverWait(driver,10);
		    	 
		    	 wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Status: Not Propagated']"))));
		    	 String msg=driver.findElement(By.xpath("//span[text()='Status: Not Propagated']")).getText();
		    	 
		    	 if(msg.equals("Status: Not Propagated")){
		    		 System.out.println("The status of the "+domain+"is "+msg);
		    		 notpropagate++;
		    	 }
	    	 }
	     }
	  System.out.println(total-propagate++ +"is the total no of invalid domains");
	  
  }
}
