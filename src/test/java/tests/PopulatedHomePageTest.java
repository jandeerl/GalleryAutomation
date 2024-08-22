package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pages.HomePage;
import utils.CameraService;
import utils.DeviceStorageManager;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PopulatedHomePageTest extends BaseTest {

    @BeforeAll
    public void setUpPhotoBacklog() throws IOException, InterruptedException {
        DeviceStorageManager deviceService = new DeviceStorageManager();
        deviceService.cleanPicturesFolder();

        CameraService cameraService = new CameraService();
        cameraService.takePhotos(3);
    }

    @Test
    public void populatedHomepageTest() {
        HomePage homePage = new HomePage(driver);
        homePage.closeUnderstandabilityPopup();
        Assertions.assertFalse(homePage.isEmptyViewContainerDisplayed());
        Assertions.assertEquals(homePage.numberOfPhotos(), 3);
    }
}
