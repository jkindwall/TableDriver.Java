package tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableCell;

public class CustomHeaderRowTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "custom-header-row";
    private static final String HEADER_CSS = "#custom-header-row tr[name=headerRow]";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.createWithHeaderRow(
            this.getDriver().findElement(By.id(CustomHeaderRowTableCellTests.TABLE_ID)),
            this.getDriver().findElement(By.cssSelector(CustomHeaderRowTableCellTests.HEADER_CSS)),
            2);
        return table.findCell(5, 1);
    }

    @Test
    public void customHeaderRowTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}
