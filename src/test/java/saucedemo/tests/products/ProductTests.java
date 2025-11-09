package saucedemo.tests.products;

import org.testng.Assert;
import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

@Test(suiteName = "Product Tests")
public class ProductTests extends BaseTest {

    public void testProductsHeaderIsDisplayed() {
        var productPage = loginPage.loginIntoApplication("standard_user", "secret_sauce");

        Assert.assertTrue(productPage.isProductHeaderDisplayed(), "Product header is not displayed");
    }
}
