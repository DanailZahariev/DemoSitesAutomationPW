package demoqa.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import demoqa.base.BaseTest;
import demoqa.pages.home.HomePage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Link Tests")
public class LinkTests extends BaseTest {

    public void testLinks() {
        var linksPage = homePage.goToElements().clickLinks();
        linksPage.clickBadRequestLink();

        String expected = "Link has responded with staus 400 and status text Bad Request";

        assertThat(linksPage.getLinkResponse()).hasText(expected);
    }

    public void testLinkOpenNewTab() {
        var linksPage = homePage.goToElements().clickLinks();
        Page newTab = linksPage.clickHomePageLink();
        HomePage newHomePage = new HomePage(newTab);

        String expectedText = "Forms";
        Locator actualText = newHomePage.getFormsCard();

        assertThat(actualText).hasText(expectedText);
    }
}
