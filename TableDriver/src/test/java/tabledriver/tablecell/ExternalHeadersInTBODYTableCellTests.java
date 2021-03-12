package tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableCell;

public class ExternalHeadersInTBODYTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "with-tbody-and-external-headers";
    private static final String HEADER_CSS = "#table-headers > span";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.createWithExternalHeaders(
            this.getDriver().findElement(By.id(ExternalHeadersInTBODYTableCellTests.TABLE_ID)),
            this.getDriver().findElements(By.cssSelector(ExternalHeadersInTBODYTableCellTests.HEADER_CSS)),
            0);
        return table.findCell(5, 1);
    }

    @Test
    public void externalHeadersInTBODYTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}
