package demoqa.interfaces;

import com.microsoft.playwright.Page;

public interface IPage {

    Page getPage();

    void navigateTo(String url);

    String getCurrentUrl();

    String getTitle();

    void waitForPageLoad();

    boolean isPageLoaded();
}
