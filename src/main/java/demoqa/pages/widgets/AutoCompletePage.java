package demoqa.pages.widgets;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class AutoCompletePage extends BasePage {

    private final Locator multiColorInput;
    private final Locator singleColorInput;
    private final Locator singleValueWrapper;
    private final Locator multiValueLabels;
    private final Locator autoCompleteMenu;
    private final Locator multiValueContainers;


    public AutoCompletePage(Page page) {
        super(page);
        this.multiColorInput = page.locator("#autoCompleteMultipleInput");
        this.singleColorInput = page.locator("#autoCompleteSingleInput");
        this.singleValueWrapper = page.locator(".auto-complete__single-value");
        this.multiValueLabels = page.locator(".auto-complete__multi-value");
        this.autoCompleteMenu = page.locator(".auto-complete__menu");
        this.multiValueContainers = page.locator(".auto-complete__multi-value");
    }

    public void selectSingleColor(String partialText, String fullText) {
        fill(singleColorInput, partialText);
        click(getLocatorByExactText(fullText));
    }

    public void selectMultiColor(String partialText, String fullText) {
        fill(multiColorInput, partialText);
        click(getLocatorByExactText(fullText));
    }

    private Locator getLocatorByExactText(String fullText) {
        return autoCompleteMenu.getByText(fullText, new Locator.GetByTextOptions().setExact(true));
    }

    public String getSingleSelectedValue() {
        return getText(singleValueWrapper);
    }

    public List<String> getMultiSelectedValues() {
        return multiValueLabels.allTextContents();
    }

    public void removeMultiColor(String colorName) {
        Locator specificTag = multiValueContainers
                .filter(new Locator.FilterOptions().setHasText(colorName));
        Locator removeButtonInsideTag = specificTag
                .locator(".auto-complete__multi-value__remove");
        click(removeButtonInsideTag);
    }

    public Locator getMultiValueLabelsLocator() {
        return multiValueLabels;
    }

    public Locator getSingleValueWrapperLocator() {
        return singleValueWrapper;
    }
}
