package steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CarWale {
	public ChromeDriver driver;

	@Given("Go to https://www.carwale.com/")
	public void openUrl() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_83.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.carwale.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("Click on Used")
	public void clickUsed() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElementByXPath("//li[@data-tabs='usedCars']").click();
	}

	@Given("Select the City as Chennai")
	public void city() throws InterruptedException {
		driver.findElementById("usedCarsList").sendKeys("chennai");
		Thread.sleep(1000);
		driver.findElementByXPath("//input[@id='usedCarsList']").sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}

	@Given("Select budget min and max and Click Search")
	public void budjet() throws InterruptedException {
		driver.findElementByXPath("//li[text()='8 Lakh']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//ul[@id='maxPriceList']/li[text()='12 Lakh']").click();
		driver.findElementByXPath("//button[@id='btnFindCar']").click();
		Thread.sleep(3000);
		driver.findElementById("placesQuery").sendKeys("chennai");
		driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click();
	}

	@Given("Select Cars with Photos under Only Show Cars With")
	public void carWithPhoto() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElementByName("CarsWithPhotos").click();
	}

	@Given("Select Manufacturer as Hyundai --> Creta")
	public void manufacturer() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);

		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElementByXPath("//ul[@id='makesList']/li[@data-manufacture-en='Hyundai']")));

		WebElement element2 = driver.findElementByXPath("//ul[@id='makesList']/li[@data-manufacture-en='Hyundai']");
		js.executeScript("arguments[0].scrollIntoView()", element2);

		js.executeScript("arguments[0].click()", element2);
		Thread.sleep(1000);
		WebElement element3 = driver.findElementByXPath("(//span[@class='model-txt'])[1]");
		js.executeScript("arguments[0].scrollIntoView()", element3);

		js.executeScript("arguments[0].click()", element3);
	}

	@Given("Select Fuel Type as Petrol")
	public void petrol() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);

		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@name='fuel']/h3")));
		WebElement element = driver.findElementByXPath("//div[@name='fuel']/h3");
		
		js.executeScript("arguments[0].scrollIntoView()", element);

		js.executeScript("arguments[0].click()", element);
		Thread.sleep(1000);
		driver.findElementByXPath("(//li[@name='Petrol']/span)[1]").click();
	}

	@Given("Select Best Match as KM: Low to High")
	public void match() throws InterruptedException {
		Thread.sleep(3000);
		WebElement path = driver.findElementByXPath("//select[@id='sort']");
		Select sel = new Select(path);
		sel.selectByVisibleText("KM: Low to High");
		Thread.sleep(3000);
	}

	@Given("Add the least KM ran car to Wishlist")
	public void add() {
		Actions action = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		List<WebElement> kmText = driver.findElementsByXPath("//span[@class='slkms vehicle-data__item']");
		List<Integer> ls_sorted = new ArrayList<Integer>();
		List<Integer> ls_ori = new ArrayList<Integer>();
		for (int i = 0; i < kmText.size(); i++) {
			String string = kmText.get(i).getText().replaceAll("\\D", "");
			int km = Integer.parseInt(string);
			ls_ori.add(km);
		}
		for (int i = 0; i < kmText.size(); i++) {
			String string = kmText.get(i).getText().replaceAll("\\D", "");
			int km = Integer.parseInt(string);
			ls_sorted.add(km);
		}
		Collections.sort(ls_sorted);
		System.out.println("sorted" + ls_sorted);

		if (ls_sorted == ls_ori) {
			System.out.println("KM sorted low to high");
		} else {
			System.out.println("KM not sorted correctly from low to high");
		}
		for (int i = 0; i < ls_ori.size(); i++) {
			if (ls_ori.get(i).equals(ls_sorted.get(0))) {
				WebElement element = driver
						.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])[" + (i + 1) + "]");
				action.moveToElement(element).perform();
				js.executeScript("arguments[0].click();", element);
			}
		}
	}

	@Given("Go to Wishlist and Click on More Details")
	public void moreDetails() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		Actions action = new Actions(driver);

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']")));
		action.moveToElement(driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']")).perform();
		driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//a[text()='More details »']").click();
	}

	@When("Print all the details under Overview in the Same way as displayed in applications")
	public void print() throws InterruptedException {
		Set<String> set = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(set);
		driver.switchTo().window(ls.get(2));
		Thread.sleep(2000);
		List<WebElement> price = driver.findElementsByXPath("//div[@class='equal-width text-light-grey']");
		List<WebElement> amt = driver.findElementsByXPath("//div[@class='equal-width dark-text']");
		Map<String, String> map = new LinkedHashMap<String, String>();

		for (int i = 0; i < amt.size(); i++) {
			String outputP = price.get(i).getText();
			String outputF = amt.get(i).getText();
			map.put(outputP, outputF);
		}
		for (Entry<String, String> eachEntry : map.entrySet()) {
			System.out.println(eachEntry);
		}
	}

	@Then("Close the browser")
	public void close() {
		driver.quit();
	}

}
