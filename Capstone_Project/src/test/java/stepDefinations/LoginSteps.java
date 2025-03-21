package stepDefinations;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Hooks.Hooks;
import pages.HomePage;
import pages.LoginPage;
import utils.Base;
import utils.Reports;

public class LoginSteps {
	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	LoginPage loginPage = new LoginPage(driver, test);
	HomePage homePage = new HomePage(driver, test);

	@When("the user enters {string} and {string}")
	public void the_user_enters_and(String email, String password) {
		loginPage.enterLoginCredentials(email, password);
	}

	@And("the user clicks the login button for a successful login")
	public void the_user_clicks_the_login_button() {
		loginPage.clickLogin();
	}

	@Then("the user is redirected to the My Courses page")
	public void the_user_is_redirected_to_the_My_Courses_page() {
//		Assert.assertTrue(loginPage.isMyCoursesPageDisplayed());
	}

	@When("the user clicks the Logout")
	public void the_user_clicks_the_logout() {
//		loginPage.clickOnMenuAndLogout();
	}

	@Then("the user is redirected to the homepage")
	public void the_user_is_redirected_to_the_homepage() {
//        Assert.assertTrue(homePage.verifyHomePage());
	}

}
