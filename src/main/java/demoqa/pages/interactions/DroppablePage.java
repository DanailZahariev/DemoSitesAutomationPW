package demoqa.pages.interactions;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DroppablePage extends BasePage {

    private final Locator draggableElement;
    private final Locator droppableBox;
    private final Locator acceptTab;
    private final Locator acceptableBox;
    private final Locator notAcceptableBox;
    private final Locator acceptableDroppableBox;

    public DroppablePage(Page page) {
        super(page);
        this.draggableElement = page.locator("#draggable");
        this.droppableBox = page.locator("div[id='simpleDropContainer'] p");
        this.acceptTab = page.locator("#droppableExample-tab-accept");
        this.acceptableBox = page.locator("#acceptable");
        this.acceptableDroppableBox = page.locator("//div[@id='acceptDropContainer']//div[@id='droppable']//p");
        this.notAcceptableBox = page.locator("#notAcceptable");
    }

    public void dragToNotAcceptable() {
        dragAndDrop(notAcceptableBox, acceptableDroppableBox);
    }


    public void dragToAcceptable() {
        dragAndDrop(acceptableBox, acceptableDroppableBox);
    }

    public void dragToDestination() {
        dragAndDrop(draggableElement, droppableBox);
    }

    public Locator getDroppableBox() {
        return this.droppableBox;
    }

    public Locator getAcceptableBox() {
        return this.acceptableDroppableBox;
    }

    public void clickAcceptTab() {
        click(acceptTab);
    }
}
