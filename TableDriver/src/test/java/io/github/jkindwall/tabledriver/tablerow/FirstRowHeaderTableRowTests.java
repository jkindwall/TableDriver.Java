package io.github.jkindwall.tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;
import io.github.jkindwall.tabledriver.TableRow;

public class FirstRowHeaderTableRowTests extends TableRowTestsBase
{
    private static final String TABLE_ID = "first-row-headers";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.create(this.getDriver().findElement(By.id(FirstRowHeaderTableRowTests.TABLE_ID)));
        return table.findRow(10);
    }

    @Test
    public void firstRowHeaderTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void firstRowHeaderTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void firstRowHeaderTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
