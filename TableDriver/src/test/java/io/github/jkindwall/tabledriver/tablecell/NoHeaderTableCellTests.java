package io.github.jkindwall.tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;
import io.github.jkindwall.tabledriver.TableCell;

public class NoHeaderTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "no-headers";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.createWithNoHeaders(this.getDriver().findElement(By.id(NoHeaderTableCellTests.TABLE_ID)), 0);
        return table.findCell(5, 1);
    }

    @Test
    public void noHeaderTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}
