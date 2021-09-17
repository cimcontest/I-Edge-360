package test_captcha;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class test_captcha {

	public static void main(String[] args) throws IOException, InterruptedException, TesseractException 
	{
		
		System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://199.199.51.186");
		try {
			
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.name("username")).sendKeys("iEdgeAdmin");
		driver.findElement(By.name("paswrd")).sendKeys("iEA@12345");
		
		
		WebElement element = driver.findElement(By.xpath("//*[@name='dnt_CaptchaImg']"));
		String str= element.getText();
		File src= element.getScreenshotAs(OutputType.FILE);
		System.out.println("The string is:->" +str);
		
		String path = "C:\\Users\\krishnap\\eclipse-workspace\\test_cap\\captchaimages\\captcha.png";
		FileHandler.copy(src, new File(path));
		Thread.sleep(1000);
		
		ITesseract img = new Tesseract();
		
		String str1 = img.doOCR(new File(path));
		System.out.println("Img OTR done!!");
		System.out.println(str1);
		int c1 = Integer.parseInt(str1.substring(0, 3));
		int c2 = Integer.parseInt(str1.substring(6, 7));
		
		//Integer i1 = Integer.valueOf(c1);
		//Integer i2 = Integer.valueOf(c2);
		
		//Thread.sleep(2000);
		
		int i = c1+c2;
		System.out.println(i);
		String val = String.valueOf(i);
		
		
		WebElement element1 = driver.findElement(By.xpath("//*[@id='DNT_CaptchaInputText']"));
		element1.sendKeys(val);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
		
		//driver.close();
	} catch(Exception e)
		{
		//System.out.println("Exception");
		System.out.println(e.getMessage());
		}
		
	}
	public void Click()
	{
		
	}
}
