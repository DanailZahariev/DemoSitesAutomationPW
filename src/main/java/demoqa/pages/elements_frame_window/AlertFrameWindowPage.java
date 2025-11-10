package demoqa.pages.elements_frame_window;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AlertFrameWindowPage extends BasePage {

    private final Locator modalMenuItem;
    private final Locator alertMenuItem;
    private final Locator framesMenuItem;
    private final Locator browserWindowsMenuItem;


    public AlertFrameWindowPage(Page page) {
        super(page);
        this.modalMenuItem = page.locator("//li[@id='item-4']//span[contains(text(),'Modal')]");
        this.alertMenuItem = page.locator("//li[@id='item-1']//span[text()='Alerts']");
        this.framesMenuItem = page.locator("//li[@id='item-2']/span[text()='Frames']");
        this.browserWindowsMenuItem = page.locator("//li[@id='item-0']//span[text()='Browser Windows']");
    }

    public AlertsPage clickAlerts() {
        click(alertMenuItem);
        return new AlertsPage(this.page);
    }

    public FramesPage clickFrames() {
        click(framesMenuItem);
        return new FramesPage(this.page);
    }
}