# Playwright Java Test Automation Framework

Educational project for **QA Automation with Playwright and Java**. Demonstrates Page Object Model (POM), best practices, and modern approach to UI automation testing.

## 🚦 Status

![Tests](https://img.shields.io/badge/tests-passing-brightgreen)
![Java](https://img.shields.io/badge/java-17+-blue)
![Playwright](https://img.shields.io/badge/playwright-1.55.0-purple)
[![Java CI with Maven](https://github.com/DanailZahariev/DemoSitesAutomationPW/actions/workflows/maven.yml/badge.svg)](https://github.com/DanailZahariev/DemoSitesAutomationPW/actions/workflows/maven.yml)


---

## 📋 Table of Contents

- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Examples](#examples)
- [Best Practices](#best-practices)

---

## 🛠️ Technologies

- **Java 17+**
- **Playwright 1.55.0** - Modern browser automation
- **TestNG** - Test framework
- **SLF4J + Logback** - Logging
- **Maven** - Build tool

---
## ⚙️ Installation

### 1. Prerequisites
- Java 17 or higher
- Maven 3.8+

### 2. Clone repository
```bash
git clone https://github.com/DanailZahariev/DemoSitesAutomationPW.git
cd DemoSitesAutomationPW
```

### 3. Install dependencies
```bash
mvn clean install
```

### 4. Install Playwright browsers
```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

---

## 🔧 Configuration

Edit `src/main/resources/config.properties`:
```properties
# Browser Configuration
browser=chrome
headless=true
viewport.width=1920
viewport.height=1080
timeout=30000

# Application URLs
base.url=https://demoqa.com/

# Screenshot Configuration
screenshot.dir=target/surefire-reports/screenshots
screenshot.onFailure=true
```

---

## 🚀 Running Tests

### All tests
```bash
mvn test
```

### Specific test class
```bash
mvn test -Dtest=LinksTest
```

### Specific test method
```bash
mvn test -Dtest=LinksTest#testBadRequestLink
```

### With different browser
```bash
mvn test -Dbrowser=firefox
```

### Headless mode
```bash
mvn test -Dheadless=true
```

---

## 📝 Examples

### Basic Test Example
```java
@Test
public void testCheckBox() {
    var formsPage = homePage.gotoForms().clickPracticeForm();
    formsPage.clickSportCheckBox();
    formsPage.clickReadingCheckBox();
    formsPage.clickMusicCheckBox();
    formsPage.uncheckReadingCheckBox();

    assertThat(formsPage.getSportHobbyLocator()).isChecked();
    assertThat(formsPage.getReadingHobbyLocator()).not().isChecked();
    assertThat(formsPage.getMusicHobbyLocator()).isChecked();
}
```

### Page Object Example
```java
public class LinksPage extends BasePage {

    private final Locator badRequestLink;
    private final Locator homePageLink;
    private final Locator linkResponse;

    public LinksPage(Page page) {
        super(page);
        this.badRequestLink = page.locator("#bad-request");
        this.homePageLink = page.locator("#simpleLink");
        this.linkResponse = page.locator("#linkResponse");
    }

    public void clickBadRequestLink() {
        click(badRequestLink);
    }

    public Locator getLinkResponse() {
        return this.linkResponse;
    }

    public Page clickHomePageLink() {
        Page newTab = page.waitForPopup(() -> click(homePageLink));
        newTab.waitForLoadState();
        return newTab;
    }
}
```

---

## 📚 What You'll Learn

### ✅ OOP Principles
- Encapsulation
- Inheritance
- Polymorphism
- Abstraction
- Interfaces

### ✅ Design Patterns
- Page Object Model (POM)
- Singleton Pattern (PlaywrightManager)

### ✅ Playwright Features
- Locators (CSS, XPath, text)
- Auto-waiting mechanism
- Dialog handling (alerts, prompts, confirms)
- Multi-tab/window handling
- Screenshot on failure
- Parallel execution support

### ✅ Best Practices
- Centralized browser management
- Configuration externalization
- Structured logging (SLF4J + Logback)
- ThreadLocal for parallel test execution
- Interface-based design

---

## 🎯 Key Features

### 1. Smart Browser Management
```java
PlaywrightManager.setUpSuite("chromium", true, baseUrl);
Page page = PlaywrightManager.getPage();
```

### 2. Auto Screenshot on Failure
```java
@Override
public void onTestFailure(ITestResult result) {
    failCount++;
    LOGGER.error("❌ FAILED: {}", result.getName());

    String screenshotPath = ScreenshotHelper.captureScreenshot(result.getName());

    if (screenshotPath != null) {
        File screenshot = new File(screenshotPath);
        if (screenshot.exists()) {
            String relativePath = screenshotPath.replace("\\", "/")
                    .replace("target/surefire-reports/", "");

            Reporter.log("<a href='" + relativePath + "' target='_blank'>");
            Reporter.log("<img src='" + relativePath + "' height='400' width='600' style='border:2px solid red;'/>");
            Reporter.log("</a><br>");

            LOGGER.info("\uD83D\uDCF8 Screenshot attached: {}", screenshotPath);
        }
    }

    if (result.getThrowable() != null) {
        Reporter.log("<br><b>Error:</b><br>");
        Reporter.log("<pre>" + result.getThrowable().getMessage() + "</pre>");
    }
}
```

### 3. Dialog Handling (Alerts)
```java
page.onceDialog(dialog -> {
    String message = dialog.message();
    dialog.accept();  // or dialog.dismiss()
});
```

### 4. Multi-Select Support
```java
public void setStandardMultiSelect(String text) {
    List<String> currentSelected = getAllSelectedMultiOptions();

    if (!currentSelected.contains(text)) {
        currentSelected.add(text);
        standardMultiSelect.selectOption(currentSelected.toArray(new String[0]));
    }
}
```

---

## 📊 Test Results

- **Logs:** `target/test-output/logs/test-execution.log`
- **Screenshots:** `target/surefire-reports/screenshots/`

---

## 🐛 Debugging

### Enable DEBUG logging
In `logback.xml` change:
```xml
<root level="DEBUG">
    <appender-ref ref="CONSOLE" />
    <appender-ref ref="FILE" />
</root>
```

### Playwright Inspector
```bash
PWDEBUG=1 mvn test
```

---

## 🤖 CI/CD Pipeline

This project uses a `Jenkinsfile` to define an automated CI/CD pipeline that runs inside a Docker container. The pipeline ensures that the code is always in a testable state.

**Pipeline Stages:**

1.  **Sanity Check:**
    -   Prints the Java and Maven versions to ensure the environment is correct.
2.  **Compile & Download Dependencies:**
    -   Compiles the Java source code.
    -   Downloads all Maven dependencies, preparing for the test execution.
3.  **Run Playwright Tests:**
    -   Executes the entire TestNG test suite using Maven.
4.  **Post-build Actions:**
    -   **Always:** Archives test artifacts, including screenshots and HTML reports, which can be viewed from the Jenkins build page.
    -   **On Success:** Prints a success message.
    -   **On Failure:** Prints a failure message, indicating that something went wrong.

---

## 📖 Useful Resources

- [Playwright Java Docs](https://playwright.dev/java/docs/intro)
- [TestNG Documentation](https://testng.org/)
- [DEMOQA Practice Site](https://demoqa.com/)

---

## 📝 License

MIT License - free for educational purposes

---

## 👨‍💻 Author

Created for educational purposes - QA Automation with Java and Playwright

---