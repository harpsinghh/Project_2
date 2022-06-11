package firstpackage;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Window_handling {

	public static WebDriver driver;

	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/window-handles-practice.html");
		
		Actions a = new Actions(driver);
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		
		//2 methods to handle windows
		//getWindowHandle() and getWindowHandles
		
		String parentWindowHandle = driver.getWindowHandle();
		System.out.println("parent window is: " + parentWindowHandle );

		driver.findElement(By.xpath("//button[@id = 'newWindowBtn']")).click();
		
		Set<String> handles = driver.getWindowHandles();
		
		for(String handle:handles) {
			System.out.println(handle);
		
		
			if(!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
				driver.manage().window().maximize();
			driver.findElement(By.id("firstName")).sendKeys("Bineet");
			driver.findElement(By.id("lastName")).sendKeys("Randhawa");
			driver.close();
			
			//even though the browser closed, the control will not automatically come to parent window

			
			}
		}
		
		
		driver.switchTo().window(parentWindowHandle);
		//Actions a1 = new Actions(driver);
		a.sendKeys(Keys.PAGE_UP).build().perform();
		
		boolean check = driver.findElement(By.id("name")).isEnabled();
		
		if(check == true) {
			driver.findElement(By.id("name")).sendKeys("bineet@gmail.com");	
			
		}
		else {
			System.out.println("error");
		}
		
		
		//continue doing by switching to new tab and so on
		
		
	}

}
