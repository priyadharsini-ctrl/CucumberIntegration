package steps;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BigBasket {

	public ChromeDriver driver;

	@Given("go to given url")
	public void goUrl()  {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_83.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
		
	@Given("Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS")
		public void clickRiceProduct() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions action = new Actions(driver);

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']")));
		action.moveToElement(driver.findElementByXPath("//a[@class='dropdown-toggle meganav-shop']")).build().perform();
		action.moveToElement(driver.findElementByLinkText("Foodgrains, Oil & Masala")).build().perform();
		driver.findElementByLinkText("Rice & Rice Products").click();
		Thread.sleep(2000);
	}

	@Given("Click on Boiled & Steam Rice and get URL of the page")
	public void getURL() {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElementByXPath("//div[@class='ng-scope']/following::span[text()='Boiled & Steam Rice']")));
		driver.findElementByXPath("//div[@class='ng-scope']/following::span[text()='Boiled & Steam Rice']").click();
		String currentUrl = driver.getCurrentUrl();
		System.out.println("URL of the page is: " + currentUrl);
	}

	@Given("Choose the Brand as bb Royal")
	public void chooseBrand() throws InterruptedException {
		driver.findElementByXPath("(//i[contains(@class,'cr-icon fa')])[3]").click();
		Thread.sleep(5000);
	}

	@Given("Go to Ponni Boiled Rice - Super Premium and select 10kg bag from Dropdown")
	public void selectPonniRice() {
		driver.findElementByXPath("//a[text()='Ponni Boiled Rice - Super Premium']/following::button[@type='button']")
				.click();
		driver.findElementByXPath("(//span[text()='10 kg'])[5]").click();
	}

	@Given("Click Add button and search Dal")
	public void searchDal() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		driver.findElementByXPath(
				"//div[@id='dynamicDirective']/product-deck[1]/section[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/div[1]/div[8]/product-template[1]/div[1]/div[4]/div[3]/div[1]/div[3]/div[2]/div[2]/button[1]")
				.click();
		// Search dal
		driver.findElementById("input").sendKeys("dal", Keys.ENTER);
		Thread.sleep(3000);
		wait.until(
				ExpectedConditions.visibilityOf(driver.findElementByXPath("(//button[@data-toggle='dropdown'])[3]")));
		driver.findElementByXPath("(//button[@data-toggle='dropdown'])[3]").click();
		Thread.sleep(2000);
	}

	@Given("Go to Toor Dal and select 2kg & set Qty 2")
	public void selectToorDal() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='2 kg'])[4]")));
		driver.findElementByXPath("(//span[text()='2 kg'])[4]").click();
		driver.findElementByXPath("(//input[@ng-model='vm.startQuantity'])[3]").clear();
		Thread.sleep(3000);
		driver.findElementByXPath("(//input[@ng-model='vm.startQuantity'])[3]").sendKeys("2");
		driver.findElementByXPath("(//button[@qa='add'])[3]").click();
		Thread.sleep(8000);
	}

	@Given("Select CHennai as City, Alandur-600016,Chennai as Area and click Continue")
	public void selectAddress() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement element = driver.findElementByXPath("//span[text()='Kottur Gardens-Adyar House']");
		js.executeScript("arguments[0].scrollIntoView()", element);
		js.executeScript("arguments[0].click()", element);
		driver.findElementById("areaselect").sendKeys("600016");
		Thread.sleep(2000);
		driver.findElementById("areaselect").sendKeys(Keys.TAB);

		driver.findElementByXPath("//button[text()='Continue']").click();
		Thread.sleep(3000);

	}

	@Given("Mouse over on My Basket take a screen shot")
	public void takeScreenShot() throws InterruptedException, IOException {
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElementByXPath("//i[contains(@class,'icon svg-basket')]")).click().build()
				.perform();
		Thread.sleep(3000);
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/bb.png");
		FileUtils.copyFile(src, dst);
	}

	@When("Click View Basket and Checkout")
	public void clickCheckOut() throws InterruptedException {
		driver.findElementByXPath("//button[text()='View Basket & Checkout']").click();
		Thread.sleep(3000);
	}

	@Then("Click the close button and close the browser")
	public void close() {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@class='close']")));
		driver.findElementByXPath("//button[@class='close']").click();
		driver.close();
	}
}
