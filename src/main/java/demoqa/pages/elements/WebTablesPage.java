package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class WebTablesPage extends BasePage {

    private final Locator submitButton;
    private final Locator addRowButton;
    private final Locator firstNameField;
    private final Locator lastNameField;
    private final Locator emailField;
    private final Locator ageField;
    private final Locator salaryField;
    private final Locator departmentField;
    private final Locator allFieldRows;

    public WebTablesPage(Page page) {
        super(page);
        this.submitButton = page.locator("#submit");
        this.addRowButton = page.locator("#addNewRecordButton");
        this.firstNameField = page.getByPlaceholder("First Name");
        this.lastNameField = page.getByPlaceholder("Last Name");
        this.emailField = page.getByPlaceholder("name@example.com");
        this.ageField = page.getByPlaceholder("Age");
        this.salaryField = page.getByPlaceholder("Salary");
        this.departmentField = page.getByPlaceholder("Department");
        this.allFieldRows = page.locator("tbody tr");
    }

    public void setFirstNameField(String firstName) {
        fill(firstNameField, firstName);
    }

    public void setLastNameField(String lastName) {
        fill(lastNameField, lastName);
    }

    public void setEmailField(String email) {
        fill(emailField, email);
    }

    public void setSalaryField(String salary) {
        fill(salaryField, salary);
    }

    public void setDepartmentField(String department) {
        fill(departmentField, department);
    }

    public void setAge(String age) {
        fill(ageField, age);
    }

    public void clickEdit(String email) {
        Locator row = getRowByUniqueText(email);
        Locator editBtn = row.getByTitle("Edit");
        click(editBtn);
    }

    public void deleteRow(String text) {
        Locator row = getRowByUniqueText(text);
        Locator deleteBtn = row.getByTitle("Delete");
        click(deleteBtn);
    }

    public String getRowFirstName(String text) {
        return getCellText(text, 0);
    }

    public String getRowLastName(String text) {
        return getCellText(text, 1);
    }

    public String getRowAge(String text) {
        return getCellText(text, 2);
    }

    public String getRowEmail(String text) {
        return getCellText(text, 3);
    }

    public String getRowSalary(String text) {
        return getCellText(text, 4);
    }

    public String getRowDepartment(String text) {
        return getCellText(text, 5);
    }

    public int getDataRows() {
        waitForVisible(allFieldRows.first());
        return allFieldRows.count();
    }

    private Locator getRowByUniqueText(String uniqueText) {
        return page.getByRole(AriaRole.ROW)
                .filter(new Locator.FilterOptions().setHasText(uniqueText));
    }

    private String getCellText(String uniqueText, int columnIndex) {
        Locator cell = getRowByUniqueText(uniqueText)
                .getByRole(AriaRole.CELL)
                .nth(columnIndex);
        return getText(cell);
    }

    public void addNewUser(String firstName, String lastName, String email, String age, String salary, String department) {
        clickAddNewRow();
        setFirstNameField(firstName);
        setLastNameField(lastName);
        setEmailField(email);
        setAge(age);
        setSalaryField(salary);
        setDepartmentField(department);
        clickSubmit();
    }

    public void clickAddNewRow() {
        click(addRowButton);
    }

    public void clickSubmit() {
        click(submitButton);
    }
}