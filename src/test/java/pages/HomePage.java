package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    @AndroidFindBy(id = "com.google.android.apps.photos:id/empty_view_container")
    private WebElement emptyViewContainer;
    @AndroidFindBy(id = "com.google.android.apps.photos:id/backup_understandability_image")
    private WebElement understandabilityImage;

    public HomePage(AndroidDriver driver) {
        super(driver);
    }


    public HomePage closeUnderstandabilityPopup() {
        if (understandabilityImage.isDisplayed())
            driver.navigate().back();
        return this;
    }

    public HomePage checkIfOnHomepage() {
        Assertions.assertTrue(emptyViewContainer.isDisplayed());
        return this;
    }
}
