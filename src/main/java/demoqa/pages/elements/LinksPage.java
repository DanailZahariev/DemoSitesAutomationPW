package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LinksPage extends BasePage {

    private final Locator badRequestLink;
    private final Locator homePageLink;
    private final Locator linkResponse;

    public LinksPage(Page page) {
        super(page);
        this.badRequestLink = page.locator("#bad-request");
        this.homePageLink = page.locator("#simpleLink");
        this.linkResponse = page.locator("#linkResponse");
    }

    public void clickBadRequestLink() {
        click(badRequestLink);
    }

    public Locator getLinkResponse() {
        return this.linkResponse;
    }

    public Page clickHomePageLink() {
        Page newTab = page.waitForPopup(() -> click(homePageLink));
        newTab.waitForLoadState();
        return newTab;
    }

}
