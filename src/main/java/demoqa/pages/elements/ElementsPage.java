package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ElementsPage extends BasePage {

    private final Locator webTableMenuItem;
    private final Locator linkTableMenuItem;
    private final Locator dynamicPropertiesMenuItem;
    private final Locator textBoxMenuItem;
    private final Locator uploadDownloadMenuItem;
    private final Locator buttonsMenuItem;

    public ElementsPage(Page page) {
        super(page);
        this.webTableMenuItem = page.locator("//span[text()='Web Tables']");
        this.linkTableMenuItem = page.locator("//span[text()='Links']");
        this.dynamicPropertiesMenuItem = page.locator("//span[text()='Dynamic Properties']");
        this.textBoxMenuItem = page.locator("//span[text()='Text Box']");
        this.uploadDownloadMenuItem = page.locator("//span[text()='Upload and Download']");
        this.buttonsMenuItem = page.locator("//span[text()='Buttons']");
    }

    public ButtonsPage clickButtons() {
        click(buttonsMenuItem);
        return new ButtonsPage(this.page);
    }

    public UploadDownloadPage clickUploadDownload() {
        click(uploadDownloadMenuItem);
        return new UploadDownloadPage(this.page);
    }

    public TextBoxPage clickTextBox() {
        click(textBoxMenuItem);
        return new TextBoxPage(this.page);
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
