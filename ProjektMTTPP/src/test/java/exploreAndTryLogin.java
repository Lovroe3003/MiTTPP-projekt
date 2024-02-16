import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class exploreAndTryLogin {
    public WebDriver driver;
    public String startURL = "https://ferivisport.hr/";
    //----------------------Test Setup-----------------------------------
    @BeforeClass
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\MTTPP\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(startURL);

    }
    @AfterClass
    public void teardownTest() {
        //Close browser and end the session
        driver.quit();
    }

    @Test (priority = 1)
    public void exploreLoyaltyClubPageTest() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(4000);
        WebElement acceptCookiesButton = driver.findElement(By.xpath("/html/body/aside/div/div/div/button[1]"));
        acceptCookiesButton.click();
        Thread.sleep(3000);
        WebElement loyaltyCLubBtn = driver.findElement(By.xpath("//*[@id=\"ui-id-193\"]"));
        loyaltyCLubBtn.click();
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0,600)");
        Thread.sleep(5000);
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,200)");
        Thread.sleep(1500);
        js.executeScript("window.scrollBy(0,-750)");
        Thread.sleep(2000);
        WebElement loyaltyClubText = driver.findElement(By.xpath("/html/body/div[1]/main/div[3]/div/div[2]/div/p"));
        Assert.assertTrue(loyaltyClubText.getText().contains("Ferivi Loyalty Klub"));
    }
    @Test(priority = 2)
    public void tryLoginTest() throws InterruptedException{
        FluentWait wait = new FluentWait(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement headerLoginButton = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div/div[1]/a"));
        headerLoginButton.click();
        Thread.sleep(2000);
        WebElement emailTextBox = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        WebElement passwordTextBox = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
        emailTextBox.sendKeys("mttppTestiranje@mail.com");
        passwordTextBox.sendKeys("testiranje2024");
        Thread.sleep(2000);
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"send2\"]"));
        submitButton.click();

        wait.withTimeout(15000, TimeUnit.MILLISECONDS);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.ignoring(AssertionError.class);
        //submitBtnText = submitButton.getText();
        wait.until(ExpectedConditions.textToBePresentInElement(submitButton, "GREŠKA, POKUŠAJTE PONOVO"));

        Assert.assertEquals(submitButton.getText(), "GREŠKA, POKUŠAJTE PONOVO");
    }
}
