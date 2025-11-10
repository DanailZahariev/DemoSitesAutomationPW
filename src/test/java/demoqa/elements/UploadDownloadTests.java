package demoqa.elements;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Test(suiteName = "Upload Download Tests")
public class UploadDownloadTests extends BaseTest {

    public void testUploadDownload() {

        var uploadDownloadPage = homePage.goToElements().clickUploadDownload();

        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("upload-test-", ".txt");
            Files.writeString(tempFile, "Hello World");
            uploadDownloadPage.uploadFile(tempFile);

            String actualResult = uploadDownloadPage.getUploadResultPath();
            String expectedResult = String.valueOf(tempFile.getFileName());

            Assert.assertTrue(actualResult.contains(expectedResult), "File is not uploaded");
        } catch (IOException e) {
           LOGGER.error("Failed to create temp file: {}", e.getMessage());
        } finally {
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException e) {
                    LOGGER.error("Failed to delete temp file: {}", e.getMessage());
                }
            }
        }
    }

    public void testFilesToDownload() {
        var uploadDownloadPage = homePage.goToElements().clickUploadDownload();

        Page page = uploadDownloadPage.getPage();
        Download downloadedFile = page.waitForDownload(uploadDownloadPage::clickDownloadButton);

        String expectedFileName = "sampleFile.jpeg";
        String actualDownloadFile = downloadedFile.suggestedFilename();

        Assert.assertEquals(actualDownloadFile, expectedFileName, "File is not downloaded");

        try {
            downloadedFile.delete();
        } catch (Exception e) {
            LOGGER.error("Failed to delete downloaded file: {}", e.getMessage());
        }
    }
}
