package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WebTablesPage extends BasePage {

    private final Locator registrationAgeField;
    private final Locator submitButton;

    public WebTablesPage(Page page) {
        super(page);
        this.registrationAgeField = page.locator("#age");
        this.submitButton = page.locator("#submit");
    }

    public void clickEdit(String email) {
        String editLocator = "//div[text()='" + email + "']//following::span[@title='Edit'][1]";
        this.page.locator(editLocator).click();
    }

    public void setAge(String age) {
        fill(registrationAgeField, age);
    }

    public void clickSubmit() {
        click(submitButton);
    }

    public String getTableAge(String email) {
        String tableAgeLocator = "//div[normalize-space()='" + email + "']/preceding::div[1]";
        return this.page.locator(tableAgeLocator).textContent();
    }
}
