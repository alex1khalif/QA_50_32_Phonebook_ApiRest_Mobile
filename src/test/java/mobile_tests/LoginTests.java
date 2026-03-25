package mobile_tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.LoginRegistrationScreen;
import screens.SplashScreen;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.positiveUser;

public class LoginTests extends TestBase{
    LoginRegistrationScreen loginRegistrationScreen;

    @BeforeMethod
    public void openAuthScreen(){
        new SplashScreen(driver);
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
    }

    @Test
    public void loginPositiveTest(){
        User user = new User(getProperty("base.properties", "login"),
               getProperty("base.properties", "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(new ContactListScreen(driver).isElementContactListIsPresent());
    }
}
