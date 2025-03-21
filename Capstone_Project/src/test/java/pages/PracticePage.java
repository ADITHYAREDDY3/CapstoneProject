package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.Reports;

public class PracticePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest test;

	// PracticePage constructor
	public PracticePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.test = test;
	}

	// Locators
	private By bmwRadio = By.id("bmwradio");
	private By benzRadio = By.id("benzradio");
	private By hondaRadio = By.id("hondaradio");
	private By bmwCheckbox = By.id("bmwcheck");
	private By benzCheckbox = By.id("benzcheck");
	private By hondaCheckbox = By.id("hondacheck");
	private By openWindowButton = By.id("openwindow");
	private By openTabLink = By.id("opentab");
	private By carDropdown = By.id("carselect");
	private By multipleSelectDropdown = By.id("multiple-select-example");
	private By disableButton = By.id("disabled-button");
	private By enableButton = By.id("enabled-button");
	private By inputField = By.id("enabled-example-input");
	private By hideButton = By.id("hide-textbox");
	private By showButton = By.id("show-textbox");
	private By displayedText = By.id("displayed-text");
	private By nameInput = By.id("name");
	private By alertButton = By.id("alertbtn");
	private By confirmButton = By.id("confirmbtn");
	private By mouseHoverButton = By.id("mousehover");
	private By topOption = By.xpath("//div[@class='mouse-hover-content']/a[text()='Top']");
	private By reloadOption = By.xpath("//div[@class='mouse-hover-content']/a[text()='Reload']");
	private By tableRows = By.xpath("//table[@id='product']/tbody/tr");
	private By autoSuggestField = By.id("autosuggest");
	private By autoSuggestResults = By.cssSelector(".ui-autocomplete li a");
	private By iframe = By.id("courses-iframe");

	private By homeMenu = By.linkText("HOME");
	private By allCourses = By.linkText("ALL COURSES");
	private By interview = By.linkText("INTERVIEW");
	private By support = By.linkText("SUPPORT");
	private By blog = By.linkText("BLOG");

	// Selectng radio button method
	public void selectRadioButton(String car) {
		switch (car.toLowerCase()) {
		case "bmw":
			driver.findElement(bmwRadio).click();
			Reports.generateReport(driver, test, Status.PASS, "BMW radio button selected");
			break;
		case "benz":
			driver.findElement(benzRadio).click();
			Reports.generateReport(driver, test, Status.PASS, "Benz radio button selected");
			break;
		case "honda":
			driver.findElement(hondaRadio).click();
			Reports.generateReport(driver, test, Status.PASS, "Honda radio button selected");
			break;
		default:
			throw new IllegalArgumentException("Invalid car option: " + car);
		}
	}

	// checking radio button is selected
	public boolean isRadioButtonSelected(String car) {
		boolean isSelected = false;
		try {
			switch (car.toLowerCase()) {
			case "bmw":
				isSelected = driver.findElement(bmwRadio).isSelected();
				break;
			case "benz":
				isSelected = driver.findElement(benzRadio).isSelected();
				break;
			case "honda":
				isSelected = driver.findElement(hondaRadio).isSelected();
				break;
			default:
				Reports.captureScreenshot(driver, "InvalidCarOption");
				throw new IllegalArgumentException("Invalid car option: " + car);
			}
			Reports.generateReport(driver, test, Status.PASS, car + " radio button selection status: " + isSelected);
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Failed to check radio button selection for " + car + ". Error: " + e.getMessage());
			Reports.captureScreenshot(driver, "isRadioButtonSelected_Failed");
		}
		return isSelected;
	}

	// For selecting checkbox
	public void selectCheckbox(String car) {
		WebElement checkbox = getCheckboxElement(car);
		if (!checkbox.isSelected()) {
			checkbox.click();
			Reports.generateReport(driver, test, Status.PASS, car + " checkbox selected");
		}
	}

	// deselect checkbox
	public void deselectCheckbox(String car) {
		WebElement checkbox = getCheckboxElement(car);
		if (checkbox.isSelected()) {
			checkbox.click();
			Reports.generateReport(driver, test, Status.PASS, car + " checkbox deselected");
		}
	}

	// validating is isCheckboxSelected
	public boolean isCheckboxSelected(String car) {
		try {
			WebElement checkbox = getCheckboxElement(car);
			boolean isSelected = checkbox.isSelected();
			Reports.generateReport(driver, test, Status.PASS, car + " checkbox is selected.");
			return isSelected;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Error verifying " + car + " checkbox selection. Error: " + e.getMessage());
			return false;
		}
	}

	private WebElement getCheckboxElement(String car) {
		try {
			switch (car.toLowerCase()) {
			case "bmw":
				return driver.findElement(bmwCheckbox);
			case "benz":
				return driver.findElement(benzCheckbox);
			case "honda":
				return driver.findElement(hondaCheckbox);
			default:
				Reports.generateReport(driver, test, Status.FAIL, car + " CheckboxElement not An option");
				throw new IllegalArgumentException("Invalid car option: " + car);
			}
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, car + " CheckboxElement not An option");
			throw new RuntimeException("Error retrieving checkbox for car: " + car, e);
		}
	}

	// clicking the open window button
	public void clickOpenWindowButton() {
		driver.findElement(openWindowButton).click();
		Reports.generateReport(driver, test, Status.INFO, "clicked on the new window");
	}

	// Switching to new window
	public void switchToNewWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for (String window : allWindows) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				Reports.generateReport(driver, test, Status.INFO, "opened the new window");
				break;
			}
		}
	}

	// validating if new window is opened
	public boolean isNewWindowOpened() {
		try {
			boolean isOpened = driver.getWindowHandles().size() > 1;

			if (isOpened) {
				Reports.generateReport(driver, test, Status.PASS, "New window is opened.");
			} else {
				Reports.generateReport(driver, test, Status.FAIL, "New window is NOT opened.");
			}
			return isOpened;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Error while verifying new window. Error: " + e.getMessage());
			return false;
		}
	}

	// if new window is opened the close
	public void closeNewWindowAndSwitchBack() {
		String mainWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(mainWindow);
				driver.close();
				Reports.generateReport(driver, test, Status.PASS, "New window closed and switched back");
				driver.switchTo().window(window);
				break;
			}
		}
	}

	// clicking the tab button
	public void clickOpenTabLink() {
		driver.findElement(openTabLink).click();
		test.info("Open Tab link clicked");
	}

	// Switch To new Tab
	public void switchToNewTab() {
		try {
			String mainTab = driver.getWindowHandle();
			Set<String> allTabs = driver.getWindowHandles();
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));

			for (String tab : allTabs) {
				if (!tab.equals(mainTab)) {
					driver.switchTo().window(tab);
					Reports.generateReport(driver, test, Status.PASS, "Switched to new tab");
					return;
				}
			}
			Reports.generateReport(driver, test, Status.FAIL, "SwitchToNewTab_Failed");
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "SwitchToNewTab_Error");
			throw e;
		}
	}

	public boolean isNewTabOpened() {
		try {
			boolean isOpened = driver.getWindowHandles().size() > 1;
			if (isOpened) {
				Reports.generateReport(driver, test, Status.PASS, "New tab is opened.");
			} else {
				Reports.generateReport(driver, test, Status.FAIL, "New tab is NOT opened.");
			}
			return isOpened;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Error while verifying new tab: " + e.getMessage());
			return false;
		}
	}

	public void closeNewTabAndSwitchBack() {
		try {
			String mainTab = driver.getWindowHandle();
			Set<String> allTabs = driver.getWindowHandles();

			for (String tab : allTabs) {
				if (!tab.equals(mainTab)) {
					driver.switchTo().window(mainTab);
					driver.close();
					Reports.generateReport(driver, test, Status.INFO, "New tab closed and switched back");
					driver.switchTo().window(tab);
					return;
				}
			}
			Reports.generateReport(driver, test, Status.FAIL, "CloseNewTab_Failed");
			throw new RuntimeException("Failed to close new tab and switch back");
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Error while closing new tab: " + e.getMessage());
			throw e;
		}
	}

	public void selectCarFromDropdown(String car) {
		// TODO Auto-generated method stub
		Select dropdown = new Select(driver.findElement(carDropdown));
		dropdown.selectByVisibleText(car);

	}

	// Asserting the option
	public String getSelectedCarFromDropdown() {
		// TODO Auto-generated method stub
		Select dropdown = new Select(driver.findElement(carDropdown));
		return dropdown.getFirstSelectedOption().getText();
	}

	// Select Multiple Options
	public void selectMultipleOptions(String... options) {
		Select select = new Select(driver.findElement(multipleSelectDropdown));
		for (String option : options) {
			select.selectByVisibleText(option);
		}
	}

	// Deselect a specific option
	public void deselectOption(String option) {
		Select select = new Select(driver.findElement(multipleSelectDropdown));
		select.deselectByVisibleText(option);
	}

	// Get selected options
	public List<String> getSelectedOptions() {
		Select select = new Select(driver.findElement(multipleSelectDropdown));
		return select.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList());
	}

	// Click "Disable" button
	public void clickDisableButton() {
		driver.findElement(disableButton).click();
	}

	// Click "Enable" button
	public void clickEnableButton() {
		driver.findElement(enableButton).click();
	}

	// Check if input field is disabled
	public boolean isInputFieldDisabled() {
		WebElement input = driver.findElement(inputField);
		return !input.isEnabled();
	}

	// Check if input field is enabled
	public boolean isInputFieldEnabled() {
		WebElement input = driver.findElement(inputField);
		return input.isEnabled();
	}

	public void clickHideButton() {
		// Clicks on the "Hide" button to hide the input field
		driver.findElement(hideButton).click();
		Reports.generateReport(driver, test, Status.INFO, "Clicked on Hide button");
	}

	public void clickShowButton() {
		// Clicks on the "Show" button to display the input field
		driver.findElement(showButton).click();
		Reports.generateReport(driver, test, Status.INFO, "Clicked on Show button");
	}

	public boolean isInputFieldDisplayed() {
		try {
			boolean isDisplayed = driver.findElement(displayedText).isDisplayed();
			Reports.generateReport(driver, test, Status.PASS, "Input field is displayed.");
			return isDisplayed;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Error checking input field visibility. Error: " + e.getMessage());
			return false;
		}
	}

	public void enterName(String name) {
		// Enters the provided name into the input field
		driver.findElement(nameInput).sendKeys(name);
		Reports.generateReport(driver, test, Status.INFO, "Entered name: " + name);
	}

	public void clickAlertButton() {
		// Clicks the alert button to trigger an alert pop-up
		driver.findElement(alertButton).click();
		Reports.generateReport(driver, test, Status.INFO, "Clicked on Alert button");
	}

	public void clickConfirmButton() {
		// Clicks the confirm button to trigger a confirmation alert
		driver.findElement(confirmButton).click();
		Reports.generateReport(driver, test, Status.INFO, "Clicked on Confirm button");
	}

	public String getAlertText() {
		// Retrieves the text displayed on the alert pop-up
		String alertText = driver.switchTo().alert().getText();
		Reports.generateReport(driver, test, Status.INFO, "Alert text: " + alertText);
		return alertText;
	}

	public void acceptAlert() {
		// Accepts (OK) the alert pop-up
		driver.switchTo().alert().accept();
		Reports.generateReport(driver, test, Status.INFO, "Accepted the alert");
	}

	public void dismissAlert() {
		// Dismisses (Cancel) the alert pop-up
		driver.switchTo().alert().dismiss();
		Reports.generateReport(driver, test, Status.INFO, "Dismissed the alert");
	}

	public void clickMouseHoverButton() {
		// Performs a mouse hover action over the "Mouse Hover" button
		WebElement hoverButton = driver.findElement(mouseHoverButton);
		Actions action = new Actions(driver);
		action.moveToElement(hoverButton).perform();
		Reports.generateReport(driver, test, Status.INFO, "Hovered over the Mouse Hover button");
	}

	public boolean isMouseHoverOptionsVisible() {
		try {
			WebElement hoverContent = driver.findElement(By.className("mouse-hover-content"));
			boolean isDisplayed = hoverContent.isDisplayed();
			Reports.generateReport(driver, test, Status.PASS, "Mouse Hover options are visible.");

			return isDisplayed;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Error checking Mouse Hover options visibility. Error: " + e.getMessage());
			return false;
		}
	}

	public void clickMouseHoverOption(String option) {
		// Clicks on a specific option in the mouse hover menu ("Top" or "Reload")
		WebElement elementToClick;
		if (option.equalsIgnoreCase("Top")) {
			elementToClick = driver.findElement(topOption);
		} else if (option.equalsIgnoreCase("Reload")) {
			elementToClick = driver.findElement(reloadOption);
		} else {
			throw new IllegalArgumentException("Invalid option: " + option);
		}

		Actions action = new Actions(driver);
		action.moveToElement(elementToClick).click().perform();
		test.info("Clicked on Mouse Hover option: " + option);
	}

	public List<List<String>> getTableData() {
		// Extracts all data from a table and returns it as a list of lists
		List<List<String>> tableData = new ArrayList<>();
		try {
			List<WebElement> rows = driver.findElements(tableRows);
			for (int i = 1; i < rows.size(); i++) { // Skipping the header row
				List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
				List<String> rowData = new ArrayList<>();
				for (WebElement column : columns) {
					rowData.add(column.getText().trim());
				}
				tableData.add(rowData);
			}
			Reports.generateReport(driver, test, Status.PASS, "Extracted table data successfully: " + tableData);
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL, "Failed to extract table data. Error: " + e.getMessage());
		}
		return tableData;
	}

	public String getCoursePrice(String courseName) {
		// Searches for a course in the table and returns its price
		try {
			List<WebElement> rows = driver.findElements(tableRows);
			for (int i = 1; i < rows.size(); i++) {
				WebElement courseCell = rows.get(i).findElement(By.className("course-name"));
				WebElement priceCell = rows.get(i).findElement(By.className("price"));
				if (courseCell.getText().trim().equalsIgnoreCase(courseName)) {
					Reports.generateReport(driver, test, Status.PASS,
							"Found course: " + courseName + " with price: " + priceCell.getText().trim());
					return priceCell.getText().trim();
				}
			}
			Reports.generateReport(driver, test, Status.FAIL, "Course not found: " + courseName);
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Error retrieving course price for: " + courseName + ". Error: " + e.getMessage());
			Reports.captureScreenshot(driver, "CoursePriceRetrievalFailed");
		}
		return "Empty Table";
	}

	public boolean isUrlSameAfterPageReload(String previousUrl) {
		try {
			boolean isSame = wait.until(ExpectedConditions.urlToBe(previousUrl));

			if (isSame) {
				Reports.generateReport(driver, test, Status.PASS,
						"URL remained the same after page reload: " + previousUrl);
			} else {
				Reports.generateReport(driver, test, Status.FAIL, "URL changed after page reload. Expected: "
						+ previousUrl + ", but found: " + driver.getCurrentUrl());
			}
			return isSame;
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					"Error while verifying URL after page reload. Error: " + e.getMessage());
			return false;
		}
	}

	// Enter text in auto-suggest field
	public void enterTextInAutoSuggest(String text) throws TimeoutException {
		WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(autoSuggestField));
		inputField.clear();
		inputField.sendKeys(text);
		Reports.generateReport(driver, test, Status.INFO, "Entered text in auto-suggest field: " + text);
	}

	// Get list of auto-suggest results
	public List<WebElement> getAutoSuggestResults() throws TimeoutException {
		List<WebElement> suggestions = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(autoSuggestResults));
		Reports.generateReport(driver, test, Status.INFO, "Retrieved " + suggestions.size() + " auto-suggest results.");
		return suggestions;
	}

	// Select a suggestion by visible text
	public void selectAutoSuggestOption(String optionText) throws TimeoutException {
		List<WebElement> suggestions = getAutoSuggestResults();
		if (suggestions == null || suggestions.isEmpty()) {
			Reports.generateReport(driver, test, Status.FAIL, "No auto-suggest options found.");
			return;
		}

		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().equalsIgnoreCase(optionText)) {
				wait.until(ExpectedConditions.elementToBeClickable(suggestion)).click();
				Reports.generateReport(driver, test, Status.PASS, "Selected auto-suggest option: " + optionText);
				return;
			}
		}
		Reports.generateReport(driver, test, Status.FAIL, "Suggestion not found: " + optionText);
	}

	// Get the entered text in the input field
	public String getEnteredText() throws TimeoutException {
		WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(autoSuggestField));
		String enteredText = inputField.getAttribute("value");
		Reports.generateReport(driver, test, Status.INFO, "Current text in search field: " + enteredText);
		return enteredText;
	}

	// Iframe validation
	public void navigateToPracticePage() {
		Reports.generateReport(driver, test, Status.INFO, "Navigated to Practice Page.");
	}

	public void switchToIframe() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
		Reports.generateReport(driver, test, Status.INFO, "Switched to iframe.");
	}

	public String getIframeTitle() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return document.title;");
	}

	public boolean isSectionVisible(String sectionName) {
		By locator = getLocator(sectionName);
		if (locator == null) {
			Reports.generateReport(driver, test, Status.FAIL, "Invalid section name: " + sectionName);
			return false;
		}
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Reports.generateReport(driver, test, Status.PASS, sectionName + " section is visible.");
			return element.isDisplayed();
		} catch (Exception e) {
			Reports.generateReport(driver, test, Status.FAIL,
					sectionName + " section is NOT visible. Error: " + e.getMessage());
			return false;
		}
	}

	// Helper method to get locator based on section name
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
