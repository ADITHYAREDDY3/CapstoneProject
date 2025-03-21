package stepDefinations;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import Hooks.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.PracticePage;
import utils.Base;

public class PracticePageSteps {

	WebDriver driver = Base.driver;
	ExtentTest test = Hooks.test;
	PracticePage practicePage = new PracticePage(driver, test);

	// Radio Button Steps
	@When("I select the {string} radio button")
	public void i_select_the_radio_button(String car) {
		practicePage.selectRadioButton(car);
	}

	@Then("the {string} radio button should be selected")
	public void the_radio_button_should_be_selected(String car) {
		Assert.assertTrue(practicePage.isRadioButtonSelected(car));
	}
	@When("I select the {string} checkbox")
	public void i_select_the_checkbox(String car) {
		practicePage.selectCheckbox(car);
	}

	@Then("the {string} checkbox should be selected")
	public void the_checkbox_should_be_selected(String car) {
		Assert.assertTrue(practicePage.isCheckboxSelected(car));
	}

	@When("I deselect the {string} checkbox")
	public void i_deselect_the_checkbox(String car) {
		practicePage.deselectCheckbox(car);
	}

	@Then("the {string} checkbox should not be selected")
	public void the_checkbox_should_not_be_selected(String car) {
		Assert.assertFalse(practicePage.isCheckboxSelected(car));
	}

	// Window Handle Steps
	@When("I click the Open Window button")
	public void i_click_the_open_window_button() {
		practicePage.clickOpenWindowButton();
	}

	@Then("a new window should open")
	public void a_new_window_should_open() {
		Assert.assertTrue(practicePage.isNewWindowOpened());
	}

	@When("I switch to the new window")
	public void i_switch_to_the_new_window() {
		practicePage.switchToNewWindow();
	}

	@When("I close the new window and switch back")
	public void i_close_the_new_window_and_switch_back() throws InterruptedException {
		practicePage.closeNewWindowAndSwitchBack();
	}

	
	
	// Tab Handle Steps
	@When("I click the Open Tab link")
	public void i_click_the_open_tab_link() {
		practicePage.clickOpenTabLink();
	}

	@Then("a new tab should open")
	public void a_new_tab_should_open() {
		Assert.assertTrue(practicePage.isNewTabOpened());
	}

	@When("I switch to the new tab")
	public void i_switch_to_the_new_tab() {
		practicePage.switchToNewTab();
	}

	@When("I close the new tab and switch back")
	public void i_close_the_new_tab_and_switch_back() {
		practicePage.closeNewTabAndSwitchBack();
	}

	// Dropdown Selection Steps
	@When("I select {string} from the dropdown")
	public void i_select_from_the_dropdown(String car) {
		practicePage.selectCarFromDropdown(car);
	}

	@Then("{string} should be selected in the dropdown")
	public void should_be_selected_in_the_dropdown(String car) {
		Assert.assertEquals(practicePage.getSelectedCarFromDropdown(), car);
	}

	
	
	
	// Multiple options selection from Multipleselectoption
	@When("I select the following options from the multiple select dropdown:")
	public void iSelectTheFollowingOptions(DataTable dataTable) {
		List<String> options = dataTable.asList();
		practicePage.selectMultipleOptions(options.toArray(new String[0]));
	}

	@When("I deselect {string} from the multiple select dropdown")
	public void iDeselectFromTheMultipleSelectDropdown(String option) {
		practicePage.deselectOption(option);
	}

	@Then("the following options should be selected in the multiple select dropdown:")
	public void theFollowingOptionsShouldBeSelected(DataTable expectedTable) {
		List<String> expectedOptions = expectedTable.asList();
		List<String> selectedOptions = practicePage.getSelectedOptions();
		Assert.assertEquals(expectedOptions, selectedOptions);
	}

	
	
	
	// Enable and disable And hide and show button clicking
	@When("I click on the {string} button")
	public void iClickOnTheButton(String button) {
		switch (button.toLowerCase()) {
		case "disable":
			practicePage.clickDisableButton();
			break;
		case "enable":
			practicePage.clickEnableButton();
			break;
		case "hide":
			practicePage.clickHideButton();
			break;
		case "show":
			practicePage.clickShowButton();
			break;
		case "alert":
			practicePage.clickAlertButton();
			break;
		case "confirm":
			practicePage.clickConfirmButton();
			break;
		default:
			throw new IllegalArgumentException("Invalid button: " + button);
		}
	}

	@Then("the input field should be disabled")
	public void theInputFieldShouldBeDisabled() {
		Assert.assertTrue(practicePage.isInputFieldDisabled(), "Input field is not disabled!");
	}

	@Then("the input field should be enabled")
	public void theInputFieldShouldBeEnabled() {
		Assert.assertTrue(practicePage.isInputFieldEnabled(), "Input field is not enabled!");
	}

	@Then("the input field should be hidden")
	public void theInputFieldShouldBeHidden() {
		Assert.assertFalse(practicePage.isInputFieldDisplayed(), "Input field is still visible!");
	}
	
