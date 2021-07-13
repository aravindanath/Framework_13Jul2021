package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//h1[text()='Admin area demo']")
    WebElement adminTitle;

    @FindBy(xpath = "//strong[text()='Welcome, please sign in!']")
    WebElement welcomeTitle;

    @FindBy(id = "Email")
    WebElement emailTextField;

    @FindBy(id="Password")
    WebElement passwordField;

    @FindBy(xpath = "//button[text()='Log in']")
    WebElement loginButton;

    @FindBy(xpath = "//h1[contains(text(),'Dashboard')]")
    WebElement dashboard;





    public void verifyLoginPage(){
        verifyTitle(adminTitle,"Admin area demo");
        verifyTitle(welcomeTitle,"Welcome, please sign in!");
        verifyElementEnable(emailTextField);
        verifyElementEnable(passwordField);
        verifyElementEnable(loginButton);
    }


    public void verifySuccesfulLogin(String email, String password){
        emailTextField.clear();
        emailTextField.sendKeys(email);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
        verifyTitle(dashboard,"Dashboard");
    }






}
