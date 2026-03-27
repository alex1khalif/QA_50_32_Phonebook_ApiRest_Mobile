package mobile_tests;

import dto.User;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import java.util.Map;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;
import static screens.MenuScreen.*;


public class RegistrationTests extends TestBase{

    LoginRegistrationScreen loginRegistrationScreen;

    @BeforeMethod
    public void openAuthScreen(){
        new SplashScreen(driver);
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
    }

    @Test
    public void registrationPositiveTest(){
        User user = positiveUser();
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ContactListScreen(driver)
                .validateTextInContactListScreenAfterRegistration
                        ("No Contacts. Add One more!", 5));
    }

    @Test
    public void registrationNegative_emptyEmailTest(){
        User user = positiveUser();
        user.setUsername("");
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("{username=must not be blank}", 5));

    }

    @Test
    public void registrationNegative_emptyPasswordTest(){
        User user = positiveUser();
        user.setPassword("");
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new MenuScreen(driver).appPhoneBookIsPresentInTelephoneMenu());

    }

    @Test
    public void registrationNegative_OnlyDigitsPasswordTest(){
        User user = positiveUser();
        user.setPassword("1234567890");
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("{password= At least 8 characters; Must contain at least 1 uppercase", 5));

    }

    @Test
    public void registrationNegative_WrongFormed_EmailTest(){
        User user = positiveUser();
        user.setUsername("alex1999gmail.com");
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("{username=must be a well-formed email address}", 5));

    }

    @Test
    public void registrationNegative_WithSpaceEmailTest(){
        User user = positiveUser();
        user.setUsername(" ");
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        //Assert.assertTrue(new ErrorScreen(driver)
          //      .validateTextInCrashScreen("Open app again", 5));
        Assert.assertTrue(new ErrorScreen(driver)
                .isAppStopDisplayed());

    }

    @Test
    public void registrationNegative_EmptyFieldsTest(){
        User user = new User("", "");
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .isAppStopDisplayed());

    }

    @Test
    public void registrationNegative_AlreadyExistsUser_Test(){
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("User already exists", 5));
    }

}
