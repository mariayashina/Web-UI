import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class CRMTest1 {
    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";

    public static void main (String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();

        login();

        Actions actions = new Actions(driver);
        WebElement projectMenuItem = driver.findElement(By.xpath("//span[text()='Проекты']/ancestor::a"));
        actions.moveToElement(projectMenuItem).perform();
        driver.findElement(By.xpath("//li[@data-route='crm_project_my']/a")).click();
        driver.findElement(By.xpath("//a[text()='Создать проект']")).click();
        driver.findElement(By.name("crm_project[name]")).sendKeys("Ромашка67");
        driver.findElement(By.xpath("//span[text()='Укажите организацию']/..")).click();
        driver.findElement(By.xpath("//input[@class='select2-input select2-focused']")).sendKeys("Test_from_GB");
        driver.findElement(By.xpath("//input[@class='select2-input select2-focused']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//div[contains(@id,'s2id_crm_project_contactMain')]/a")).click();
        driver.findElement(By.xpath("//select[@name=\"crm_project[contactMain]\"]/option[3]")).click();
        Select businessUnit = new Select(driver.findElement(By.xpath("//select[@data-name='field__business-unit']")));
        businessUnit.selectByVisibleText("Research & Development");
        Select curator = new Select(driver.findElement(By.xpath("//select[@data-name='field__curator']")));
        curator.selectByVisibleText("Applanatest Applanatest Applanatest");
        Select projectLeader = new Select(driver.findElement(By.xpath("//select[@data-name='field__rp']")));
        projectLeader.selectByVisibleText("Applanatest2 Applanatest2 Applanatest2");
        Select projectAdmin = new Select(driver.findElement(By.xpath("//select[@data-name='field__administrator']")));
        projectAdmin.selectByVisibleText("Исаева Анастасия");
        Select projectManager = new Select(driver.findElement(By.xpath("//select[@data-name='field__manager']")));
        projectManager.selectByVisibleText("Козлов Илья");

        driver.findElement(By.cssSelector(".btn-group:nth-child(4) > .btn")).click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Проект сохранен']")));

        driver.findElement(By.xpath("//*[text()='Проект сохранен']"));

        Thread.sleep(5000);

        driver.quit();
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();
    }
}
