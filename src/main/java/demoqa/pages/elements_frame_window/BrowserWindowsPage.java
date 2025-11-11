package demoqa.pages.elements_frame_window;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BrowserWindowsPage extends BasePage {

    private final Locator newWindowButton;

    public BrowserWindowsPage(Page page) {
        super(page);
        this.newWindowButton = page.locator("#windowButton");
    }

    public void clickNewWindowButton() {
        click(newWindowButton);
    }
}
