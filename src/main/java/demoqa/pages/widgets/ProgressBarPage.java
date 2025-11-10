package demoqa.pages.widgets;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProgressBarPage extends BasePage {

    private final Locator startButton;
    private final Locator progressValue;

    public ProgressBarPage(Page page) {
        super(page);
        this.startButton = page.locator("#startStopButton");
        this.progressValue = page.locator("//div[@id='progressBar']//div[@aria-valuenow='100']");
    }

    public String getProgressValue() {
        return getByAttributeValue(progressValue, "aria-valuenow");
    }

    public void clickStartButton() {
        click(startButton);
    }

}
