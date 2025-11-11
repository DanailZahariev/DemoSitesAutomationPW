package saucedemo.tests.products;

import org.testng.annotations.Test;
import saucedemo.base.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Product Tests")
public class ProductTests extends BaseTest {

    public void testProductsHeaderIsDisplayed() {
        var productPage = loginPage.loginIntoApplication("standard_user", "secret_sauce");

        assertThat(productPage.getHeaderText()).isVisible();
    }
}
