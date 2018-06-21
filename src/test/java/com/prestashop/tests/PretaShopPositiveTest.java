package com.prestashop.tests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PretaShopPositiveTest {

	WebDriver driver;
	Faker faker = new Faker();
	String email = faker.internet().emailAddress();
	String firstName = faker.name().firstName();
	String lastName = faker.name().lastName();
	String password = faker.internet().password();

	Random random = new Random();

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test
	public void correctCredentialsTest() {
		driver.get("http://automationpractice.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"email_create\"]")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.id("id_gender" + (random.nextInt(2) + 1))).click();

		driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		driver.findElement(By.id("passwd")).sendKeys(password);

		Select day = new Select(driver.findElement(By.id("days")));
		day.selectByIndex((random.nextInt(31) + 1));
		Select month = new Select(driver.findElement(By.id("months")));
		month.selectByIndex((random.nextInt(12) + 1));
		Select year = new Select(driver.findElement(By.id("years")));
		year.selectByIndex((random.nextInt(117) + 2));

		driver.findElement(By.id("uniform-newsletter")).click();
		driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("company")).sendKeys(faker.company().name());
		driver.findElement(By.id("address1")).sendKeys(faker.address().streetAddress());
		driver.findElement(By.id("address2")).sendKeys(faker.address().secondaryAddress());
		driver.findElement(By.id("city")).sendKeys(faker.address().cityName());

		Select state = new Select(driver.findElement(By.id("id_state")));
		state.selectByIndex((random.nextInt(50) + 1));

		driver.findElement(By.id("postcode")).sendKeys(faker.address().zipCode().substring(0, 5));
		driver.findElement(By.id("other")).sendKeys("No info");
		driver.findElement(By.id("phone")).sendKeys(faker.phoneNumber().cellPhone());
		driver.findElement(By.id("phone_mobile")).sendKeys(faker.phoneNumber().cellPhone());
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys(faker.internet().emailAddress());
		driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span")).click();

		// SIGN OUT
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")).click();

		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText(),
				(firstName + " " + lastName));

	}
	
	@AfterMethod
	public void tearDownMethod() {
		driver.close();
	}
	

}
