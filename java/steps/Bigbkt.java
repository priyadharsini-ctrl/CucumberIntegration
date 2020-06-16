package steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Bigbkt {
	public ChromeDriver driver;

	Actions action;
	WebDriverWait wait;
	
	
	@Given("Click url") 
			public void url() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_83.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	    
	}

	@Given("mouse over on Shop by Category")
	public void mouseOver()  {
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']")));
		action.moveToElement(driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']")).build().perform();
	}

	@Given("Go to Beverages and Fruit juices & Drinks")
	public void juiceAndDrinks()  {
		action.moveToElement(driver.findElementByXPath("(//a[@href='/cl/beverages/?nc=nb'])[2]")).build().perform();
		action.moveToElement(driver.findElementByXPath("(//a[@href='/pc/beverages/fruit-juices-drinks/?nc=nb'])[2]")).build().perform();
	}

	@Given("Click on JUICES")
	public void clickJuices() throws InterruptedException {
		driver.findElementByXPath("(//a[@ng-href='/pc/beverages/fruit-juices-drinks/juices-sweetened/?nc=nb'])[2]").click();
		Thread.sleep(3000);
	}

	@Given("click Tropicana and Real under Brand")
	public void clickBrand() throws InterruptedException  {
		driver.findElementByXPath("//span[text()='Real']")
		.click();
Thread.sleep(3000);
driver.findElementByXPath("//span[text()='Tropicana']")
		.click();
Thread.sleep(3000);
	}

	@Given("Check whether the products is availabe with Add button")
	public void checkAdd()  {
		List<WebElement> path = driver.findElementsByXPath("//button[contains(@class,'btn btn-add')]");
		for (WebElement eachButtn : path) {
			if (eachButtn.getText().equalsIgnoreCase("ADD")) {
				System.out.println("Each Item Has ADD Button");
			} else {
				System.out.println("ADD button not present");
			}
		}
		}

	@Given("Add the First listed available product")
	public void clickProduct() throws InterruptedException {
		driver.findElementByXPath("(//button[contains(@class,'btn btn-add')])[1]").click();
		Thread.sleep(3000);
		}

	@Given("Select Chennai as City, Alandur(.*),Chennai as Area and click Continue")
	public void addAddress(String data) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement element = driver.findElementByXPath("//span[text()='Kottur Gardens-Adyar House']");
		js.executeScript("arguments[0].scrollIntoView()", element);
		js.executeScript("arguments[0].click()", element);
		js.executeScript("arguments[0].click()", element);

		driver.findElementByXPath("//a[text()='Change Location']").click();
		driver.findElementById("areaselect").sendKeys(data);
		Thread.sleep(2000);
		driver.findElementById("areaselect").sendKeys(Keys.TAB);

	}

	@Given("Mouse over on My Basket print the product name. count and price")
	public void myBasket() throws InterruptedException  {
		action.moveToElement(driver.findElementByXPath("//i[contains(@class,'icon svg-basket')]")).click().build()
		.perform();
Thread.sleep(3000);
String prodName = driver.findElementByXPath("//div[@class='product-name']/a").getText();
String price = driver.findElementByXPath("(//span[@class='ng-binding'])[4]").getText();
String count = driver.findElementByXPath("(//div[@qa='pcsMB'])[1]")
		.getText();
System.out.println("Product name is: "+prodName+"count is" +count+"Price is: "+price);
	}

	@When("Click View Basker and Checkout")
	public void checkOut() throws InterruptedException {
		driver.findElementByXPath("//button[text()='View Basket & Checkout']").click();
		Thread.sleep(3000);
	}

	@Then("Click the close button and close browser")
	public void closeBrowser()  {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@class='close']")));
		driver.findElementByXPath("//button[@class='close']").click();
		driver.close();
		}
}
