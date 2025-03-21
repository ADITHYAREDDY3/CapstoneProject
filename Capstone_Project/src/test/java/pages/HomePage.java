package pages;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.Reports;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest test;

    // Locators
    private By homeMenu = By.linkText("HOME");
    private By allCourses = By.linkText("ALL COURSES");
    private By interview = By.linkText("INTERVIEW");
    private By support = By.linkText("SUPPORT");
    private By blog = By.linkText("BLOG");

    // Constructor
    public HomePage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.test = test;
    }

    // Navigate to Home Page
    public void navigateToHomePage() {
        try {
            driver.findElement(homeMenu).click();
            Reports.generateReport(driver, test, Status.PASS, "Navigated to Home Page successfully.");
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to navigate to Home Page. Error: " + e.getMessage());
            throw e;
        }
    }

    // Verify Home Page
    public boolean verifyHomePage() {
        try {
            boolean isHome = wait.until(ExpectedConditions.titleContains("Home Page"));
            if (isHome) {
                Reports.generateReport(driver, test, Status.PASS, "Home Page title verified successfully.");
            } else {
                Reports.generateReport(driver, test, Status.FAIL, "Home Page title verification failed.");
            }
            return isHome;
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Exception while verifying Home Page title. Error: " + e.getMessage());
            throw e;
        }
    }

    // Get Page Title with Logging
    public String getPageTitle() {
        try {
            String title = driver.getTitle();
            Reports.generateReport(driver, test, Status.INFO, "Page title retrieved: " + title);
            return title;
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to retrieve page title. Error: " + e.getMessage());
            throw e;
        }
    }

    // Check Section Visibility with Explicit Wait and Logging
    public boolean isSectionVisible(String sectionName) {
        By locator = getLocator(sectionName);
        if (locator == null) {
            Reports.generateReport(driver, test, Status.FAIL, "Invalid section name provided: " + sectionName);
            return false;
        }

        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            boolean isDisplayed = element.isDisplayed();
            Reports.generateReport(driver, test, Status.PASS, sectionName + " section is visible.");
            return isDisplayed;
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, sectionName + " section is NOT visible. Unexpected error: " + e.getMessage());
        }
        return false;
    }

    // Helper method to fetch locator based on section name
    private By getLocator(String sectionName) {
        switch (sectionName.toUpperCase()) {
            case "HOME":
                return homeMenu;
            case "ALL COURSES":
                return allCourses;
            case "INTERVIEW":
                return interview;
            case "SUPPORT":
                return support;
            case "BLOG":
                return blog;
            default:
                return null;
        }
    }
}
