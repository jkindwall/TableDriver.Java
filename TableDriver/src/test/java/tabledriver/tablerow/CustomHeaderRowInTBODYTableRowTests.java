package tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableRow;

public class CustomHeaderRowInTBODYTableRowTests extends TableRowTestsBase
{
    private static final String TABLE_ID = "custom-header-row-in-tbody";
    private static final String HEADER_CSS = "#custom-header-row-in-tbody tr[name=headerRow]";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.createWithHeaderRow(
            this.getDriver().findElement(By.id(CustomHeaderRowInTBODYTableRowTests.TABLE_ID)),
            this.getDriver().findElement(By.cssSelector(CustomHeaderRowInTBODYTableRowTests.HEADER_CSS)),
            2);
        return table.findRow(10);
    }

    @Test
    public void customHeaderRowInTBODYTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void customHeaderRowInTBODYTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void customHeaderRowInTBODYTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
