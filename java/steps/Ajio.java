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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Ajio {
	public ChromeDriver driver;

	@Given("Go to https://www.ajio.com/")
	public void url() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver_83.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.ajio.com/shop/women");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("Mouseover on Women, CATEGORIES and click on Kurtas")
	public void women() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[@href='/shop/women']")).build().perform();
		// action.moveToElement(driver.findElementByXPath("(//li[@class='catg
		// active-text']//a)[1]")).build().perform();
		driver.findElementByXPath("(//a[@title='Kurtas'])[2]").click();
	}

	@Given("Click on Brands and choose Ajio")
	public void clickBrands() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElementByXPath("(//span[@class='facet-left-pane-label'])[4]")));
		driver.findElementByXPath("(//span[@class='facet-left-pane-label'])[4]").click();
		driver.findElementByXPath("//label[@for='AJIO']").click();
		Thread.sleep(3000);
	}

	@Given("Check all the results are Ajio")
	public void checkResults() {
		List<WebElement> list = driver.findElementsByXPath("//div[@class='brand']");
		String text ="";
		for (int i = 0; i < list.size(); i++) {
			 text = list.get(i).getText();
		}
			if (text.equalsIgnoreCase("AJIO")) {
				System.out.println("All Selected items are of brand AJIO");
			} else {
				System.out.println("Brands are not AJIO");
			}
		
	}

	@Given("Set Sort by the result as Discount")
	public void sortAsDiscount() {
		WebElement element = driver.findElementByXPath("//div[@class='filter-dropdown']//select[1]");
		Select sel = new Select(element);
		sel.selectByVisibleText("Discount");
	}

	@Given("Select the Color and click ADD TO BAG")
	public void setColor() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		Thread.sleep(5000);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElementByXPath("(//img[contains(@class,'rilrtl-lazy-img ')])[1]")));
		driver.findElementByXPath("(//img[contains(@class,'rilrtl-lazy-img ')])[1]").click();
		Set<String> set = driver.getWindowHandles();
		List<String> ls = new ArrayList<String>(set);
		driver.switchTo().window(ls.get(2));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//img[@alt='blue']")));
		driver.findElementByXPath("//img[@alt='blue']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(2000);
	}

	@Given("Verify the error message Select your size to know your estimated delivery date")
	public void verifyErrorMsg() {
		WebElement errorMsg = driver.findElementByClassName("edd-pincode-msg-details");
		System.out.println("Error Msg is :" + errorMsg.getText());
	}

	@Given("Select size and click ADD TO BAG")
	public void selectSize() throws InterruptedException {
		driver.findElementByXPath("(//div[text()='XS'])[1]").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(8000);
	}

	@Given("click on Enter pin-code to know estimated delivery date")
	public void clickMsg() {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath(
				"//span[contains(@class,'edd-pincode-msg-details edd-pincode-msg-details-pointer')]")));
		driver.findElementByXPath("//span[contains(@class,'edd-pincode-msg-details edd-pincode-msg-details-pointer')]")
				.click();
	}

	@Given("Enter the pincode as 603103 and click Confirm pincode")
	public void enterPincode() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElementByClassName("edd-pincode-modal-text").sendKeys("603103");
		driver.findElementByClassName("edd-pincode-modal-submit-btn").click();
		Thread.sleep(2000);
	}

	@When("Print the message and click Go to Bag")
	public void printMsg() throws InterruptedException {
		String msg = driver.findElementByClassName("edd-message-success-details").getText();
		System.out.println("Delivery message is: " + msg);
		driver.findElementByClassName("ic-pdp-add-cart").click();
		Thread.sleep(3000);
	}

	@Then("Click on Proceed to Shipping and clode the browser")
	public void close() {
		driver.findElementByXPath("//button[text()='Proceed to shipping']").click();
		driver.quit();
	}
}
