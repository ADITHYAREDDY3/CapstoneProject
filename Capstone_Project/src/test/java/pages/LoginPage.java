package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoAlertPresentException;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.Reports;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest test;

    // Locators
    private By emailField = By.id("email");
    private By passwordField = By.id("login-password");
    private By loginButton = By.id("login");
    private By arrowMenu = By.className("zl-navbar-rhs-img");
    private By logoutLink = By.partialLinkText("Logout");
    private By errorMsg = By.id("incorrectdetails");
    private By forgetPassword = By.linkText("Forgot Password?");
    private By nameInput = By.id("name");
    private By confirmButton = By.id("confirmbtn");

    // Constructor
    public LoginPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Enter email and password
    public void enterLoginCredentials(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
        Reports.generateReport(driver, test, Status.PASS, "Entered login credentials: " + email);
    }

    // Click login button
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        Reports.generateReport(driver, test, Status.PASS, "Clicked Login button.");
    }

 // Check if My Courses page is displayed
    public boolean isMyCoursesPageDisplayed() {
        try {
            boolean result = wait.until(ExpectedConditions.titleContains("My Courses"));

            if (result) {
                Reports.generateReport(driver, test, Status.PASS, "My Courses page displayed.");
            } else {
            	 Reports.generateReport(driver, test, Status.FAIL,"My Courses page NOT displayed.");
            }
            return result;
        } catch (Exception e) {
        	 Reports.generateReport(driver, test, Status.FAIL,"Error verifying My Courses page: ");
            return false;
        }
    }


    // Logout method
    public void clickOnMenuAndLogout() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(arrowMenu)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink)).click();
            Reports.generateReport(driver, test, Status.PASS, "User logged out successfully.");
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Unable to click the logout button. Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Check if error message is visible
    public boolean isErrorMsgVisible() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
            boolean isVisible = errorElement.isDisplayed();
            
            if (isVisible) {
                Reports.generateReport(driver, test, Status.PASS, "Error message is visible.");
            } else {
                Reports.generateReport(driver, test, Status.FAIL, "Error message is NOT visible.");
            }
            return isVisible;
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Error message is NOT visible. Error: " + e.getMessage());
            return false;
        }
    }


    // Click on Forgot Password link
    public void clickOnForgetPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(forgetPassword)).click();
        Reports.generateReport(driver, test, Status.INFO, "Clicked on Forgot Password link.");
    }

    // Verify if Password Reset Page is visible
    public boolean isVerifyPasswordresetPageVisible() {
        try {
            boolean result = wait.until(ExpectedConditions.titleContains("Password Reset"));
            
            if (result) {
                Reports.generateReport(driver, test, Status.PASS, "Password Reset Page is displayed.");
            } else {
                Reports.generateReport(driver, test, Status.FAIL, "Password Reset Page is NOT displayed.");
            }
            return result;
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Error verifying Password Reset Page. Error: " + e.getMessage());
            return false;
        }
    }


    // Enter name in input field
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        Reports.generateReport(driver, test, Status.INFO, "Entered name: " + name);
    }

    // Click Confirm Button
    public void clickConfirmButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmButton)).click();
        Reports.generateReport(driver, test, Status.INFO, "Clicked on Confirm button.");
    }

    // Accept Alert
    public void acceptAlert() {
        try {
            driver.switchTo().alert().accept();
            Reports.generateReport(driver, test, Status.INFO, "Accepted the alert.");
        } catch (NoAlertPresentException e) {
            Reports.generateReport(driver, test, Status.FAIL, "No alert present to accept. Exception: " + e.getMessage());
        }
    }

    // Dismiss Alert
    public void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
            Reports.generateReport(driver, test, Status.INFO, "Dismissed the alert.");
        } catch (NoAlertPresentException e) {
            Reports.generateReport(driver, test, Status.FAIL, "No alert present to dismiss. Exception: " + e.getMessage());
        }
    }
}
