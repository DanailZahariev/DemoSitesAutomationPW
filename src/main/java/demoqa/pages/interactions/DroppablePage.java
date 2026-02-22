package demoqa.pages.interactions;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DroppablePage extends BasePage {

    private final Locator droppableBox;
    private final Locator acceptTab;
    private final Locator acceptableBox;
    private final Locator notAcceptableBox;

    public DroppablePage(Page page) {
        super(page);
        this.acceptTab = page.locator("#droppableExample-tab-accept");
        this.droppableBox = page.locator("#droppableExample-tabpane-accept .drop-box");
        this.acceptableBox = page.locator("#acceptable");
        this.notAcceptableBox = page.getByText("Not Acceptable", new Page.GetByTextOptions().setExact(true));
    }

    public void dragToNotAcceptable() {
        dragAndDrop(notAcceptableBox, droppableBox);
    }

    public void dragToAcceptable() {
        dragAndDrop(acceptableBox, droppableBox);
    }

    public Locator getDroppableBox() {
        return this.droppableBox;
    }

    public void clickAcceptTab() {
        click(acceptTab);
        waitForVisible(page.locator("#acceptable.ui-draggable-handle"));
    }
}
