package io.github.jkindwall.tabledriver.tablecell;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;
import io.github.jkindwall.tabledriver.TableCell;

public class ExternalHeadersTableCellTests extends TableCellTestsBase
{
    private static final String TABLE_ID = "with-external-headers";
    private static final String HEADER_CSS = "#table-headers > span";

    @Override
    protected TableCell getTestTableCell()
    {
        Table table = Table.createWithExternalHeaders(
            this.getDriver().findElement(By.id(ExternalHeadersTableCellTests.TABLE_ID)),
            this.getDriver().findElements(By.cssSelector(ExternalHeadersTableCellTests.HEADER_CSS)),
            0);
        return table.findCell(5, 1);
    }

    @Test
    public void externalHeadersTableCellPropertiesTest()
    {
        this.testTableCellProperties();
    }
}
