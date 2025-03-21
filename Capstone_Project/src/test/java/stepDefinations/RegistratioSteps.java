package stepDefinations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;

import Hooks.Hooks;
import io.cucumber.java.en.*;
import pages.HomePage;
import pages.SignUpPage;
import utils.Base;

public class RegistratioSteps {
    HomePage homePage;
    SignUpPage signUpPage;
    WebDriver driver = Base.driver;
    ExtentTest test = Hooks.test;

    @Given("User is on the Letskodeit Practise homepage")
    public void user_is_on_the_letskodeit_practise_homepage() {
        signUpPage = new SignUpPage(driver, test);
    }

    @When("the user clicks the Sign In link")
    public void the_user_clicks_the_sign_in_link() {
        signUpPage.clickSignInButton();
    }

    @Then("the user is in the login page")
    public void the_user_is_in_the_login_page() {
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "User is not on the login page");
    }

    @Then("the user clicks on the register link")
    public void the_user_clicks_on_the_register_link() {
        signUpPage.clickSignUpButton();
    }

    @Then("the registration page is displayed on the screen")
    public void the_registration_page_is_displayed_on_the_screen() {
//        Assert.assertTrue(driver.getTitle().contains("Register"), "Registration page is not displayed");
    }

    @When("the user enters {string} and {string} and {string} and {string} and {string}")
    public void the_user_enters_and_and_and_and(String firstName, String lastName, String email, String password, String confirmPassword) {
        signUpPage.enterName(firstName, lastName);
        signUpPage.enterEmail(email);
        signUpPage.enterPassword(password);
        signUpPage.enterConfirmPassword(confirmPassword);
    }

    @When("the user ticks the captcha checkbox")
    public void the_user_ticks_the_captcha_checkbox() {
        signUpPage.handleCaptcha(); 
    }

    @When("the user clicks the signup button")
    public void the_user_clicks_the_signup_button() {
//        signUpPage.clickRegisterButton();
    }

    @Then("the user sees the My Courses page")
    public void the_user_sees_the_my_courses_page() {
//        Assert.assertTrue(signUpPage.getConfirmationMessage(), "Registration was not successful");
    }
}