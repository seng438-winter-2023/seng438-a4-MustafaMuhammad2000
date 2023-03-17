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
public class AddToCartEmptyCartTest {
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
  public void addToCartEmptyCart() {
    driver.get("https://www.amazon.ca//kindle-the-lightest-and-most-compact-kindle/dp/B09SWS16W6/?_encoding=UTF8&_ref=dlx_gate_dd_dcl_tlt_e23bcaf2_dt&pd_rd_w=905vL&content-id=amzn1.sym.1f5310db-faba-4c05-8f1b-0c0a5b93525f&pf_rd_p=1f5310db-faba-4c05-8f1b-0c0a5b93525f&pf_rd_r=GNAAJVW7S8HN3H307180&pd_rd_wg=F3ytt&pd_rd_r=75e1bca4-5cd0-44bf-9c6b-fe54c3c93023&ref_=pd_gw_unk");
    driver.manage().window().setSize(new Dimension(806, 816));
    {
      WebElement element = driver.findElement(By.cssSelector(".nav-imageHref > img"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.tagName("body"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element, 0, 0).perform();
    }
    driver.findElement(By.id("dealsx_atc_btn-announce")).click();
    {
      WebElement element = driver.findElement(By.id("dealsx_atc_btn-announce"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector("#abb-intl-product-view-B09NMXWC1T li:nth-child(3)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector("#abb-intl-product-view-B09NMXWC1T li:nth-child(3)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.cssSelector("#abb-intl-product-view-B09NMXWC1T li:nth-child(3)"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.cssSelector(".a-popover-wrapper:nth-child(2)")).click();
    driver.findElement(By.cssSelector(".abb-intl-decline .a-button-input")).click();
    driver.findElement(By.cssSelector(".nav-cart-icon")).click();
    driver.close();
  }
}
