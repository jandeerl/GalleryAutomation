package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.logging.Logger;

public class CameraService {
    private static final Logger logger = Logger.getLogger(
            DeviceStorageManager.class.getName());
    String cameraBundleId = "com.android.camera2";
    String confirmButtonId = "com.android.camera2:id/confirm_button";
    String shutterButtonId = "com.android.camera2:id/shutter_button";
    AndroidDriver driver = DriverProvider.provideDriver();

    public CameraService() throws IOException {
    }

    private void setupApp() {
        driver.executeScript("mobile: changePermissions",
                Map.ofEntries(Map.entry("permissions", "all"),
                        Map.entry("appPackage", cameraBundleId)));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private void openPhotosApp() {
        driver.activateApp(cameraBundleId);
    }

    private void confirmRememberPhotoLocations() {
        try {
            WebElement confirmButton = driver.findElement(
                    By.id(confirmButtonId));
            confirmButton.click();
        } catch (NoSuchElementException e) {
            logger.warning(e.getMessage());
            logger.info("Remember locations popup not found. Continuing.");
        }
    }

    private void takePhoto() throws InterruptedException {
        WebElement shutterButton = driver.findElement(By.id(shutterButtonId));
        shutterButton.click();
        //Wait one second for the button to reset/photo to get saved
        Thread.sleep(1000);
    }

    public void takePhotos(Integer numberOfPhotos) throws InterruptedException {
        setupApp();
        openPhotosApp();
        confirmRememberPhotoLocations();
        for (int i = 0; i < numberOfPhotos; i++) {
            takePhoto();
        }
    }


}
