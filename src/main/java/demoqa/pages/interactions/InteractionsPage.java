package demoqa.pages.interactions;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class InteractionsPage extends BasePage {

    private final Locator droppableMenuItem;

    public InteractionsPage(Page page) {
        super(page);
        this.droppableMenuItem = page.locator("//li[@id='item-3']//span[text()='Droppable']");
    }

    public DroppablePage clickDroppable() {
        click(droppableMenuItem);
        return new DroppablePage(this.page);
    }
}
