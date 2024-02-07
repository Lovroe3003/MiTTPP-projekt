import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class addToCartTest {
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
    public void openSpecificItemPageTest() throws InterruptedException {
        Thread.sleep(6000);
        WebElement acceptCookiesButton = driver.findElement(By.xpath("/html/body/aside/div/div/div/button[1]"));
        acceptCookiesButton.click();
        Thread.sleep(5000);
        WebElement searchTextBox = driver.findElement(By.xpath("//*[@id=\"search\"]"));
        searchTextBox.sendKeys("nike hlače fb8002");
        Thread.sleep(1000);
        searchTextBox.submit();
        Thread.sleep(5000);
        WebElement searchResult = driver.findElement(By.xpath("/html/body/div[1]/main/div[3]/div[1]/div[1]/div/div[2]/ol/li/div/div/strong/a"));
        searchResult.click();
        Thread.sleep(4000);
        WebElement itemSKU = driver.findElement(By.xpath("/html/body/div[1]/main/div[2]/div/div[1]/div[2]/div[3]"));
        System.out.print(itemSKU.getText());
        Assert.assertTrue(itemSKU.getText().contains("FB8002"));
        Thread.sleep(4000);
    }
    @Test (priority = 2)
    public void addtItemToCartTest() throws InterruptedException {
        WebElement sizeLargeBtn = driver.findElement(By.xpath("//*[@id=\"option-label-ferivi_velicina-136-item-42\"]"));
        sizeLargeBtn.click();
        WebElement addToCartBtn = driver.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]"));
        addToCartBtn.click();
        Thread.sleep(3000);
        WebElement cartSize = driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div/div/div/div[1]/strong/span[2]"));
        int numberOfItemsInCart = Integer.parseInt(cartSize.getText());
//        System.out.print("\n Number of items in cart: "+ numberOfItemsInCart);
        if (numberOfItemsInCart>0){
            System.out.print("\nSuccessfully added to cart. Number of items in cart: "+ numberOfItemsInCart);
        }
        else System.out.print("Adding to cart failed.");
        Assert.assertTrue(numberOfItemsInCart>0);
    }
}
