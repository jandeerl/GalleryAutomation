package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pages.HomePage;
import utils.DeviceStorageManager;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmptyHomePageTest extends BaseTest {
    @BeforeAll
    public void cleanUpStorage() throws IOException {
        DeviceStorageManager service = new DeviceStorageManager();
        service.cleanPicturesFolder();
    }

    @Test
    public void emptyHomepageTest() {
        HomePage homePage = new HomePage(driver);
        homePage.closeUnderstandabilityPopup();
        Assertions.assertTrue(homePage.isEmptyViewContainerDisplayed());
    }

}
