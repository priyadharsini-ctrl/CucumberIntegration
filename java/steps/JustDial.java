package steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class JustDial {
	
	public ChromeDriver driver;
	WebDriverWait wait;
	String price;

	@Given("open url")
	public void url() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_83.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.justdial.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("Click on Air Tickets")
	public void clickAirTickt() throws InterruptedException  {
		//Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[@id='hotkeys_text_4']")).click();

	}

	@Given("Type (.*) and choose Chennai, IN - Chennai Airport as Leaving From")
	public void enterFromAddress(String data) throws InterruptedException  {
		wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOf(driver.findElementById("departure")));
		driver.findElementById("departure").sendKeys(data);
		Thread.sleep(1000);
		driver.findElementById("departure").sendKeys(Keys.TAB);
		Thread.sleep(1000);
	}

	@Given("Type (.*) and select Toronto, CA - Toronto City Centre Airport as Going To")
	public void enterToAddr(String place) throws InterruptedException {
		driver.findElementById("arrival").sendKeys(place);
		Thread.sleep(1000);
		driver.findElementById("arrival").sendKeys(Keys.ARROW_DOWN);
		driver.findElementById("arrival").sendKeys(Keys.TAB);

		Thread.sleep(1000);
	}

	@Given("Set Departure as 2020, July 22")
	public void enterDate() throws InterruptedException  {
		driver.findElementByXPath("//span[@class='ui-icon ui-icon-circle-triangle-e']").click();
		Thread.sleep(1000);
		driver.findElementByLinkText("22").click();
		Thread.sleep(2000);
	}

	@Given("Add Adult 2, Children 1 click and Search")
	public void enterDetails()  {
		driver.findElementByXPath("(//span[@class='plus'])[1]").click();
		driver.findElementByXPath("(//span[@class='plus'])[2]").click();
		driver.findElementByXPath("//input[contains(@class,'btn inputbtn')]").click();
	}

	@Given("Select Air Canada from multi-airline itineraries")
	public void checkAirlines() throws InterruptedException  {
		Thread.sleep(3000);
		driver.findElementById("airline1").click();
	}

	@Given("Click on Price to sort the result")
	public void clickSort() throws InterruptedException  {
		Thread.sleep(3000);
		driver.findElementByLinkText("Price").click();
		Thread.sleep(2000);
	}

	@Given("Click on Details of first result under Price")
	public void clickMoreDetails() throws InterruptedException  {
		driver.findElementById("resTD1").click();
		Thread.sleep(2000);
	}

	@Given("Capture the Flight Arrival times") 
	public void printTime() {
		List<WebElement> path = driver.findElementsByXPath("//span[@class='dettime']");
		for (WebElement webElement : path) {
			String text = webElement.getText();
			System.out.println("Flight Arrival times: " + text);

		}
		}

	@Given("Capture the total price in a list and Click on Book")
	public void clickBook() throws InterruptedException {
		 price = driver
				.findElementByXPath(
						"//table[@id='sortResult']/tbody[1]/tr[3]/td[1]/div[1]/div[2]/ul[1]/li[4]/div[1]/span[1]")
				.getText();
		System.out.println("Total price is : " + price);
		driver.findElementByXPath("(//a[@class='bookButton'])[1]").click();
		Thread.sleep(5000);
	}

	@When("Capture the Airport name base on the list of time")
	public void printAirportName()  {
		List<WebElement> name = driver.findElementsByXPath("//tr[@class='childText']//td[2]");
		for (WebElement eachName : name) {
			String names = eachName.getText();
			System.out.println("Airport details :" + names);
		}

	}

	@Then("Capture the total fare and print the difference amount from previous total price")
	public void printDiffr()  {
		String totalFare = driver
				.findElementByXPath(
						"//div[@id='outerDiv']//table/tbody[1]/tr[1]/td[2]/div[1]/ul[1]/li[4]/div[2]/span[1]/b[1]")
				.getText();
		int fare = Integer.parseInt(totalFare);
		int pric = Integer.parseInt(price);
		int diffr = fare - pric;
		System.out.println("Difference in price is : " + diffr);
	}
}
