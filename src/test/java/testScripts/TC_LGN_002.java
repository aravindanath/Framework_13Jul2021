package testScripts;

import Pages.BasePage;
import Pages.LoginPage;
import org.testng.annotations.Test;


/**
 * @author aravindanathdm
 *
 * Verify sucessful login
 *
 */
public class TC_LGN_002 extends BaseTest{


    @Test
    public void TC_LGN_001(){

        String email = BasePage.getValue("email"), pass = BasePage.getValue("password");

        LoginPage lp = new LoginPage(driver);
        lp.verifyLoginPage();
        lp.verifySuccesfulLogin(email,pass);



    }

}
