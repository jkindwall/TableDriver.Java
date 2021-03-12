package tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableRow;

public class THTagsInTBODYTableRowTests extends TableRowTestsBase
{
    private static final String TABLE_ID = "with-th-tags-in-tbody";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.create(this.getDriver().findElement(By.id(THTagsInTBODYTableRowTests.TABLE_ID)));
        return table.findRow(10);
    }

    @Test
    public void thTagsInTBODYTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void thTagsInTBODYTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void thTagsInTBODYTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
