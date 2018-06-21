package com.prestashop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrestaShopTestNG {

	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	//@Ignore
	@Test
	public void wrongCredentialsTest() {
		driver.get("http://automationpractice.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("emreyildirim@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText()
				.contains("Authentication failed."));
		// Assert.assertTrue(driver.findElement(By.id("authentication")).getText().contains("Authentication
		// failed."));
	}

	//@Ignore
	@Test
	public void invalidEmailTest() {
		driver.get("http://automationpractice.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("emreyildirim");
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText()
				.contains("Invalid email address."));
	}

	//@Ignore
	@Test
	public void blankEmailTest() {
		driver.get("http://automationpractice.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		// driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("empty");
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText()
				.contains("An email address required."));
	}

	@Test
	public void blankPasswordTest() {
		driver.get("http://automationpractice.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("emreyildirim@gmail.com");
		// driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText()
				.contains("Password is required."));
	}

	@AfterMethod
	public void tearDownMethod() {
		driver.close();
	}

}
