import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenAndCheckWebsiteTest {
    public WebDriver driver;
    public String startURL = "https://www.google.hr/";
    //----------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\MTTPP\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(startURL);
    }
    @Test
    public void openFeriviTest() throws InterruptedException {
        WebElement acceptCookiesButton = driver.findElement(By.xpath("//*[@id=\"L2AGLb\"]"));
        acceptCookiesButton.click();
        Thread.sleep(2000);
        WebElement searchTextBox = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
        searchTextBox.sendKeys("Ferivi Sport");
        searchTextBox.submit();
        Thread.sleep(5500);
        WebElement websiteName = driver.findElement(By.xpath("/html/body/div[5]/div/div[11]/div/div[2]/div[2]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div/span"));
        websiteName.click();
        Thread.sleep(2000);
        WebElement header = driver.findElement(By.xpath("/html/body/div[1]/header"));
        Assert.assertTrue(header.isDisplayed());
    }
    @AfterMethod
    public void teardownTest() {
        //Close browser and end the session
        driver.quit();
    }
}