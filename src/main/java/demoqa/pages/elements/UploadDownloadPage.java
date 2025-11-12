package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Path;

public class UploadDownloadPage extends BasePage {

    private final Locator downloadButton;
    private final Locator uploadButton;
    private final Locator uploadResultPath;


    public UploadDownloadPage(Page page) {
        super(page);
        this.downloadButton = page.locator("#downloadButton");
        this.uploadResultPath = page.locator("#uploadedFilePath");
        this.uploadButton = page.locator("#uploadFile");
    }

    public void uploadFile(Path filePath) {
        uploadButton.setInputFiles(filePath);
    }

    public Locator getUploadResultPath() {
        return this.uploadResultPath;
    }

    public void clickDownloadButton() {
        click(downloadButton);
    }
}
