package tabledriver.tablerow;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableRow;

public class ExternalHeadersInTBODYTableRowTests extends TableRowTestsBase
{
    private static final String TABLE_ID = "with-tbody-and-external-headers";
    private static final String HEADER_CSS = "#table-headers > span";

    @Override
    protected TableRow getTestTableRow()
    {
        Table table = Table.createWithExternalHeaders(
            this.getDriver().findElement(By.id(ExternalHeadersInTBODYTableRowTests.TABLE_ID)),
            this.getDriver().findElements(By.cssSelector(ExternalHeadersInTBODYTableRowTests.HEADER_CSS)),
            0);
        return table.findRow(10);
    }

    @Test
    public void externalHeadersInTBODYTableRowPropertiesTest()
    {
        this.testTableRowProperties();
    }

    @Test
    public void externalHeadersInTBODYTableRowGetCellsTest()
    {
        this.testGetCells();
    }

    @Test
    public void externalHeadersInTBODYTableRowFindCellTest()
    {
        this.testFindCell();
    }
}
