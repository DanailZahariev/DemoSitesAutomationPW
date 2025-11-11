package demoqa.elements;

import demoqa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test(suiteName = "Web Table Tests")
public class WebTableTests extends BaseTest {

    public void testWebTable() {
        String email = "cierra@example.com";
        String age = "25";
        String expectedAge = "25";

        var webTablesPage = homePage.goToElements().clickWebTables();
        webTablesPage.clickEdit(email);
        webTablesPage.setAge(age);
        webTablesPage.clickSubmit();

        String actualAge = webTablesPage.getRowAge(email);

        Assert.assertEquals(actualAge, expectedAge, "Age is not correct");

    }

    public void testWebTableDelete() {
        String email = "cierra@example.com";
        int expectedRows = 3;
        var webTablePage = homePage.goToElements().clickWebTables();
        int dataRowCount = webTablePage.getDataRows();

        Assert.assertEquals(dataRowCount, expectedRows, "Table has incorrect number of rows");

        webTablePage.deleteRow(email);
        dataRowCount = webTablePage.getDataRows();
        expectedRows = 2;

        Assert.assertEquals(dataRowCount, expectedRows, "Row wasn't deleted" + "Expected rows: 2" + "Actual rows: " + dataRowCount);
    }

    public void testAddNewRow() {
        var firstName = "Ivan";
        var lastName = "Ivanov";
        var email = "ivan@example.com";
        var age = 20;
        var salary = 5000;
        var department = "QA";
        int expectedRows = 4;

        var webTablePage = homePage.goToElements().clickWebTables();
        webTablePage.addNewUser(firstName, lastName, email, String.valueOf(age), String.valueOf(salary), department);

        int dataRowCount = webTablePage.getDataRows();

        Assert.assertEquals(dataRowCount, expectedRows, "Row wasn't added");

        String actualFirstName = webTablePage.getRowFirstName(email);
        String actualLastName = webTablePage.getRowLastName(email);
        String actualAge = webTablePage.getRowAge(email);
        String actualEmail = webTablePage.getRowEmail(email);
        String actualSalary = webTablePage.getRowSalary(email);
        String actualDepartment = webTablePage.getRowDepartment(email);

        Assert.assertEquals(String.valueOf(age), actualAge);
        Assert.assertEquals(String.valueOf(salary), actualSalary);
        Assert.assertEquals(actualFirstName, firstName);
        Assert.assertEquals(actualLastName, lastName);
        Assert.assertEquals(actualEmail, email);
        Assert.assertEquals(actualDepartment, department);
    }
}
