package tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.BeforeEach;
import utils.DriverProvider;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    AndroidDriver driver;

    @BeforeEach
    public void setUp() throws IOException {
        driver = DriverProvider.provideDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}
