package tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableRow;

public class NoHeaderTableRowTests extends NoHeaderTableRowTestsBase
{
    private static final String TABLE_ID = "no-headers";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.createWithNoHeaders(this.getDriver().findElement(By.id(NoHeaderTableRowTests.TABLE_ID)), 0);
        return table.findRow(10);
    }

    @Test
    public void noHeaderTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void noHeaderTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void noHeaderTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
