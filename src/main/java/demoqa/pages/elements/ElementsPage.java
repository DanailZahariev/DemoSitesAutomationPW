package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ElementsPage extends BasePage {

    private final Locator webTableMenuItem;
    private final Locator linkTableMenuItem;
    private final Locator dynamicPropertiesMenuItem;
    private final Locator textBoxMenuItem;

    public ElementsPage(Page page) {
        super(page);
        this.webTableMenuItem = page.locator("//li[@id='item-3']//span[text()='Web Tables']");
        this.linkTableMenuItem = page.locator("//li[@id='item-5']//span[text()='Links']");
        this.dynamicPropertiesMenuItem = page.locator("//li[@id='item-8']//span[text()='Dynamic Properties']");
        this.textBoxMenuItem = page.locator("//li[@id='item-0']//span[text()='Text Box']");
    }

    public LinksPage clickLinks() {
        click(linkTableMenuItem);
        return new LinksPage(this.page);
    }

    public WebTablesPage clickWebTables() {
        click(webTableMenuItem);
        return new WebTablesPage(this.page);
    }

    public DynamicPropertiesPage clickDynamicProperties() {
        click(dynamicPropertiesMenuItem);
        return new DynamicPropertiesPage(this.page);
    }
}
