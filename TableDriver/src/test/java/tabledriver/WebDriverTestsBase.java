package tabledriver;

import java.util.concurrent.TimeUnit;

import javax.lang.model.util.ElementScanner6;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class WebDriverTestsBase
{
    private WebDriver driver;
    protected WebDriver getDriver() 
    { 
        return this.driver; 
    }

    @Before
    public void initWebDriver()
    {
        String browser = System.getenv("TEST_BROWSER");
        browser = browser == null ? Browser.CHROME_BROWSER : browser.toUpperCase();

        switch (browser)
        {
            case Browser.EDGE_BROWSER:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPageLoadStrategy("normal");
                this.driver = new EdgeDriver(edgeOptions);
                break;

            case Browser.CHROME_BROWSER:
                String pipelineChromeDriverPath = System.getenv("CHROMEWEBDRIVER");
                if (pipelineChromeDriverPath == null)
                {
                    WebDriverManager.chromedriver().setup();
                }
                else
                {
                    System.setProperty(
                        "webdriver.chrome.driver", 
                        String.format("%s/chromedriver", pipelineChromeDriverPath));
                }

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                this.driver = new ChromeDriver(chromeOptions);
                break;

            case Browser.FIREFOX_BROWSER:
                String pipelineFirefoxDriverPath = System.getenv("GECKOWEBDRIVER");
                if (pipelineFirefoxDriverPath == null)
                {
                    WebDriverManager.firefoxdriver().setup();
                }
                else
                {
                    System.setProperty(
                        "webdriver.gecko.driver",
                        String.format("%s/geckodriver", pipelineFirefoxDriverPath));
                }

                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                this.driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new UnsupportedOperationException(String.format("Invalid browser type specified: '%s'", browser));
        }

        this.driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
    }

    @After
    public void DriverShutdown()
    {
        this.driver.quit();
    }
}
