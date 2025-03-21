package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.Reports;
import java.time.Duration;

public class ShoppingCartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest test;

    // Locators
    private By practiceDropdown = By.xpath("//a[contains(@class,'dropdown-toggle') and contains(text(),'PRACTICE')]");
    private By ecommercePracticeLink = By.xpath("//a[@href='http://ecommercepractice.letskodeit.com/' and contains(@class,'dynamic-link')]");
    private By shopNowButton = By.xpath("//button[contains(@class,'Hero-module--ctaButton')]");
    private By productImage = By.xpath("//img[@alt='relaxed t shirt woman']");
    private By sizeOption = By.xpath("//div[contains(@class,'BoxOption-module--isActive')]");
    private By addToBagButton = By.xpath("//button[contains(text(),'Add to Bag')]");
    private By cartIcon = By.xpath("//a[@href='/cart']");
    private By increaseQuantity = By.xpath("//div[contains(@class,'AdjustItem-module--iconContainer')]");
    private By checkoutButton = By.xpath("//button[contains(text(),'checkout')]");
    private By myBagHeader = By.xpath("//h3[text()='My Bag']");

    // Constructor
    public ShoppingCartPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Utility method for safe clicking with logging
    private void safeClick(By locator, String elementName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
            Reports.generateReport(driver, test, Status.PASS, "Clicked on: " + elementName);
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to click " + elementName);
            throw e;  
        }
    }

    // Navigate to eCommerce Practice Page
    public void goToEcommercePractice() {
        try {
            safeClick(practiceDropdown, "Practice Dropdown");
            safeClick(ecommercePracticeLink, "eCommerce Practice Link");
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Navigation failed: " + e.getMessage());
            throw e;
        }
    }

    // Switch to new window
    public void switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                Reports.generateReport(driver, test, Status.PASS, "Switched to new window. New Window Handle: " + windowHandle);
                return;
            }
        }
        Reports.generateReport(driver, test, Status.FAIL, "New window not found.");
    }


    // Click Shop Now
    public void clickShopNow() {
        safeClick(shopNowButton, "Shop Now Button");
    }

    // Select Product
    public void selectProduct() {
        safeClick(productImage, "Product Image");
    }

    // Select Size
    public void selectSize() {
        safeClick(sizeOption, "Size Option");
    }

    // Add to Bag
    public void addToBag() {
        safeClick(addToBagButton, "Add to Bag Button");
    }

    // Proceed to Checkout
    public void checkout() {
        try {
            Reports.generateReport(driver, test, Status.INFO, "Starting checkout process...");
            safeClick(cartIcon, "Cart Icon");
            safeClick(increaseQuantity, "Increase Quantity Button");
            safeClick(checkoutButton, "Checkout Button");
            Reports.generateReport(driver, test, Status.PASS, "Checkout process completed successfully.");
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Checkout failed: " + e.getMessage());
            throw e;
        }
    }

    // Verify "My Bag" page
    public boolean isMyBagDisplayed() {
        try {
            boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(myBagHeader)).isDisplayed();
            Reports.generateReport(driver, test, Status.PASS, "My Bag page is displayed.");
            return isDisplayed;
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to verify My Bag page: " + e.getMessage());
            return false;
        }
    }
}
