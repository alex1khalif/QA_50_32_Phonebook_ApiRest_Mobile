package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    WebElement noContacts;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact list']")
    WebElement contactList;

    public boolean validateTextInContactListScreenAfterRegistration(String text, int time){
        return isTextInElementPresent(noContacts, text, time);
    }

    public boolean isElementContactListIsPresent(){
        return contactList.isDisplayed();
    }

}
