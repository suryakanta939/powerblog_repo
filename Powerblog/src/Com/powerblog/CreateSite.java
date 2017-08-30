package Com.powerblog;

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

public class CreateSite {
	WebDriver driver;
	String baseUrl="https://www.mycompanyadmin.com/";
	String Un="Y375";
	String Pwd="31029779";
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
	  driver.findElement(By.xpath("//h3[text()='My sites']")).click();
	  try{
	  if(driver.findElement(By.xpath("//td[h3[text()='mysite']]")).isDisplayed()){
		  driver.findElement(By.xpath("//td[h3[text()='mysite']]//a[text()='Delete']")).click();
		  driver.findElement(By.xpath("//input[@id='delete_network']")).click();
		  
		  WebElement dashboard=driver.findElement(By.xpath("//div[text()='Dashboard']"));
		   Actions act1=new Actions(driver);
		  act1.moveToElement(dashboard).perform();
		  driver.findElement(By.xpath("//a[text()='Home']")).click();
driver.findElement(By.xpath("//h3[contains(text(),'Create Site')]")).click();
		  
		  WebElement domains=driver.findElement(By.xpath("//select[@id='domain']"));
		  Select sel=new Select(domains);
		  sel.selectByVisibleText("blog.makeyourdestiny.com");
		  
		  WebDriverWait wait=new WebDriverWait(driver,10);
		  wait.until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//span[text()='Status: Propagated']"))));
		  String status=driver.findElement(By.xpath("//span[text()='Status: Propagated']")).getText();
		  if(status.contains("Propagated")){
			  driver.findElement(By.id("site_name")).sendKeys("mysite");
			  WebElement themeblog=driver.findElement(By.xpath("//select[@id='blog_template']"));
			  Select sel1=new Select(themeblog);
			  sel1.selectByVisibleText("Divi 3.0");
			  driver.findElement(By.id("add_network")).click();
		  }else{
			  System.out.println("the domian is not propagated");
		  }
	  }else{
		  driver.navigate().back();
	  }
	 } //catch
	  catch(Throwable t){
		 // Thread.sleep(1000);
		  driver.navigate().back();
		  driver.findElement(By.xpath("//h3[contains(text(),'Create Site')]")).click();
		  
		  WebElement domains=driver.findElement(By.xpath("//select[@id='domain']"));
		  Select sel=new Select(domains);
		  sel.selectByVisibleText("blog.makeyourdestiny.com");
		  
		  WebDriverWait wait=new WebDriverWait(driver,10);
		  wait.until(ExpectedConditions.visibilityOf( driver.findElement(By.xpath("//span[text()='Status: Propagated']"))));
		  String status=driver.findElement(By.xpath("//span[text()='Status: Propagated']")).getText();
		  if(status.contains("Propagated")){
			  driver.findElement(By.id("site_name")).sendKeys("mysite");
			  WebElement themeblog=driver.findElement(By.xpath("//select[@id='blog_template']"));
			  Select sel1=new Select(themeblog);
			  sel1.selectByVisibleText("Divi 3.0");
			  driver.findElement(By.id("add_network")).click();
		  }else{
			  System.out.println("the domian is not propagated");
		  }
		  
//		  WebElement dashboard=driver.findElement(By.xpath("//div[text()='Dashboard']"));
//		   Actions act1=new Actions(driver);
//		  act1.moveToElement(dashboard).perform();
//		  driver.findElement(By.xpath("//a[text()='Home']")).click();
//		  
//		  driver.findElement(By.xpath("//h3[text()='My sites']")).click();
//		  driver.findElement(By.xpath("//td[h3[text()='mysite']]//a[text()='Delete']")).click();
//		  driver.findElement(By.xpath("//input[@id='delete_network']")).click();
	  }
	 
  }
}
