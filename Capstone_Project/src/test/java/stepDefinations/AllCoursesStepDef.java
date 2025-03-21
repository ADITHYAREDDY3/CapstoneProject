package stepDefinations;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import pages.AllCoursesPage;
import   utils.Base;
import Hooks.Hooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllCoursesStepDef {
	WebDriver driver=Base.driver;
	ExtentTest test= Hooks.test;
	AllCoursesPage allCoursePage=new AllCoursesPage(driver,test);
	@Given("user is on the All Courses page")
	public void user_is_on_the_all_courses_page() {
	  boolean actResult=allCoursePage.allCoursePage();
	  Assert.assertTrue(actResult);
	}
	@When("user selects {string} from the category field")
	public void user_selects_from_the_category_field(String category) { 
	    allCoursePage.selectCategory(category);
	}
	@And("clicks on the search button")
	public void clicks_on_the_search_button() {
	   allCoursePage.clickOnsearchButton();
	}
	@Then("courses related to {string} should be displayed")
	public void courses_related_to_should_be_displayed(String category) {
	  
	   Assert.assertTrue( allCoursePage.isCategoryDisplayed(category));
	}
	@When("user enters {string} in the search field")
	public void user_enters_in_the_search_field(String course) {
	    allCoursePage.enterCourseName(course);
	}
	@Then("courses related to course {string} should be displayed")
	public void courses_related_to_course_should_be_displayed(String course) {
	    Assert.assertTrue(allCoursePage.isCourseDisplayed(course));
	}
	
	@And("user searches for {string} in search field")
	public void user_searches_for_in_search_field(String course) {
		allCoursePage.enterCourseName(course);
	}
	@Then("relevant courses should be displayed")
	public void relevant_courses_should_be_displayed() {
	    Assert.assertTrue(allCoursePage.areRelevantCoursesDisplayed());
	}

}