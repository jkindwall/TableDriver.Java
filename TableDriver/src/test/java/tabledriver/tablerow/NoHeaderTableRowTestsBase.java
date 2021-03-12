package tabledriver.tablerow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.NoSuchElementException;

import tabledriver.TableCell;
import tabledriver.TableRow;
import tabledriver.TableSamples;

public abstract class NoHeaderTableRowTestsBase extends TableRowTestsBase
{
    protected void testFindCell()
    {
        TableSamples.goToTestPage(this.getDriver());
        TableRow row = this.getTestTableRow();

        TableCell cell = row.findCell("2");
        assertNotNull(cell);
        assertEquals("Medium", cell.getElement().getText());

        cell = row.findCell(1);
        assertNotNull(cell);
        assertEquals("Dark Red", cell.getElement().getText());

        boolean exceptionThrown = false;
        try
        {
            cell = row.findCell("8");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: columnHeaderText.  The table does not contain a column with the header caption of '8'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            cell = row.findCell(5);
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }
}
