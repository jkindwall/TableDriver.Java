package io.github.jkindwall.tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;
import io.github.jkindwall.tabledriver.TableCell;

public class THTagsInTBODYTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "with-th-tags-in-tbody";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.create(this.getDriver().findElement(By.id(THTagsInTBODYTableCellTests.TABLE_ID)));
        return table.findCell(5, 1);
    }

    @Test
    public void thTagsInTBODYTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}
