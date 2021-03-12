package tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableCell;

public class NoHeaderInTBODYTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "no-headers-in-tbody";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.createWithNoHeaders(this.getDriver().findElement(By.id(NoHeaderInTBODYTableCellTests.TABLE_ID)), 0);
        return table.findCell(5, 1);
    }

    @Test
    public void noHeaderInTBODYTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}
