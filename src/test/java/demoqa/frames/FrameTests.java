package demoqa.frames;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Frame Tests")
public class FrameTests extends BaseTest {

    public void testFramesBigBox() {
        var framesPage = homePage.goToAlertFrameWindow().clickFrames();
        String actualText = framesPage.getTextInBigFrame();
        String expectedText = "This is a sample page";

        Assert.assertEquals(actualText, expectedText, "\n Actual & expected text are not equal\n");
        String actualHeaderText = framesPage.getHeaderFramesText();
        String expectedHeaderText = "Frames";

        Assert.assertEquals(actualHeaderText, expectedHeaderText, "\n Actual & expected text are not equal\n");
    }

    public void testFramesSmallBox() {
        var framesPage = homePage.goToAlertFrameWindow().clickFrames();
        String actualText = framesPage.getTextInSmallFrame();
        String expectedText = "This is a sample page";

        Assert.assertEquals(actualText, expectedText, "\n Actual & expected text are not equal\n");

        String actualHeaderText = framesPage.getHeaderFramesText();
        String expectedHeaderText = "Frames";

        Assert.assertEquals(actualHeaderText, expectedHeaderText, "\n Actual & expected text are not equal\n");
    }
}
