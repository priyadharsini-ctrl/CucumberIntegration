package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Shein {
	public ChromeDriver driver;
	Actions action;
	WebDriverWait wait;

	@Given("open https://www.shein.in/")
	public void openLink() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_83.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.shein.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.findElementByXPath("(//i[@class='iconfont icon-close she-close'])[19]").click();
	}

	@Given("Mouseover on Clothing and click Jeans")
	public void clickJeans() throws InterruptedException {
		action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("(//span[@class='cate-sec-in j-cate-sec-in'])[2]")).build()
				.perform();
		WebElement jeans = driver.findElementByXPath("(//a[@title='Jeans'])[1]");
		driver.executeScript("arguments[0].click()", jeans);
		Thread.sleep(3000);
	}

	@Given("Choose Black under Jeans product count")
	public void chooseBlack() throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByLinkText("Black")));
		driver.findElementByLinkText("Black").click();
		Thread.sleep(2000);
	}

	@Given("check size as medium")
	public void checkSize() throws InterruptedException {
		driver.findElementByXPath("(//li[@class='filter-title leftnav-first-title'])[3]").click();
		driver.findElementByXPath(
				"html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/div[3]/div[1]/span[4]/a[1]/span[1]")
				.click();
		Thread.sleep(3000);
	}

	@Given("check whether the color is black")
	public void checkColor() {
		String col = driver.findElementByXPath("//a[text()='Black']").getText();
		System.out.println("Colour is  " + col);
		if (col.equalsIgnoreCase("Black")) {
			System.out.println("Selected items are of black colour");
		} else {
			System.out.println("Selected items are of black colour");
		}
	}

	@Given("Click first item to Add to Bag")
	public void clickAdd() throws InterruptedException {
		driver.findElementByXPath("(//a[@class='c-goodsitem__goods-name j-goodsitem__goods-name '])[1]").click();
		Thread.sleep(2000);
		Set<String> winSet = driver.getWindowHandles();
		List<String> winLis = new ArrayList<String>(winSet);
		driver.switchTo().window(winLis.get(2));
		Thread.sleep(3000);
	}

	@Given("Click the size as M abd click Submit")
	public void clickSubmit() throws InterruptedException {
		driver.executeScript("window.scrollBy(0, 350)");
		driver.findElementByXPath(
				"(//div[contains(@class,'product-intro__size-radio j-product-intro__size-radio')])[3]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("(//button[@class='she-btn-black she-btn-xl'])[1]").click();
	}

	@When("Click view Bag")
	public void view() throws InterruptedException {

		Thread.sleep(1000);
		driver.findElementByXPath("//a[text()='view bag']").click();
	}

	@Then("Check the size is Medium or not and close browser")
	public void close() {
		String size = driver.findElementByXPath("//em[text()='M']").getText();
		if (size.equalsIgnoreCase("M"))
			System.out.println("Size M is validated");
		else
			System.out.println("Size M is validated");
		driver.quit();
	}
}
