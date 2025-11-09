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

        String actualAge = webTablesPage.getTableAge(email);

        Assert.assertEquals(actualAge, expectedAge, "Age is not correct");

    }

}
