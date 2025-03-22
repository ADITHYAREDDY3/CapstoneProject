# Selenium Automation Project

## Project Overview
This project automates the testing of the [Let's Kode It](https://www.letskodeit.com/practice) website using Selenium WebDriver, Cucumber (BDD), TestNG, and Extent Reports. The automation covers various functionalities, including login, course search, category filtering, shopping cart operations, and more.

## Tech Stack
- **Programming Language**: Java
- **Test Automation Framework**: Selenium WebDriver
- **BDD Framework**: Cucumber
- **Test Runner**: TestNG / JUnit
- **Reporting**: Extent Reports
- **Build Tool**: Maven
- **CI/CD**: Jenkins
- **Browser Compatibility**: Chrome, Firefox
- **Configuration Management**: Properties File

## Project Structure
```
project-root/
├── src/main/java/com/automation/pages/   # Page Object Model (POM) classes
├── src/main/java/com/automation/utils/   # Utility classes
├── src/test/java/com/automation/steps/   # Cucumber Step Definitions
├── src/test/java/com/automation/runners/ # TestNG & Cucumber Test Runners
├── src/test/resources/features/          # Cucumber Feature Files
├── src/test/resources/config/            # Properties File (config.properties)
├── reports/                              # Extent Reports
├── pom.xml                               # Maven dependencies
└── README.md                             # Project Documentation
```

## Features Automated
### 1. **Login & Authentication**
- User login with valid credentials
- Invalid login error message validation
- Password reset functionality

### 2. **Practice Page Automation**
- Radio buttons and checkboxes selection
- Element Displayed Example (Show/Hide functionality)
- Handling JavaScript alerts and popups

### 3. **All Courses Page**
- Filtering courses by category
- Searching for courses

### 4. **Shopping Cart Automation**
- Adding items to the cart
- Removing items from the cart
- Proceeding to checkout

### 5. **jQuery UI Droppable Page**
- Validating drag-and-drop functionality
- Click and hold release actions
- Offset-based drag-and-drop

## Setup Instructions
### Prerequisites
- Install **Java (JDK 11+)**
- Install **Maven**
- Install **ChromeDriver** and **GeckoDriver** for browser testing
- Install **IntelliJ IDEA** or **Eclipse**

### Steps to Set Up
1. Clone the repository:
   ```sh
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```sh
   cd project-root
   ```
3. Install dependencies:
   ```sh
   mvn clean install
   ```
4. Run tests:
   - For TestNG:
     ```sh
     mvn test
     ```
   - For Cucumber:
     ```sh
     mvn clean test -Dcucumber.options="src/test/resources/features"
     ```

### Running Tests on Different Browsers
Modify the `config.properties` file to set the browser:
```properties
browser=chrome  # or firefox
```

## Reporting
- After execution, Extent Reports are generated in the `reports/` directory.
- Open `ExtentReport.html` in a browser to view the test execution results.

## CI/CD Integration
- The project is integrated with Jenkins for automated execution.
- Add a new Jenkins job, configure Maven goals (`clean test`), and trigger builds periodically or on code commits.

## Contributing
- Fork the repository and create a new branch for enhancements.
- Follow best practices for Selenium automation and POM.
- Submit a pull request with detailed documentation.

## License
This project is licensed under the MIT License.

