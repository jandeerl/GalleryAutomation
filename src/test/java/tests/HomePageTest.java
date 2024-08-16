package tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import pages.HomePage;
import utils.DriverProvider;

import java.io.IOException;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HomePageTest {
    AndroidDriver driver;

    @BeforeAll
    public void setUp() throws IOException {
        driver = DriverProvider.provideDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void emptyHomepageTest() {
        HomePage homePage = new HomePage(driver);
        homePage.closeUnderstandabilityPopup().checkIfOnHomepage();
    }



}
