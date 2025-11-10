package demoqa.pages.elements_frame_window;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import demoqa.pages.forms.PracticeFormPage;

public class FormsPage extends BasePage {

    private final Locator practiceFormMenuItem;

    public FormsPage(Page page) {
        super(page);
        this.practiceFormMenuItem = page.locator("//li[@id='item-0']/span[text()='Practice Form']");;
    }

    public PracticeFormPage clickPracticeForm() {
        click(practiceFormMenuItem);
        return new PracticeFormPage(this.page);
    }
}
