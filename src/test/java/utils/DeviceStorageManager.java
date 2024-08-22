package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class DeviceStorageManager {
    private static final Logger logger = Logger.getLogger(
            DeviceStorageManager.class.getName());
    private final AndroidDriver driver = DriverProvider.provideDriver();

    public DeviceStorageManager() throws IOException {
    }

    private void refreshMediaStore() {
        driver.executeScript("mobile: shell",
                Map.ofEntries(Map.entry("command", "content call"),
                        Map.entry("args",
                                new String[]{"--method scan_volume", "--uri content://media", "--arg external_primary"}
                        )));
    }


    public void cleanPicturesFolder() {
        try {
            driver.executeScript("mobile: shell",
                    Map.ofEntries(Map.entry("command", "rm"),
                            Map.entry("args", "storage/emulated/0/Pictures/*")
                    ));
            logger.info("Successfully deleted pictures from folder.");
        } catch (WebDriverException e) {
            logger.warning(e.getMessage());
            logger.info("Failed to delete pictures from folder. Continuing.");
        }
        refreshMediaStore();
    }

}
