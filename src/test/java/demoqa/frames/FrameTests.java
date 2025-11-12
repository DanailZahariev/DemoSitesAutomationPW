package demoqa.frames;

import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Frame Tests")
public class FrameTests extends BaseTest {

    public void testFramesBigBox() {
        var framesPage = homePage.goToAlertFrameWindow().clickFrames();
        String expectedText = "This is a sample page";

        assertThat(framesPage.getTextInBigFrame()).hasText(expectedText);

        String expectedHeaderText = "Frames";

        assertThat(framesPage.getHeaderFramesText()).hasText(expectedHeaderText);
    }

    public void testFramesSmallBox() {
        var framesPage = homePage.goToAlertFrameWindow().clickFrames();
        String expectedText = "This is a sample page";

        assertThat(framesPage.getTextInSmallFrame()).hasText(expectedText);

        String expectedHeaderText = "Frames";

        assertThat(framesPage.getHeaderFramesText()).hasText(expectedHeaderText);
    }
}
