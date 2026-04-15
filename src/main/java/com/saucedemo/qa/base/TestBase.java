package com.saucedemo.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\91975\\Desktop\\Eclipse Project\\Eclipse Workspace\\"
			+"SauceDemoTest\\src\\main\\java\\com\\saucedemo\\qa\\config\\config.properties");
			prop.load(ip);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Initialization() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "D:\\Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();

			// 1. Disable the Password Generation and Checkup service
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			// 2. Disable Safe Browsing (which triggers breach alerts)
			options.addArguments("--safebrowsing-disable-extension-blacklist");
			options.addArguments("--safebrowsing-disable-download-protection");

			// 3. Optional: Run in Incognito or use a clean profile to avoid synced data
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}else if(browserName.equals("Edge")) {
			//System.setProperty("webdriver.edge.driver", "D:\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.get(prop.getProperty("url"));
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		String PAGE_TIMEOUT_VALUE = prop.getProperty("pageLoadTimeout");
		String IMPLICIT_WAIT_VALUE = prop.getProperty("ImplicitWait");
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(PAGE_TIMEOUT_VALUE)));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(IMPLICIT_WAIT_VALUE)));
		
	}
	
}
