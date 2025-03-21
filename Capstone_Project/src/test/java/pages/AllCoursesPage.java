package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.Reports;


public class AllCoursesPage {
	private WebDriver driver;
	private WebDriverWait wait;
	private ExtentTest test;
	
	public AllCoursesPage(WebDriver driver,ExtentTest test) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		this.test=test;
	}
	
	//locators
	private By allCoursesPage=By.linkText("ALL COURSES");
	private By categoryDropDown=By.name("categories");
	private By searchField=By.xpath("//input[@id='search']");
	private By searchButton=By.xpath("//button[@type='submit']");
	private By courseTitles=By.xpath("//div[contains(@class, 'zen-course-title')]");
	//checking navigated allcourse page or not
	public boolean allCoursePage() {
		driver.findElement(allCoursesPage).click();
		boolean actResult=true;
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='All Courses']")));
			Reports.generateReport(driver, test, Status.PASS, "User navigated to All courses page");
		}
		catch(TimeoutException e) {
			actResult=false;
			Reports.generateReport(driver, test, Status.FAIL, "user failed to naviagte to all course page "+e.getMessage());
			
		}
		return actResult;
	}
	//selecting category
	public void selectCategory(String category) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryDropDown));
            Select select = new Select(dropdown);
            select.selectByVisibleText(category);
            Thread.sleep(1000);
            Reports.generateReport(driver, test, Status.PASS, "Selected category "+category);
        } catch (Exception e) {
        	Reports.generateReport(driver, test, Status.FAIL, "Failed to select Category");
        }
    }
	//clicking search Button
	public void clickOnsearchButton() {
	    try {
	        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchButton)); 
	        searchBtn.click();
	        Reports.generateReport(driver, test, Status.PASS, "Clicked on Search button");
	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, "Failed to click on Search button");
	    }
	}

	//checking category displayed or not
	public boolean isCategoryDisplayed(String expectedCategory) {
	    try {
	        WebElement categoryLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Category')]")));
	        String actualCategory = categoryLabel.getText().replace("Category :", "").trim();

	        if (actualCategory.equalsIgnoreCase(expectedCategory)) {
	        	Reports.generateReport(driver, test, Status.PASS, "Category is displayed"+actualCategory);
	            return true;
	        } else {
	        	Reports.generateReport(driver, test, Status.FAIL,"Category is not dispalyed"); 
	            return false;
	        }
	    } catch (Exception e) {
	    	Reports.generateReport(driver, test, Status.FAIL, "Failed to verify category: "+e.getMessage());
	        return false;
	    }
	}
	//searching cources in searching field
	public void enterCourseName(String course) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchField));
        Assert.assertTrue(searchBox.isDisplayed());
        searchBox.click();
        searchBox.sendKeys(course);
    }
	//checking courses displayed or not when searching in search filed
	public boolean isCourseDisplayed(String searchedCourse) {
	    try {
	        List<WebElement> courseElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(courseTitles));

	        List<String> actualCourseTitles = new ArrayList<>();

	        for (WebElement course : courseElements) {
	            actualCourseTitles.add(course.getText().trim().toLowerCase());  
	        }
	        boolean isFound = actualCourseTitles.stream()
	                .anyMatch(title -> title.contains(searchedCourse.toLowerCase()));

	        if (isFound) {
	            Reports.generateReport(driver, test, Status.PASS, "Searched course is displeayed correctly");
	            return true;
	        } else {
	            Reports.generateReport(driver, test, Status.FAIL, "searchedCourse is not found");
	            return false;
	        }

	    } catch (Exception e) {
	        Reports.generateReport(driver, test, Status.FAIL, e.getMessage());
	        return false;
	    }
	}
	//checing relevant courses displayed or not choosing both catgory and course search filed
	 public boolean areRelevantCoursesDisplayed() {
	        try {
	            List<WebElement> courseElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(courseTitles));
	            List<String> actualCourseTitles = new ArrayList<>();

	            for (WebElement course : courseElements) {
	                actualCourseTitles.add(course.getText().trim().toLowerCase());
	            }
	            
	            boolean coursesFound = !actualCourseTitles.isEmpty();

	            if (coursesFound) {
	                Reports.generateReport(driver, test, Status.PASS, "Courses are displayed successfully");
	            } else {
	            	Reports.generateReport(driver, test, Status.FAIL, "No courses were found");
	                
	            }
	            return coursesFound;
	        } catch (Exception e) {
	            Reports.generateReport(driver, test, Status.FAIL, e.getMessage());
	            return false;
	        }
	    }



}