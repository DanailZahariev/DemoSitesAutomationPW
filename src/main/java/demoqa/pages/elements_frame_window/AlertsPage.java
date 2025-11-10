package demoqa.pages.elements_frame_window;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AlertsPage extends BasePage {

    private final Locator informationAlertButton;
    private final Locator confirmationAlertButton;
    private final Locator confirmationResult;
    private final Locator promptAlertButton;
    private final Locator promptResult;

    public AlertsPage(Page page) {
        super(page);
        this.informationAlertButton = page.locator("#alertButton");
        this.confirmationAlertButton = page.locator("#confirmButton");
        this.confirmationResult = page.locator("#confirmResult");
        this.promptAlertButton = page.locator("#promtButton");
        this.promptResult = page.locator("#promptResult");
    }

    public void clickInformationAlertButton() {
        click(informationAlertButton);
    }

    public void clickConfirmationAlertButton() {
        click(confirmationAlertButton);
    }

    public String getConfirmationResult() {
        return getText(confirmationResult);
    }

    public void clickPromptAlertButton() {
        click(promptAlertButton);
    }

    public String getPromptAlertText() {
        return getText(promptResult);
    }

    public String clickInformationAlertAndGetText() {
        return handleDialogAndGetMessage(informationAlertButton, true);
    }

    public void clickConfirmationAlertAndAccept() {
        acceptDialog(confirmationAlertButton);
    }

    public void clickConfirmationAlertAndDismiss() {
        dismissDialog(confirmationAlertButton);
    }

    public void clickPromptAlertAndEnterText(String text) {
        handleDialogWithText(promptAlertButton, text);
    }
}
