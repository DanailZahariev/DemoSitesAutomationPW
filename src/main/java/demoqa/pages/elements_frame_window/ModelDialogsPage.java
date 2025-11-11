package demoqa.pages.elements_frame_window;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ModelDialogsPage extends BasePage {

    private final Locator smallModalButton;
    private final Locator smallModalText;
    private final Locator closeSmallModalButton;

    public ModelDialogsPage(Page page) {
        super(page);
        this.smallModalButton = page.locator("#showSmallModal");
        this.smallModalText = page.locator("//div[contains(text(),'small modal')]");
        this.closeSmallModalButton = page.locator("#closeSmallModal");
    }

    public void clickSmallModalButton() {
        click(smallModalButton);
    }

    public void clickCloseSmallModalButton() {
        click(closeSmallModalButton);
    }

    public String getSmallModalText() {
        return getText(smallModalText);
    }

}
