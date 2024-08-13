import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class FirstTest {

    @Test
    public void firstTest() throws IOException {
        AndroidDriver driver = DriverProvider.provideDriver();
        try {
            WebElement el = driver.findElement(AppiumBy.id(
                    "com.google.android.apps.photos:id/recycler_view"));
            Assertions.assertTrue(el.isDisplayed());
        } finally {
            driver.quit();
        }
    }
}
