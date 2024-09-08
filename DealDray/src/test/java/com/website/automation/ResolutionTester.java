package com.website.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ResolutionTester {

    public static void testOnResolutions(WebDriver driver, List<String> urls, String browser, String[] resolutions) throws IOException {
        int maxLinks = 5;  // Set the maximum number of links to test
        for (int i = 0; i < Math.min(urls.size(), maxLinks); i++) {  // Limit to maxLinks
            String url = urls.get(i);
            for (String res : resolutions) {
                String[] dim = res.split("x");
                Dimension resolution = new Dimension(Integer.parseInt(dim[0]), Integer.parseInt(dim[1]));
                driver.manage().window().setSize(resolution);
                driver.get(url);
                // Take and save the screenshot
                takeScreenshot(driver, browser, res);
            }
        }
    }

    public static void takeScreenshot(WebDriver driver, String browser, String resolution) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        // Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Create the folder structure and save the screenshot
        String folderPath = "./screenshots/" + browser + "/" + resolution + "/";
        Files.createDirectories(Paths.get(folderPath));
        File destination = new File(folderPath + "Screenshot-" + timeStamp + ".png");
        Files.copy(screenshot.toPath(), destination.toPath());
    }

    public static void main(String[] args) throws Exception {
        
        String[] resolutionsDesktop = {"1920x1080", "1366x768", "1536x864"};
        String[] resolutionsMobile = {"360x640", "414x896", "375x667"};
        List<String> urls = SiteMapParser.getPagesFromSitemap("https://www.getcalley.com/page-sitemap.xml");

        // Test with Chrome
//        WebDriverManager.chromedriver().browserVersion("128.0.6613.120").setup();
//        WebDriver chromeDriver = new ChromeDriver();
       WebDriver chromeDriver = new ChromeDriver();
        testOnResolutions(chromeDriver, urls, "Chrome", resolutionsDesktop);
        testOnResolutions(chromeDriver, urls, "Chrome", resolutionsMobile);
        chromeDriver.quit();

         //Test with Firefox
        WebDriver firefoxDriver = new FirefoxDriver();
        testOnResolutions(firefoxDriver, urls, "Firefox", resolutionsDesktop);
        testOnResolutions(firefoxDriver, urls, "Firefox", resolutionsMobile);
        firefoxDriver.quit();
    }
}
