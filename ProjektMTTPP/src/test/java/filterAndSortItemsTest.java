import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class filterAndSortItemsTest {
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
    public void selectCategoryTest() throws InterruptedException{
        Thread.sleep(4000);
        WebElement acceptCookiesButton = driver.findElement(By.xpath("/html/body/aside/div/div/div/button[1]"));
        acceptCookiesButton.click();
        Thread.sleep(5000);
        WebElement categoryButton = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[5]/div/div[2]/nav/ul/li[2]"));
        Thread.sleep(4000);
        categoryButton.click();
        Thread.sleep(2000);
        WebElement searchResultText = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div[1]/h1/span"));
        System.out.print(searchResultText.getText()+"\n");
        Assert.assertEquals(searchResultText.getText(), "Muškarci");
    }
    @Test (priority = 2)
    public void showOnlyDiscountedItemsTest() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,400)");
        Thread.sleep(500);
        WebElement discountedButton = driver.findElement(By.xpath("/html/body/div[1]/main/div[3]/div[2]/div/div[2]/dl/div[4]/dd/form/ol/li"));
        discountedButton.click();
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0,-400)");
        Thread.sleep(2000);
        WebElement selectedFilters = driver.findElement(By.xpath("/html/body/div[1]/main/div[3]/div[2]/div/div[1]/div[1]/ol/li[1]/span"));
        System.out.print(selectedFilters.getText());
        Assert.assertEquals(selectedFilters.getText(), "Sniženo");
    }

}
