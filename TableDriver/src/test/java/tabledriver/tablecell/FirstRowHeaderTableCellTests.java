package tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableCell;

public class FirstRowHeaderTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "first-row-headers";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.create(this.getDriver().findElement(By.id(FirstRowHeaderTableCellTests.TABLE_ID)));
        return table.findCell(5, 1);
    }

    @Test
    public void firstRowHeaderTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}

