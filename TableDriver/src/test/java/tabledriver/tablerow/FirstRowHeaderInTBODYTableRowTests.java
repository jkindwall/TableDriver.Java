package tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableRow;

public class FirstRowHeaderInTBODYTableRowTests extends TableRowTestsBase
{
    private static final String TABLE_ID = "first-row-headers-in-tbody";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.create(this.getDriver().findElement(By.id(FirstRowHeaderInTBODYTableRowTests.TABLE_ID)));
        return table.findRow(10);
    }

    @Test
    public void firstRowHeaderInTBODYTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void firstRowHeaderInTBODYTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void firstRowHeaderInTBODYTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
