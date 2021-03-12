package tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableCell;

public class CustomHeaderRowInTBODYTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "custom-header-row-in-tbody";
    private static final String HEADER_CSS = "#custom-header-row-in-tbody tr[name=headerRow]";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.createWithHeaderRow(
            this.getDriver().findElement(By.id(CustomHeaderRowInTBODYTableCellTests.TABLE_ID)),
            this.getDriver().findElement(By.cssSelector(CustomHeaderRowInTBODYTableCellTests.HEADER_CSS)),
            2);
        return table.findCell(5, 1);
    }

    @Test
    public void customHeaderRowInTBODYTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}
