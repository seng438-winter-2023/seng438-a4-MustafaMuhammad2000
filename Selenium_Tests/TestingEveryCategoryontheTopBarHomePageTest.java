// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class TestingEveryCategoryontheTopBarHomePageTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void testingEveryCategoryontheTopBarHomePage() {
    driver.get("https://www.amazon.ca/");
    driver.manage().window().setSize(new Dimension(1936, 1048));
    driver.findElement(By.linkText("Best Sellers")).click();
    driver.findElement(By.linkText("New Releases")).click();
    driver.findElement(By.linkText("Deals Store")).click();
    driver.findElement(By.linkText("Customer Service")).click();
    driver.findElement(By.cssSelector("#nav-link-amazonprime > span:nth-child(1)")).click();
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.linkText("Books")).click();
    driver.findElement(By.linkText("Toys & Games")).click();
    driver.findElement(By.linkText("Sports & Outdoors")).click();
    driver.findElement(By.linkText("Health & Household")).click();
    driver.findElement(By.linkText("Gift Cards")).click();
  }
}
