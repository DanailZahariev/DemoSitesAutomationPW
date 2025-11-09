package saucedemo.pages;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductPage extends BasePage {

    private final Locator title;

    public ProductPage(Page page) {
        super(page);
        this.title = page.locator(".title");
    }

    public boolean isProductHeaderDisplayed() {
        return isVisible(title);
    }
}
