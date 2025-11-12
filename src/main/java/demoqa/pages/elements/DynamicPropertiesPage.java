package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DynamicPropertiesPage extends BasePage {

    private final Locator visibleAfterButton;

    public DynamicPropertiesPage(Page page) {
        super(page);
        this.visibleAfterButton = page.locator("#visibleAfter");
    }

    public Locator getVisibleAfterText() {
        return this.visibleAfterButton;

    }
}
