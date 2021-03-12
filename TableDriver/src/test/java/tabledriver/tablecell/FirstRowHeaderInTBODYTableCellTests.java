package tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableCell;

public class FirstRowHeaderInTBODYTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "first-row-headers-in-tbody";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.create(this.getDriver().findElement(By.id(FirstRowHeaderInTBODYTableCellTests.TABLE_ID)));
        return table.findCell(5, 1);
    }

    @Test
    public void firstRowHeaderInTBODYTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}

