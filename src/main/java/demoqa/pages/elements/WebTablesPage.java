package demoqa.pages.elements;

import base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WebTablesPage extends BasePage {

    private final Locator ageField;
    private final Locator submitButton;
    private final Locator dataRows;
    private final Locator addRow;
    private final Locator firstNameField;
    private final Locator lastNameField;
    private final Locator emailField;
    private final Locator salaryField;
    private final Locator departmentField;


    public WebTablesPage(Page page) {
        super(page);
        this.ageField = page.locator("#age");
        this.submitButton = page.locator("#submit");
        this.dataRows = page.locator(".rt-tbody .rt-tr-group .rt-tr:not(.-padRow)");
        this.addRow = page.locator("#addNewRecordButton");
        this.firstNameField = page.locator("#firstName");
        this.lastNameField = page.locator("#lastName");
        this.emailField = page.locator("#userEmail");
        this.salaryField = page.locator("#salary");
        this.departmentField = page.locator("#department");
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

    public void clickEdit(String email) {
        Locator row = getRowByUniqueText(email);
        Locator editBtn = row.locator("//span[@title='Edit']");
        click(editBtn);
    }

    public void deleteRow(String text) {
        Locator row = getRowByUniqueText(text);
        Locator deleteBtn = row.locator("//span[@title='Delete']");
        click(deleteBtn);
    }

    public void setAge(String age) {
        fill(ageField, age);
    }

    public String getRowFirstName(String text) {
        Locator row = getRowByUniqueText(text);
        Locator firstNameCell = row.locator(" .rt-td").nth(0);
        return getText(firstNameCell);
    }

    public String getRowLastName(String text) {
        Locator row = getRowByUniqueText(text);
        Locator lastNameCell = row.locator(" .rt-td").nth(1);
        return getText(lastNameCell);
    }

    public String getRowAge(String text) {
        Locator row = getRowByUniqueText(text);
        Locator ageCell = row.locator(" .rt-td").nth(2);
        return getText(ageCell);
    }

    public String getRowEmail(String text) {
        Locator row = getRowByUniqueText(text);
        Locator emailCell = row.locator(" .rt-td").nth(3);
        return getText(emailCell);
    }

    public String getRowSalary(String text) {
        Locator row = getRowByUniqueText(text);
        Locator salaryCell = row.locator(" .rt-td").nth(4);
        return getText(salaryCell);
    }

    public String getRowDepartment(String text) {
        Locator row = getRowByUniqueText(text);
        Locator departmentCell = row.locator(" .rt-td").nth(5);
        return getText(departmentCell);
    }

    public int getDataRows() {
        return dataRows.count();
    }

    private Locator getRowByUniqueText(String uniqueText) {
        return this.page.locator(".rt-tr-group")
                .filter(new Locator.FilterOptions().setHasText(uniqueText));
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
        click(addRow);
    }

    public void clickSubmit() {
        click(submitButton);
    }
}
