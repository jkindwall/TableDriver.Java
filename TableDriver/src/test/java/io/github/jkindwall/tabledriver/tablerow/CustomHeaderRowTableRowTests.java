package io.github.jkindwall.tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;
import io.github.jkindwall.tabledriver.TableRow;

public class CustomHeaderRowTableRowTests extends TableRowTestsBase
{
    private static final String TABLE_ID = "custom-header-row";
    private static final String HEADER_CSS = "#custom-header-row tr[name=headerRow]";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.createWithHeaderRow(
            this.getDriver().findElement(By.id(CustomHeaderRowTableRowTests.TABLE_ID)),
            this.getDriver().findElement(By.cssSelector(CustomHeaderRowTableRowTests.HEADER_CSS)),
            2);
        return table.findRow(10);
    }

    @Test
    public void customHeaderRowTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void customHeaderRowTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void customHeaderRowTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
