package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.Base;
import utils.Reports;

import java.time.Duration;

public class SignUpPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentTest test;

    // Locators
    private By signIn = By.partialLinkText("SIGN IN");
    private By signUpButton = By.xpath("//a[contains(text(),'Sign Up')]");
    private By nameField = By.name("name");
    private By lastName = By.id("last_name");
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By confirmPasswordField = By.id("password_confirmation");
    private By registerButton = By.cssSelector("input.btn.btn-default.btn-block.btn-md.dynamic-button");

    // Constructor
    public SignUpPage(WebDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Methods with Extent Reports
    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signIn)).click();
        Reports.generateReport(driver, test, Status.INFO, "Clicked on 'SIGN IN' button");
    }

    public void clickSignUpButton() {
        // Waits until the 'Sign Up' button is clickable and then clicks it
        wait.until(ExpectedConditions.elementToBeClickable(signUpButton)).click();
        Reports.generateReport(driver, test, Status.INFO, "Clicked on 'Sign Up' button");
        if(driver.getTitle().contains("Register")) {
            Reports.generateReport(driver, test, Status.INFO, "User is on Register page");
        }else {
            Reports.generateReport(driver, test, Status.FAIL, "User is not on Register page");
        }
    }

    public void enterName(String name, String lastname) {
        try {
            // Waits for the name input field to be visible, clears any existing text, and enters the first name
            WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
            nameElement.clear();
            nameElement.sendKeys(name);

            // Waits for the last name input field to be visible, clears any existing text, and enters the last name
            WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lastName));
            lastNameElement.clear();
            lastNameElement.sendKeys(lastname);

            Reports.generateReport(driver, test, Status.PASS, "Entered Name: " + name + " and Last Name: " + lastname);
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to enter Name and Last Name");
        }
    }

    public void enterEmail(String email) {
        try {
            // Waits for the email input field to be visible, clears any existing text, and enters the email
            WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            emailElement.clear();
            emailElement.sendKeys(email);
            Reports.generateReport(driver, test, Status.PASS, "Entered Email: " + email);
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to enter Email");
        }
    }

    public void enterPassword(String password) {
        try {
            // Waits for the password input field to be visible, clears any existing text, and enters the password
            WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
            passwordElement.clear();
            passwordElement.sendKeys(password);
            Reports.generateReport(driver, test, Status.PASS, "Entered Password");
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to enter Password");
        }
    }

    public void enterConfirmPassword(String confirmPassword) {
        try {
            // Waits for the confirm password input field to be visible, clears any existing text, and enters the confirm password
            WebElement confirmPasswordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordField));
            confirmPasswordElement.clear();
            confirmPasswordElement.sendKeys(confirmPassword);
            Reports.generateReport(driver, test, Status.PASS, "Entered Confirm Password");
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Failed to enter Confirm Password");
        }
    }

    public void handleCaptcha() {
        try {
            // Waits for the reCAPTCHA iframe to be present and switches to it
            WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@title, 'reCAPTCHA')]")));
            driver.switchTo().frame(iframe);

            // Waits for the reCAPTCHA checkbox to be clickable and clicks it
            WebElement captcha = wait.until(ExpectedConditions.elementToBeClickable(By.id("recaptcha-anchor")));
            captcha.click();

            // Inform user to complete the CAPTCHA manually
            Reports.generateReport(driver, test, Status.INFO, "Clicked on reCAPTCHA checkbox. Please complete it manually.");

            // Waits for CAPTCHA verification (polls every 2 seconds for up to 30 seconds)
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            // Waits until the CAPTCHA checkbox is verified
            fluentWait.until(ExpectedConditions.attributeToBe(captcha, "aria-checked", "true"));

            // Switches back to the default content
            driver.switchTo().defaultContent();
            Reports.generateReport(driver, test, Status.PASS, "reCAPTCHA verified successfully");
        } catch (Exception e) {
            Reports.generateReport(driver, test, Status.FAIL, "Captcha handling might require manual intervention.");
        }
    }

    public void clickRegisterButton() {
        // Waits until the 'Register' button is clickable and then clicks it
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
        Reports.generateReport(driver, test, Status.INFO, "Clicked on 'Register' button");
    }

    public boolean getConfirmationMessage() {
        // Waits until the title contains "My Courses" to confirm successful registration
        boolean isDisplayed = wait.until(ExpectedConditions.titleContains("My Courses"));
        
        if (isDisplayed) {
            Reports.generateReport(driver, test, Status.PASS, "Sign-up successful, redirected to 'My Courses' page.");
        } else {
            Reports.generateReport(driver, test, Status.FAIL, "Sign-up failed.");
        }
        
        return isDisplayed;
    }
}