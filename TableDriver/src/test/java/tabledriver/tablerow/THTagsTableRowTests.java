package tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableRow;

public class THTagsTableRowTests extends TableRowTestsBase
{
    private static final String TABLE_ID = "with-th-tags";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.create(this.getDriver().findElement(By.id(THTagsTableRowTests.TABLE_ID)));
        return table.findRow(10);
    }

    @Test
    public void thTagsTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void thTagsTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void thTagsTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
