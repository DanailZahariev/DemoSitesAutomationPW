package demoqa.pages.forms;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PracticeFormPage extends BasePage {

    private final Locator femaleRadioButton;
    private final Locator sportHobbyCheckbox;
    private final Locator readingHobbyCheckbox;
    private final Locator musicHobbyCheckbox;
    private final Locator submitButton;

    public PracticeFormPage(Page page) {
        super(page);
        this.femaleRadioButton = page.locator("#gender-radio-2");
        this.sportHobbyCheckbox = page.locator("#hobbies-checkbox-1");
        this.readingHobbyCheckbox = page.locator("#hobbies-checkbox-2");
        this.musicHobbyCheckbox = page.locator("#hobbies-checkbox-3");
        this.submitButton = page.locator("#submit");
    }

    public void clickFemaleRadioButton() {
        femaleRadioButton.check(new Locator.CheckOptions().setForce(true));
    }

    public void clickSportCheckBox() {
        sportHobbyCheckbox.check(new Locator.CheckOptions().setForce(true));
    }

    public void uncheckSportCheckBox() {
        sportHobbyCheckbox.uncheck(new Locator.UncheckOptions().setForce(true));
    }

    public boolean isSportCheckBoxSelected() {
        return sportHobbyCheckbox.isChecked();
    }

    public void clickReadingCheckBox() {
        readingHobbyCheckbox.check(new Locator.CheckOptions().setForce(true));
    }

    public void uncheckReadingCheckBox() {
        readingHobbyCheckbox.uncheck(new Locator.UncheckOptions().setForce(true));
    }

    public boolean isReadingCheckBoxSelected() {
        return readingHobbyCheckbox.isChecked();
    }

    public void clickMusicCheckBox() {
        musicHobbyCheckbox.check(new Locator.CheckOptions().setForce(true));
    }

    public void uncheckMusicCheckBox() {
        musicHobbyCheckbox.uncheck(new Locator.UncheckOptions().setForce(true));
    }

    public boolean isMusicCheckBoxSelected() {
        return musicHobbyCheckbox.isChecked();
    }
}
