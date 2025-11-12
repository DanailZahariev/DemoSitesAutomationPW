package demoqa.pages.widgets;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.ArrayList;
import java.util.List;

public class SelectMenuPage extends BasePage {

    private final Locator standardMultiSelect;
    private final Locator standardMultiSelectCheckedOptions;

    public SelectMenuPage(Page page) {
        super(page);
        this.standardMultiSelect = page.locator("#cars");
        this.standardMultiSelectCheckedOptions = standardMultiSelect.locator("option:checked");
    }

    public void setStandardMultiSelect(String text) {
        List<String> currentSelected = getAllSelectedMultiOptions();

        if (!currentSelected.contains(text)) {
            currentSelected.add(text);
            standardMultiSelect.selectOption(currentSelected.toArray(new String[0]));
        }
    }

    public void setStandardMultiSelect(int index) {
        List<String> currentSelected = getAllSelectedMultiOptions();
        String optionText = standardMultiSelect.locator("option").nth(index).textContent();

        if (!currentSelected.contains(optionText)) {
            currentSelected.add(optionText);
            standardMultiSelect.selectOption(currentSelected.toArray(new String[0]));
        }
    }

    public void deselectStandardMulti(String text) {
        List<String> allSelected = new ArrayList<>(getAllSelectedMultiOptions());

        if (allSelected.remove(text)) {
            if (allSelected.isEmpty()) {
                page.evaluate("document.querySelector('#cars').selectedIndex = -1");
            } else {
                standardMultiSelect.selectOption(allSelected.toArray(new String[0]));
            }
        }
    }

    public List<String> getAllSelectedMultiOptions() {
        return standardMultiSelectCheckedOptions.allTextContents();
    }

    public Locator getCheckedOptionsLocator() {
        return this.standardMultiSelectCheckedOptions;
    }
}