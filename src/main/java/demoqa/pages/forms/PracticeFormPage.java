package demoqa.pages.forms;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PracticeFormPage extends BasePage {

    private final Locator femaleRadioButton;
    private final Locator maleRadioButton;
    private final Locator otherRadioButton;
    private final Locator sportHobbyCheckbox;
    private final Locator readingHobbyCheckbox;
    private final Locator musicHobbyCheckbox;

    public PracticeFormPage(Page page) {
        super(page);
        this.femaleRadioButton = page.locator("label[for='gender-radio-2']");
        this.maleRadioButton = page.locator("label[for='gender-radio-1']");
        this.otherRadioButton = page.locator("label[for='gender-radio-3']");
        this.sportHobbyCheckbox = page.locator("label[for='hobbies-checkbox-1']");
        this.readingHobbyCheckbox = page.locator("label[for='hobbies-checkbox-2']");
        this.musicHobbyCheckbox = page.locator("label[for='hobbies-checkbox-3']");
    }

    public void clickFemaleRadioButton() {
        femaleRadioButton.check();
    }

    public void clickSportCheckBox() {
        sportHobbyCheckbox.check();
    }

    public void uncheckSportCheckBox() {
        sportHobbyCheckbox.uncheck();
    }


    public void clickReadingCheckBox() {
        readingHobbyCheckbox.check();
    }

    public void uncheckReadingCheckBox() {
        readingHobbyCheckbox.uncheck();
    }

    public void clickMusicCheckBox() {
        musicHobbyCheckbox.check();
    }

    public void uncheckMusicCheckBox() {
        musicHobbyCheckbox.uncheck();
    }

    public Locator getSportHobbyLocator() {
        return this.sportHobbyCheckbox;
    }

    public Locator getReadingHobbyLocator() {
        return this.readingHobbyCheckbox;
    }

    public Locator getMusicHobbyLocator() {
        return this.musicHobbyCheckbox;
    }
}
