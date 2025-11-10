package demoqa.pages.elements_frame_window;

import base.BasePage;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FramesPage extends BasePage {

    private final String iFrameBigBox = "#frame1";
    private final String textInFrame = "#sampleHeading";
    private final String iFrameSmallBox = "#frame2";
    private final Locator headerFramesText;

    public FramesPage(Page page) {
        super(page);
        this.headerFramesText = page.locator("//div[@id='framesWrapper']//h1[text()='Frames']");
    }

    public String getTextInBigFrame() {
        FrameLocator bigFrame = page.frameLocator(iFrameBigBox);
        return bigFrame.locator(textInFrame).textContent();
    }

    public String getTextInSmallFrame() {
        FrameLocator smallFrame = page.frameLocator(iFrameSmallBox);
        return smallFrame.locator(textInFrame).textContent();
    }

    public String getHeaderFramesText() {
        return getText(headerFramesText);
    }
}
