package stepDefinations;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import Hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.HomePage;
import utils.Base;

public class HomePageSteps {
   // Get WebDriver from Base
    HomePage homePage;
	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;

    @Given("I open the browser and navigated to home page")
    public void i_open_the_browser() {
        homePage = new HomePage(driver, test); 
        homePage.navigateToHomePage();
        System.out.println("The browser is opened and Home Page is initialized.");
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        String actualTitle = homePage.getPageTitle();
        System.out.println("Actual Page Title: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
    }

    @Then("the following sections should be visible:")
    public void the_following_sections_should_be_visible(DataTable dataTable) {
        List<String> sections = dataTable.asList(); // Convert DataTable to List
        for (String section : sections) {
            boolean isVisible = homePage.isSectionVisible(section);
            Assert.assertTrue(isVisible, "Section '" + section + "' is not visible");
        }
    }


}
