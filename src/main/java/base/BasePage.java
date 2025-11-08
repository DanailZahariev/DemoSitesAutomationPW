package base;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import demoqa.interfaces.IPage;

public abstract class BasePage implements IPage {

    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    @Override
    public Page getPage() {
        return page;
    }

    @Override
    public void navigateTo(String url) {
        page.navigate(url);
    }

    @Override
    public String getCurrentUrl() {
        return page.url();
    }

    @Override
    public String getTitle() {
        return page.title();
    }

    @Override
    public void waitForPageLoad() {
        page.waitForLoadState();
    }

    @Override
    public boolean isPageLoaded() {
        return page.url().startsWith("https://demoqa.com");
    }

    protected void fill(Locator locator, String value) {
        locator.fill(value);
    }

    protected void click(Locator locator) {
        locator.click();
    }

    protected String getByAttributeValue(Locator locator, String attribute) {
        return locator.getAttribute(attribute);
    }

    protected String getText(Locator locator) {
        return locator.textContent();
    }

    protected String getValue(Locator locator) {
        return locator.inputValue();
    }

    protected void waitForVisible(Locator locator) {
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
    }

    protected boolean isVisible(Locator locator) {
        return locator.isVisible();
    }

    protected boolean isEnabled(Locator locator) {
        return locator.isEnabled();
    }

    protected String handleDialogAndGetMessage(Locator trigger, boolean accept) {
        String[] message = new String[1];

        page.onceDialog(dialog -> {
            message[0] = dialog.message();
            if (accept) {
                dialog.accept();
            } else {
                dialog.dismiss();
            }
        });

        trigger.click();
        return message[0];
    }

    protected void handleDialogWithText(Locator trigger, String text) {
        page.onceDialog(dialog -> {
            dialog.accept(text);
        });

        trigger.click();
    }

    protected void acceptDialog(Locator trigger) {
        page.onceDialog(Dialog::accept);
        trigger.click();
    }

    protected void dismissDialog(Locator trigger) {
        page.onceDialog(Dialog::dismiss);
        trigger.click();
    }

}