package stepDefinations;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;

import Hooks.Hooks;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.Base;

public class InavlidLoginSteps {
	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	LoginPage loginPage = new LoginPage(driver, test);
	@When("the user enters WongCrendtials {string} and {string}")
	public void theUserEntersWrongCredentials(String email, String password) {
	    loginPage.enterLoginCredentials(email, password);
	}

	@When("the user clicks the login button for an unsuccessful login")
	public void theUserClicksTheLoginButton() {
	  loginPage.clickLogin();
	}

	@Then("the login page will show {string} in the display")
	public void the_LoginPage_Will_Show_ErrorMessage(String expectedMessage) {
		Assert.assertTrue(loginPage.isErrorMsgVisible(), "Error message is not displayed");
	}

	@When("the user clicks the Forget Password button")
	public void theUserClicksForgetPasswordButton() {
		loginPage.clickOnForgetPassword();
	}

	@Then("the user is redirected to the Password RestPage")
	public void theUserIsRedirectedToPasswordResetPage() {
		Assert.assertTrue(loginPage.isVerifyPasswordresetPageVisible());
	}
}
