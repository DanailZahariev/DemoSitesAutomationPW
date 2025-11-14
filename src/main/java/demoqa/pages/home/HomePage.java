package demoqa.pages.home;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import demoqa.pages.elements.ElementsPage;
import demoqa.pages.elements_frame_window.AlertFrameWindowPage;
import demoqa.pages.elements_frame_window.FormsPage;
import demoqa.pages.interactions.InteractionsPage;
import demoqa.pages.widgets.WidgetsPage;

public class HomePage extends BasePage {

    private final Locator formsCard;
    private final Locator elementsCard;
    private final Locator widgetMenuCard;
    private final Locator alertFrameWindowMenuCard;
    private final Locator interactionsMenuCard;

    public HomePage(Page page) {
        super(page);
        this.formsCard = page.locator("//div[@id='app']//h5[text()='Forms']");
        this.elementsCard = page.locator("//div[@id='app']//h5[text()='Elements']");
        this.widgetMenuCard = page.locator("//div[@id='app']//h5[text()='Widgets']");
        this.alertFrameWindowMenuCard = page.locator("//div[@id='app']//h5[contains(text(),'Alerts')]");
        this.interactionsMenuCard = page.locator("//div[@id='app']//h5[text()='Interactions']");
    }

    public InteractionsPage goToInteractions() {
        click(interactionsMenuCard);
        return new InteractionsPage(this.page);
    }

    public AlertFrameWindowPage goToAlertFrameWindow() {
        click(alertFrameWindowMenuCard);
        return new AlertFrameWindowPage(this.page);
    }

    public FormsPage gotoForms() {
        click(formsCard);
        return new FormsPage(this.page);
    }

    public ElementsPage goToElements() {
        click(elementsCard);
        return new ElementsPage(this.page);
    }

    public WidgetsPage goToWidgets() {
        click(widgetMenuCard);
        return new WidgetsPage(this.page);
    }

    public Locator getFormsCard() {
        return this.formsCard;
    }

}
