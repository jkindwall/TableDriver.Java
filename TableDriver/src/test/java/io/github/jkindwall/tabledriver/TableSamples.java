package io.github.jkindwall.tabledriver;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;

public class TableSamples
{
    public static final String TEST_PAGE = "TableSamples.html";

    private static Object testPageUriLock = new Object();
    private static String testPageUri;
    public static String getTestPageUri()
    {
        if (TableSamples.testPageUri == null)
        {
            synchronized (TableSamples.testPageUriLock)
            {
                if (TableSamples.testPageUri == null)
                {
                    Path relativePath = Paths.get("src", "test", "resources", TableSamples.TEST_PAGE);
                    String absolutePath = relativePath.toFile().getAbsolutePath().replaceAll("\\\\", "/");
                    TableSamples.testPageUri = String.format("file:///%s", absolutePath);
                }
            }
        }

        return TableSamples.testPageUri;
    }

    public static void goToTestPage(WebDriver driver)
    {
        driver.navigate().to(TableSamples.getTestPageUri());
    }

    private TableSamples() { }
}
