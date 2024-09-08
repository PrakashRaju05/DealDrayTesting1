package com.website.automation;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestData {
	public static void main (String args[]) throws Exception {
		ChromeDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://demo.dealsdray.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@name='username']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
//	WebElement ordersBox=	driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/button[1]/span[1]")).click();
        WebElement ordersBox = driver.findElement(By.xpath("//h3[@class=' MuiBox-root css-psy1y7' and text()='54']"));
	ordersBox.click();
	driver.findElement(By.xpath("//button[text()='Add Bulk Orders']")).click();
//	Thread.sleep(5000);
	driver.findElement(By.xpath("//input[@class='MuiOutlinedInput-input MuiInputBase-input MuiInputBase-inputSizeSmall css-1imb3v5']")).sendKeys("C:\\Users\\Prakash\\Downloads\\demo-data.xlsx");
//	driver.findElement(By.xpath("//input[@id='mui-7']")).click();
	driver.findElement(By.xpath("//button[text()='Import']")).click();
	driver.findElement(By.xpath("//button[text()='Validate Data']")).click();
	Thread.sleep(5000);
//	driver.wait(5000);
	driver.switchTo().alert().accept();
	//FILE
    File sourcefile=driver.getScreenshotAs(OutputType.FILE); 
	File destfile=new File("./Screenshots/img1.jpg");
	FileUtils.copyFile(sourcefile,destfile);
	System.out.print("ScreenShot is saved");
    driver.quit();
	}

}