	@Then("the input field should be visible")
	public void theInputFieldShouldBeVisible() {
		Assert.assertTrue(practicePage.isInputFieldDisplayed(), "Input field is not visible!");
	}
	
	
	
	
	//Alert Validatation

	@When("I enter {string} in the name field")
	public void iEnterInTheNameField(String name) {
		practicePage.enterName(name);
	}

	@Then("an alert should appear with text containing {string}")
	public void anAlertShouldAppearWithTextContaining(String expectedText) {
		String actualText = practicePage.getAlertText();
		Assert.assertTrue(actualText.contains(expectedText), "Alert text does not match!");
		practicePage.acceptAlert();
	}

	@Then("a confirmation alert should appear")
	public void aConfirmationAlertShouldAppear() {
		String actualText = practicePage.getAlertText();
		Assert.assertNotNull(actualText, "No confirmation alert found!");
	}

	@And("I accept the confirmation alert")
	public void iAcceptTheConfirmationAlert() {
		practicePage.acceptAlert();
	}

	
	
	
	//Hover button VAlidation
	@When("I Hover on the {string} button")
	public void HoverOnTheButton(String button) {
		if (button.equalsIgnoreCase("Mouse Hover")) {
			practicePage.clickMouseHoverButton();
		} else {
			throw new IllegalArgumentException("Invalid button: " + button);
		}
	}

	@Then("the mouse hover options should be visible")
	public void theMouseHoverOptionsShouldBeVisible() {
		Assert.assertTrue(practicePage.isMouseHoverOptionsVisible(), "Mouse hover options are not visible!");
	}

	@When("I click on the {string} option")
	public void iClickOnTheOption(String option) {
		practicePage.clickMouseHoverOption(option);
	}

	@Then("the page should scroll to the top")
	public void thePageShouldScrollToTheTop() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		boolean isAtTop = wait.until(driver -> {// It will wait until the script is complete then verify
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Double scrollTop = (Double) js.executeScript("return window.scrollY;");
			System.out.println("Current Scroll Position: " + scrollTop); // Debugging log
			return scrollTop <= 121.5999984741211;
		});
		Assert.assertTrue(isAtTop, "Page did not scroll to the top!");
	}

	@Then("the page should reload")
	public void thePageShouldReload() {
		practicePage.isUrlSameAfterPageReload("https://www.letskodeit.com/practice");
	}
	
	
	
	//the Table validation
	@Then("the table should contain the following courses:")
	public void theTableShouldContain(DataTable expectedTable) {
		List<List<String>> expectedData = expectedTable.asLists();
		List<List<String>> actualData = practicePage.getTableData();
		System.out.println("Actual Table Data: " + actualData);
		System.out.println("Expected Table Data" + expectedData);
		Assert.assertEquals(actualData, expectedData, "Table data does not match!");
	}

	@When("I search for the course {string} in table")
	public void iSearchForTheCourse(String courseName) {
		test.info("Searching for course: " + courseName);
	}

	@Then("the price should be {string}")
	public void thePriceShouldBe(String expectedPrice) {
		String actualPrice = practicePage.getCoursePrice("Python Programming Language");
		Assert.assertEquals(actualPrice, expectedPrice, "Course price does not match!");
	}

	
	
	
	
	
	
	//Auto suggest validation
	@When("I enter {string} in the search field")
	public void iEnterTextInSearchField(String text) throws TimeoutException {
		practicePage.enterTextInAutoSuggest(text);
	}

	@Then("I should see the suggestions related to {string}")
	public void iShouldSeeTheSuggestions(String expectedText) throws TimeoutException{
		boolean isSuggestionFound = practicePage.getAutoSuggestResults().stream()
				.anyMatch(suggestion -> suggestion.getText().contains(expectedText));
		Assert.assertTrue(isSuggestionFound, "Auto-suggest did not return expected results.");
	}

	@Then("I select {string} from the suggestions")
	public void iSelectFromSuggestions(String optionText)throws TimeoutException {
		practicePage.selectAutoSuggestOption(optionText);
	}

	@Then("the search field should contain {string}")
	public void theSearchFieldShouldContain(String expectedText)throws TimeoutException {
		Assert.assertEquals(practicePage.getEnteredText(), expectedText, "Selected value did not appear in the field.");
	}
	
	
	
	//iframe valdation
	 @Given("I am on the Practice Page")
	 public void i_am_on_the_practice_page() {
	     practicePage.navigateToPracticePage();
	 }

	 @And("I switch to the iframe")
	 public void i_switch_to_the_iframe() {
	     practicePage.switchToIframe();
	 }

	 @Then("I verify the iframe page title")
	 public void i_verify_the_iframe_page_title() {
	     String actualTitle = practicePage.getIframeTitle();
	     String expectedTitle = "All Courses"; // Replace with the actual expected title
	     Assert.assertEquals(actualTitle, expectedTitle, "Iframe page title does not match!");
	 }

	 @Then("the following sections should be visible in the iframe:")
	 public void the_following_sections_should_be_visible(DataTable sectionsTable) {
	     List<String> sections = sectionsTable.asList();
	     for (String section : sections) {
	         Assert.assertTrue(practicePage.isSectionVisible(section), section + " section is NOT visible!");
	     }
	 }

}
