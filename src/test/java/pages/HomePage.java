package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class HomePage extends BasePage {

    private static final Logger logger = Logger.getLogger(
            HomePage.class.getName());
    @AndroidFindBy(id = "com.google.android.apps.photos:id/empty_view_container")
    private WebElement emptyViewContainer;
    @AndroidFindBy(id = "com.google.android.apps.photos:id/recycler_view")
    private WebElement recyclerView;
    @AndroidFindBy(id = "com.google.android.apps.photos:id/backup_understandability_image")
    private WebElement understandabilityImage;

    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    public HomePage closeUnderstandabilityPopup() {
        try {
            understandabilityImage.isDisplayed();
            driver.navigate().back();
        } catch (NotFoundException e) {
            logger.warning(e.getMessage());
            logger.info("Understandability popup not found. Continuing.");
        }
        return this;
    }

    public boolean isEmptyViewContainerDisplayed() {
        boolean isContainerPresent;
        try {
            isContainerPresent = emptyViewContainer.isDisplayed();
        } catch (NotFoundException e) {
            logger.warning(e.getMessage());
            logger.info("Container not present. Continuing.");
            isContainerPresent = false;
        }
        return isContainerPresent;
    }

    public int numberOfPhotos() {
        return recyclerView.findElements(By.xpath(
                        "//android.view.ViewGroup[contains(@content-desc, 'Photo taken')]"))
                .size();
    }
}
