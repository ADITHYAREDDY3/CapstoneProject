package Hooks;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utils.Base;

public class Hooks extends Base {
    static WebDriver driver;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeAll
    public static void beforeAll() {
        try {
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myReport.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void afterAll() {
        extent.flush();
        // Quit the browser after all tests finish
        if (Base.driver != null) {
            Base.driver.quit();
            Base.driver = null;
        }
    }

    @Before
    public void before(Scenario scenario) {
        test = extent.createTest(scenario.getName()); // Create test in ExtentReports
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Scenario failed: " + scenario.getName());
        } else {
            test.pass("Scenario passed: " + scenario.getName());
        }
    }

    //Combined Home Page Hooks
    @Before("@HomePage or @PracticePage or @UserAuth or @shoppingpage")
    public void setUpForHomePage(Scenario scenario) {
        System.out.println("Executing Home Page Scenario: " + scenario.getName());
        test.info("Starting Home Page Scenario: " + scenario.getName());

        if (Base.driver == null) {
            getBrowser();
        }
        getUrl();
    }

    //All Courses Page Hook
    @Before("@allCourses")
    public void setUpForAllCoursesPage(Scenario scenario) {
        System.out.println("Executing Scenario: " + scenario.getName());
        test.info("Starting All Courses Page scenario: " + scenario.getName());

        if (Base.driver == null) {
            getBrowser();
        }
        Base.driver.get("https://www.letskodeit.com/courses");
    }

	public static WebDriver getDriver() {
		// TODO Auto-generated method stub
		getBrowser();
		return null;
	}
}
