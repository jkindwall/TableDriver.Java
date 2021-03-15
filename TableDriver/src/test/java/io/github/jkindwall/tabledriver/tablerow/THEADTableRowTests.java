package io.github.jkindwall.tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;
import io.github.jkindwall.tabledriver.TableRow;

public class THEADTableRowTests extends TableRowTestsBase
{
    private static final String TABLE_ID = "with-thead-tbody";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.create(this.getDriver().findElement(By.id(THEADTableRowTests.TABLE_ID)));
        return table.findRow(10);
    }

    @Test
    public void theadTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void theadTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void theadTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
