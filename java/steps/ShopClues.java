package steps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShopClues {
	
	public ChromeDriver driver;
	Actions action; 
	JavascriptExecutor js; 
	List<WebElement> name; 
	List<WebElement> allPrice;
	WebDriverWait wait;
	
	@Given("Go to https://www.shopclues.com/")
	public void shopClues()  {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_83.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.shopclues.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("Mouseover on women and click Casual Shoes")
	public void clickShoes(){
		 action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[text()='WOMEN']")).build().perform();
		driver.findElementByXPath("//a[text()='Casual Shoes']").click();
	   	}

	@Given("Select Color as Black")
	public void setColor() throws InterruptedException  {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		Set<String> set = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(set);
		driver.switchTo().window(ls.get(2));
		Thread.sleep(2000);
		//js.executeScript("window.scrollBy(0,100)");
		WebElement color = driver.findElementByXPath("//label[@for='Black']");

		js.executeScript("arguments[0].click()", color);
		Thread.sleep(3000);
	}

	@Given("Check whether the product name contains the word black")
	public void checkColor()  {
		 name = driver.findElementsByXPath("//span[contains(@class,'prod_name')]");
		
		 allPrice = driver.findElementsByXPath("//span[@class='p_price']");

	}

	@Given("If so, add the product name and price in to Map")
	public void addMap()  {
		Map<String, String> map = new HashMap<String, String>();

		for (WebElement webElement : name) {
			if (webElement.getText().contains("Black")) {
				String prodName = webElement.getText();

				for (WebElement webElement2 : allPrice) {
					String prodPrice = webElement2.getText();
					map.put(prodName, prodPrice);
				}
			}
		}
		for (Entry<String, String> eachEntry : map.entrySet()) {
			System.out.println(eachEntry.getKey() + "------>" + eachEntry.getValue());
		}
	}

	@Given("Check Display and the count of shoes which are more than 500 rupees")
	public void checkCount() {
		int count = 0;
		for (WebElement eachPrice : allPrice) {
			String priceStr = eachPrice.getText();
			int priceNumber = Integer.parseInt(priceStr.replaceAll("\\D", ""));
			if (priceNumber > 500) {
				count++;
			}
		}
		System.out.println("Number of Products with Price more than 500 is :" + count);

	}

	@Given("Click the highest price of the shoe")
	public void highestPrice() throws InterruptedException {
		driver.findElementByLinkText("High Price").click();
	    driver.findElementByLinkText("High Price").click();

		Thread.sleep(2000);
		driver.findElementByXPath("(//span[contains(@class,'prod_name')])[2]").click();
		Thread.sleep(2000);
	}

	@Given("Get the current page URL and check with the product ID")
	public void getPageUrl() throws InterruptedException  {
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(3));
		Thread.sleep(3000);
		System.out.println("Current PageUrl: " + driver.getCurrentUrl());
		String string = driver.findElementByClassName("pID").getText().replaceAll("\\D", "");
		System.out.println("Product ID id: " + string);
	}

	@Given("Copy the offercode")
	public void getOffercode()  {
		String couponCode = driver.findElementByXPath("(//div[@class='coupons_code']//span)[1]").getText();
		System.out.println("coupon code is " + couponCode);
	}

	@Given("Mouse over on Shopping cart and click View Cart")
	public void clickAddToCart() throws InterruptedException  {
		driver.findElementByXPath("//span[@tooltip='Black']").click();
		Thread.sleep(2000);

		driver.findElementByXPath("(//span[@class='variant var '])[2]").click();

		Thread.sleep(2000);
		driver.findElementById("add_cart").click();
		Thread.sleep(3000);

		action.moveToElement(driver.findElementByClassName("cart_icon_count")).build().perform();
		Thread.sleep(2000);
		action.click(driver.findElementByXPath("//a[text()='View Cart']")).build().perform();
		Thread.sleep(2000);
	}

	@When("Type Pincode as 600016 click Submit and Place Order")
	public void enterPincode() throws InterruptedException {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById("pin_code_popup")));
		driver.findElementById("pin_code_popup").sendKeys("600016");
		driver.findElementById("get_pincode_popup").click();
		Thread.sleep(2000);
	}

	@Then("Close")
	public void clickClose()  {
	    driver.quit();
	}
}
