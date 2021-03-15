package io.github.jkindwall.tabledriver.tablecell;

import static org.junit.Assert.assertEquals;

import io.github.jkindwall.tabledriver.TableCell;
import io.github.jkindwall.tabledriver.TableSamples;
import io.github.jkindwall.tabledriver.WebDriverTestsBase;

public abstract class TableCellTestsBase extends WebDriverTestsBase
{
    protected abstract TableCell getTestTableCell();

    protected void testTableCellProperties()
    {
        TableSamples.goToTestPage(this.getDriver());
        TableCell cell = this.getTestTableCell();

        assertEquals(5, cell.getRowIndex());
        assertEquals(1, cell.getColumnIndex());
        assertEquals("td", cell.getElement().getTagName());
        assertEquals("Blue", cell.getElement().getText());
    }
}
