package Pages;

import com.sun.xml.internal.ws.policy.AssertionSet;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class BasePage {


    protected WebDriver driver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public static void acceptAlert(WebDriver driver){
        Alert alt = driver.switchTo().alert();
        System.out.println("Title "+ alt.getText());
        alt.accept();
    }

    public static void dismissAlert(WebDriver driver){
        Alert alt = driver.switchTo().alert();
        System.out.println("Title "+ alt.getText());
        alt.dismiss();
    }

    public static void acceptAlertAndSendValue(WebDriver driver, String text){
        Alert alt = driver.switchTo().alert();
        System.out.println("Title "+ alt.getText());
        alt.sendKeys(text);
        alt.accept();
    }

    public static void mouseHover(WebDriver driver, WebElement element){
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
    }

    public static void click(WebDriver driver, WebElement element){
        Actions act = new Actions(driver);
        act.click(element).build().perform();
    }

    public static void dragNDrop(WebDriver driver, WebElement src,WebElement dest){
        Actions act = new Actions(driver);
        act.dragAndDrop(src,dest).build().perform();
    }



    public  static void scrollTillElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }


    public static void takeSnapShot(WebDriver driver) {
        // Covert web driver to takescreenshot
        try {
            TakesScreenshot scrShot = (TakesScreenshot) driver;
            // call getScreenshotAs method to create image file
            File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

            File destFile = new File("./demo.png");

            FileUtils.copyFile(srcFile, destFile);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void fullScreenShot(WebDriver driver)  {
        try {
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
            ImageIO.write(screenshot.getImage(), "jpg", new File("./demofull.png"));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void selectByVisibletext(WebElement element,String text){
        Select sel = new Select(element);
        sel.selectByVisibleText(text);
    }

    public static void selectByvalue(WebElement element,String text){
        Select sel = new Select(element);
        sel.selectByValue(text);
    }

    public static void selectByindex(WebElement element,int index){
        Select sel = new Select(element);
        sel.selectByIndex(index);
    }



    public static String getValue(String key) {

        String path = System.getProperty("user.dir") + File.separator + "config.properties";
        String value = null;

        try {

            FileInputStream fis = new FileInputStream(path);
            Properties prop = new Properties();
            prop.load(fis);
            value = prop.getProperty(key);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return value;
    }


    public static void verifyTitle(WebElement element, String text){
        String actual = element.getText();
        System.out.println("Title: "+actual);
        Assert.assertEquals(actual,text,"Title mis match!");

    }

    public static void verifyElementEnable(WebElement element){
        System.out.println("Element isEnabled?  "+element.isEnabled());
       Assert.assertTrue(element.isEnabled());
    }

}
