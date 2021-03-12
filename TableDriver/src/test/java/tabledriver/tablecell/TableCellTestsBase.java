package tabledriver.tablecell;

import static org.junit.Assert.assertEquals;

import tabledriver.TableCell;
import tabledriver.TableSamples;
import tabledriver.WebDriverTestsBase;

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
