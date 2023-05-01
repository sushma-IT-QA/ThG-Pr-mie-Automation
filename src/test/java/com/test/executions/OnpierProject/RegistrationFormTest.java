package com.test.executions.OnpierProject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationFormTest {

	static WebDriver driver;

	@BeforeMethod
	//Replace the driver directory path
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ADMIN\\Downloads\\softwares\\chromedriver_win32 (1)\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

		driver.get("https://thg-greenair.dev.wgv.onpier.de/onboarding");
		System.out.println("Path " + System.getProperty("user.dir") + "\\src\\test\\resources\\THG File.pdf");

	}

	@Test
	public void getTitleTest() {
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, "THG Prämie", "Wrong title page ");
	}

	@Test(priority = 1)
	public void vehicleDetails() {
		driver.findElement(By.xpath(
				"//*[@id=\"THG\"]/div[1]/app-vehicle-class-selector/div/div/form/label[1]/mat-card/mat-card-content"))
				.click();
		driver.findElement(By.xpath("//*[@id=\"BonusModel\"]/div/mat-card[1]/mat-card-content/div[2]/div/button"))
				.click();

		// Locate the file input field and set the path of the PDF file to upload
		WebElement fileInputField1 = driver.findElement(By.xpath("//*[@id=\"Fahrzeugschein Vorderseite\"]"));
		fileInputField1.sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\THG File.pdf");
		WebElement fileInputField2 = driver.findElement(By.xpath("//input[@type='file']"));
		fileInputField2.sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\THG File2.pdf");
//		
		driver.findElement(By.xpath(
				"//*[@id=\"cdk-step-content-0-0\"]/app-step1/app-stepper-buttons/div/div[2]/button[2]/span[2]/div"))
				.click();
	}

	// To verify if the expected elements are available on registration page
//	@Test(priority =2 ,dependsOnMethods ="vehicleDetails") 
	public void verifyElementOnPageTest() {
//		driver.findElement(By.xpath("//input[@type='radio']")).isDisplayed();
		driver.findElement(By.xpath("//*[@id=\"mat-radio-2\"]/div/div/div[1]")).isDisplayed();
		driver.findElement(By.xpath("//*[@id=\"mat-radio-3\"]/div/div/div[3]/div")).isDisplayed();
		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[1]/div[1]/onpier-input/div/div/div"))
				.isDisplayed();

		driver.findElement(By.xpath(
				"//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[1]/div[2]/onpier-input/div/div/div/input"))
				.isDisplayed();

		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[1]/div[2]/onpier-input/div/div/div"))
				.isDisplayed();

	}

	/*
	 * To verify Registration process with providing valid input data filling all
	 * the fields "further" button should be enabled and should go to
	 * "apply for bonus page"
	 * 
	 */
	@Test
	public void validRegistrationTest() {

		WebElement dropdownElement = driver
				.findElement(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[1]"));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByIndex(1);

		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[2]/onpier-input[1]/div/div/div"))
				.sendKeys("billy");
		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[2]/onpier-input[2]/div/div/div"))
				.sendKeys("mac");
		driver.findElement(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/onpier-input/div/div/div"))
				.sendKeys("billymac123@gmail.com");
		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[3]/onpier-input[1]/div/div/div"))
				.sendKeys("Billy mac");
		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[3]/onpier-input[2]/div/div/div"))
				.sendKeys("Wj28377747446479");
		driver.findElement(By.xpath(
				"//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/app-stepper-buttons/div/div[2]/button[2]/span[5]"))
				.click();

	}

	// To verify the process with different invalid input data
	@Test
	public void emptyAccountHolderTest() {
		WebElement dropdownElement = driver
				.findElement(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[1]"));
		Select dropdown = new Select(dropdownElement);
		dropdown.selectByVisibleText("Herr");

		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[2]/onpier-input[1]/div/div/div"))
				.sendKeys("billy");
		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[2]/onpier-input[2]/div/div/div"))
				.sendKeys("mac");
		driver.findElement(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/onpier-input/div/div/div"))
				.sendKeys("billymac123@gmail.com");
		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[3]/onpier-input[1]/div/div/div"))
				.sendKeys("");
		driver.findElement(
				By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[3]/onpier-input[2]/div/div/div"))
				.sendKeys("Wj28377747446479");
		driver.findElement(By.xpath(
				"//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/app-stepper-buttons/div/div[2]/button[2]/span[5]"))
				.click();

	}

//	@Test
	public void verifyPursueTest() {
		driver.findElement(By.xpath("//*[@id=\"mat-radio-3\"]/div/label")).click();
		driver.findElement(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[1]/div[1]/mat-icon"))
				.click();
		String textMessage = driver.findElement(By.xpath("//*[@id=\"mat-mdc-dialog-5\"]/div/div")).getText();
		System.out.println(textMessage);
	}

//	@Test
	public void verifyTaxIdTest() {
		driver.findElement(By.xpath("//*[@id=\"cdk-step-content-0-1\"]/div/app-step2/form/div[1]/div[1]/mat-icon"))
				.click();
		String TextMessage = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/mat-dialog-container/div/div"))
				.getText();
		System.out.println(TextMessage);
	}

	@AfterMethod
	public void tearDown() {

		driver.close();
	}
}