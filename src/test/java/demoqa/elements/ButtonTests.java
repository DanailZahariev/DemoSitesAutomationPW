package demoqa.elements;

import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Buttons Tests")
public class ButtonTests extends BaseTest {

    @Test
    public void testDoubleClick() {
        var buttonsPage = homePage.goToElements().clickButtons();

        buttonsPage.clickDoubleClickBtn();
        String expectedMessage = "You have done a double click";

        assertThat(buttonsPage.getDoubleClickMessage()).hasText(expectedMessage);
    }

    @Test
    public void testRightClick() {
        var buttonsPage = homePage.goToElements().clickButtons();

        buttonsPage.clickRightClickBtn();
        String expectedMessage = "You have done a right click";

        assertThat(buttonsPage.getRightClickMessage()).hasText(expectedMessage);
    }

    @Test
    public void testClick() {
        var buttonsPage = homePage.goToElements().clickButtons();

        buttonsPage.clickClickBtn();
        String expectedMessage = "You have done a dynamic click";

        assertThat(buttonsPage.getClickMessage()).hasText(expectedMessage);
    }

}
