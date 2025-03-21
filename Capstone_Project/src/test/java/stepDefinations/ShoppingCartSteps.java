package stepDefinations;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import Hooks.Hooks;
import pages.ShoppingCartPage;
import utils.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingCartSteps {
    WebDriver driver = Base.driver;
    ExtentTest test = Hooks.test;
    private ShoppingCartPage shoppingCartPage;

    @Given("User is on the practice page")
    public void userIsOnPracticePage() {
        shoppingCartPage = new ShoppingCartPage(driver,test);
    }

    @When("User navigates to eCommerce Practice Page")
    public void userNavigatesToEcommercePractice() {
        shoppingCartPage.goToEcommercePractice();
        shoppingCartPage.switchToNewWindow();
    }
    @When("User clicks on {string}")
    public void userClicksOn(String button) {
        shoppingCartPage.clickShopNow();
    }

    @When("User selects a product")
    public void userSelectsAProduct() {
        shoppingCartPage.selectProduct();
    }

    @When("User selects size {string}")
    public void userSelectsSize(String size) {
        shoppingCartPage.selectSize();
    }
    @When("User adds the product to the bag")
    public void userAddsProductToBag() {
        shoppingCartPage.addToBag();
    }

    @When("User proceeds to checkout")
    public void userProceedsToCheckout() {
        shoppingCartPage.checkout();
    }

    @Then("User should see {string} page")
    public void userShouldSeePage(String expectedPage) {
        assertTrue(shoppingCartPage.isMyBagDisplayed(), expectedPage + " page is not displayed!");
    }
}