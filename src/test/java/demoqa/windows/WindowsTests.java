package demoqa.windows;

import com.microsoft.playwright.Page;
import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Windows Tests")
public class WindowsTests extends BaseTest {

    public void testNewWindowUrl() {
        var windowsPage = homePage.goToAlertFrameWindow().clickBrowserWindows();
        Page mainPage = windowsPage.getPage();
        Page newWinwowPage = mainPage.waitForPopup(windowsPage::clickNewWindowButton);
        newWinwowPage.waitForLoadState();
        String actualUrl = newWinwowPage.url();

        String expectedUrl = "https://demoqa.com/sample";

        Assert.assertEquals(actualUrl, expectedUrl, "Url is not correct");
    }
}
