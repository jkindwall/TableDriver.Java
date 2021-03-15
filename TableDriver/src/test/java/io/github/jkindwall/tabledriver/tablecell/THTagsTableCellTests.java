package io.github.jkindwall.tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;
import io.github.jkindwall.tabledriver.TableCell;

public class THTagsTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "with-th-tags";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.create(this.getDriver().findElement(By.id(THTagsTableCellTests.TABLE_ID)));
        return table.findCell(5, 1);
    }

    @Test
    public void thTagsTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}
