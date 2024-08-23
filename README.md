# Photos app test automation in Appium

This is an example project demonstrating test automation on Photos app on Pixel devices.

The entire project is written in Java 21, based on Appium 2 & UIAutomator2.

### Shell commands usage

Due to the app relying on files being present on device, adb commands need to be enabled
to allow refreshing the MediaStore, i.e.:
```java
//DeviceStorageManager.java

    private void refreshMediaStore() {
        driver.executeScript("mobile: shell",
                Map.ofEntries(Map.entry("command", "content call"),
                        Map.entry("args",
                                new String[]{"--method scan_volume", "--uri content://media", "--arg external_primary"}
                        )));
    }
```
Similarly, files are deleted in the @BeforeEach method, to ensure any existing photos (from previous tests etc.) do not interfere with what images are 
displayed in the gallery.
```java
//DeviceStorageManager.java

public void cleanPicturesFolder(){
        try {
            driver.executeScript("mobile: shell",
                Map.ofEntries(Map.entry("command","rm"),
                        Map.entry("args","storage/emulated/0/Pictures/*")
                ));
            logger.info("Successfully deleted pictures from folder.");
        } catch (WebDriverException e) {
            logger.warning(e.getMessage());
            logger.info("Failed to delete pictures from folder. Continuing.");
        }
        refreshMediaStore();
    }
```

### Sample photos

To populate the Photos app, sample photos need to be available on device. As part of the setup for PopulatedHomePageTest,
photos are taken from the device's camera.

<img src="populatedHomepageTest.gif" alt ="PopulatedHomePageTest being run">

