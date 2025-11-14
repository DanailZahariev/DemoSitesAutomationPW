package demoqa.interactions;

import demoqa.base.BaseTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Test(suiteName = "Droppable Tests")
public class DroppableTests extends BaseTest {

    public void testDragAndDrop() {
        var droppablePage = homePage.goToInteractions().clickDroppable();
        String initialText = "Drop here";

        assertThat(droppablePage.getDroppableBox()).hasText(initialText);

        droppablePage.dragToDestination();
        String expectedText = "Dropped!";

        assertThat(droppablePage.getDroppableBox()).hasText(expectedText);
    }

    public void testDragAndDropToAcceptable() {
        var droppablePage = homePage.goToInteractions().clickDroppable();
        String initialText = "Drop here";

        droppablePage.clickAcceptTab();

        assertThat(droppablePage.getDroppableBox()).hasText(initialText);

        droppablePage.dragToAcceptable();

        String expectedText = "Dropped!";

        assertThat(droppablePage.getAcceptableBox()).hasText(expectedText);
    }

    public void testDragAndDropNotAcceptable() {
        var droppablePage = homePage.goToInteractions().clickDroppable();
        String initialText = "Drop here";

        droppablePage.clickAcceptTab();

        assertThat(droppablePage.getDroppableBox()).hasText(initialText);

        droppablePage.dragToNotAcceptable();

        String expectedText = "Drop here";

        assertThat(droppablePage.getDroppableBox()).hasText(expectedText);
    }
}
