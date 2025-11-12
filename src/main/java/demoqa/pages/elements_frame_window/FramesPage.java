package demoqa.pages.elements_frame_window;

import base.BasePage;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FramesPage extends BasePage {

    private final String iFrameBigBox = "#frame1";
    private final String iFrameSmallBox = "#frame2";
    private final String textInFrame = "#sampleHeading";
    private final Locator headerFramesText;

    public FramesPage(Page page) {
        super(page);
        this.headerFramesText = page.locator("//div[@id='framesWrapper']//h1[text()='Frames']");
    }

    public Locator getTextInBigFrame() {
       return getFrameLocator(iFrameBigBox).locator(textInFrame);
    }

    public Locator getTextInSmallFrame() {
       return getFrameLocator(iFrameSmallBox).locator(textInFrame);
    }

    private FrameLocator getFrameLocator(String iframe) {
        return page.frameLocator(iframe);
    }

    public Locator getHeaderFramesText() {
        return this.headerFramesText;
    }
}
