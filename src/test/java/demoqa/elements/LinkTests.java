package demoqa.elements;

import com.microsoft.playwright.Page;
import demoqa.base.BaseTest;
import demoqa.pages.home.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Link Tests")
public class LinkTests extends BaseTest {

    public void testLinks() {
        var linksPage = homePage.goToElements().clickLinks();
        linksPage.clickBadRequestLink();

        var actual = linksPage.getLinkResponse();
        String expected = "Link has responded with staus 400 and status text Bad Request";

        Assert.assertEquals(actual, expected, "Response is not correct");
    }

    public void testLinkOpenNewTab() {
        var linksPage = homePage.goToElements().clickLinks();
        Page newTab = linksPage.clickHomePageLink();
        HomePage newHomePage = new HomePage(newTab);

        String expectedText = "Forms";
        String actualText = newHomePage.getFormsCardText();

        Assert.assertEquals(actualText, expectedText, "Text is not correct");
    }
}
