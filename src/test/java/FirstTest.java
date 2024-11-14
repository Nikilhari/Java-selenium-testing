import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class FirstTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\NIKIL HARI\\BEA\\Testing\\drivers\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://automationteststore.com/");
        driver.manage().window().maximize();

        try {
            registerNewUser(driver);
            Thread.sleep(2000);
            loginUser(driver);
            Thread.sleep(2000);
            AddToCart(driver);
        } finally {
            Thread.sleep(10000);
            driver.quit();
        }
    }

    public static void registerNewUser(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login or register"))).click();
        Thread.sleep(1000);

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Continue']")));
        continueButton.click();
        Thread.sleep(1000);



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("AccountFrm_firstname"))).sendKeys("John");
        driver.findElement(By.id("AccountFrm_lastname")).sendKeys("Doe");
        driver.findElement(By.id("AccountFrm_email")).sendKeys("KKKkKK.doe@example.com");
        driver.findElement(By.id("AccountFrm_address_1")).sendKeys("123 Main St");
        driver.findElement(By.id("AccountFrm_city")).sendKeys("Cityville");



        driver.findElement(By.id("AccountFrm_postcode")).sendKeys("12345");
        driver.findElement(By.id("AccountFrm_country_id")).sendKeys("United States");
        driver.findElement(By.id("AccountFrm_loginname")).sendKeys("Pradeeeep");
        driver.findElement(By.id("AccountFrm_password")).sendKeys("password123");
        driver.findElement(By.id("AccountFrm_confirm")).sendKeys("password123");
        driver.findElement(By.id("AccountFrm_newsletter0")).click();
        driver.findElement(By.id("AccountFrm_agree")).click();
        WebElement zoneDropdown = driver.findElement(By.id("AccountFrm_zone_id"));
        Select dropdown = new Select(driver.findElement(By.id("AccountFrm_zone_id")));
        dropdown.selectByIndex(3);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Continue']"))).click();
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("maintext")));
        Assert.assertTrue(successMessage.getText().contains("YOUR ACCOUNT HAS BEEN CREATED!"),
                "Registration failed or success message not displayed.");

        System.out.println("Registration test passed.");
    }

    public static void loginUser(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement logoffButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logoff")));
        logoffButton.click();
        Thread.sleep(1000);

        System.out.println("Logout option selected.");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Login or register"))).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginFrm_loginname"))).sendKeys("Pradeeeep");
        driver.findElement(By.id("loginFrm_password")).sendKeys("password123");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Login']"))).click();

        WebElement logincofirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("maintext")));
        Assert.assertTrue(logincofirmation.getText().contains("MY ACCOUNT"), "Login failed or logout option not displayed.");
        System.out.println("Login test passed.");
    }
    public static void AddToCart(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement men = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://automationteststore.com/index.php?rt=product/category&path=58']")));
        men.click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://automationteststore.com/index.php?rt=product/category&path=58_60']"))).click();
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='productcart'])[1]")));
        addToCartButton.click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://automationteststore.com/index.php?rt=checkout/cart']"))).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart_checkout1"))).click();
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout_btn"))).click();
        Thread.sleep(1000);

        WebElement orderConformation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("maintext")));
        Assert.assertTrue(orderConformation.getText().contains("CHECKOUT CONFIRMATION"), "CHECK OUT FAILED");
        System.out.println("Order placed successfully");


    }
}
