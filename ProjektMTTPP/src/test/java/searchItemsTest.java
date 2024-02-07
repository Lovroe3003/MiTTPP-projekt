import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class searchItemsTest {
    public WebDriver driver;
    public String startURL = "https://ferivisport.hr/";
    //----------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\MTTPP\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(startURL);
    }
    @Test
    public void filterItemsTest() throws InterruptedException {
        Thread.sleep(6000);
        WebElement acceptCookiesButton = driver.findElement(By.xpath("/html/body/aside/div/div/div/button[1]"));
        acceptCookiesButton.click();
        Thread.sleep(5000);
        WebElement searchTextBox = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        searchTextBox.sendKeys("Adidas Samba");
        Thread.sleep(1500);
        searchTextBox.submit();
        Thread.sleep(5500);
        WebElement searchResultText = driver.findElement(By.xpath("/html/body/div[1]/main/div[1]/h1/span"));
        System.out.print(searchResultText.getText());
        Assert.assertTrue(searchResultText.getText().contains("Adidas Samba"));
        Thread.sleep(2000);
    }
    @AfterMethod
    public void teardownTest() {
        //Close browser and end the session
        driver.quit();
    }
}
