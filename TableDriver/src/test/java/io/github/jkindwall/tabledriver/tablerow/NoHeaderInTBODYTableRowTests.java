package io.github.jkindwall.tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;
import io.github.jkindwall.tabledriver.TableRow;

public class NoHeaderInTBODYTableRowTests extends NoHeaderTableRowTestsBase
{
    private static final String TABLE_ID = "no-headers-in-tbody";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.createWithNoHeaders(this.getDriver().findElement(By.id(NoHeaderInTBODYTableRowTests.TABLE_ID)), 0);
        return table.findRow(10);
    }

    @Test
    public void noHeaderInTBODYTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void noHeaderInTBODYTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void noHeaderInTBODYTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
