package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuScreen extends BaseScreen{
    public MenuScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/alertTitle']")
    WebElement crashApp;

    public boolean appPhoneBookIsPresentInTelephoneMenu(){
        return crashApp.isDisplayed();
    }
}
