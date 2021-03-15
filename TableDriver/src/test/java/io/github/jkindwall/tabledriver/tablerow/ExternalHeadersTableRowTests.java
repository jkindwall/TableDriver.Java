package io.github.jkindwall.tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;
import io.github.jkindwall.tabledriver.TableRow;

public class ExternalHeadersTableRowTests extends TableRowTestsBase
{
    private static final String TABLE_ID = "with-external-headers";
    private static final String HEADER_CSS = "#table-headers > span";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.createWithExternalHeaders(
            this.getDriver().findElement(By.id(ExternalHeadersTableRowTests.TABLE_ID)),
            this.getDriver().findElements(By.cssSelector(ExternalHeadersTableRowTests.HEADER_CSS)),
            0);
        return table.findRow(10);
    }

    @Test
    public void externalHeadersTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void externalHeadersTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void externalHeadersTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
