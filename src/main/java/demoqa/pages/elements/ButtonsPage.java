package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ButtonsPage extends BasePage {

    private final Locator doubleClickBtn;
    private final Locator rightClickBtn;
    private final Locator clickBtn;
    private final Locator doubleClickMessage;
    private final Locator rightClickMessage;
    private final Locator clickMessage;

    public ButtonsPage(Page page) {
        super(page);
        this.doubleClickBtn = page.locator("#doubleClickBtn22");
        this.rightClickBtn = page.locator("#rightClickBtn");
        this.clickBtn = page.locator("//div//button[text()='Click Me']");
        this.doubleClickMessage = page.locator("#doubleClickMessage");
        this.rightClickMessage = page.locator("#rightClickMessage");
        this.clickMessage = page.locator("#dynamicClickMessage");
    }

    public void clickDoubleClickBtn() {
        doubleClick(doubleClickBtn);
    }

    public void clickRightClickBtn() {
        rightClick(rightClickBtn);
    }

    public void clickClickBtn() {
        click(clickBtn);
    }

    public Locator getDoubleClickMessage() {
        return this.doubleClickMessage;
    }

    public Locator getRightClickMessage() {
        return this.rightClickMessage;
    }

    public Locator getClickMessage() {
        return this.clickMessage;
    }
}
