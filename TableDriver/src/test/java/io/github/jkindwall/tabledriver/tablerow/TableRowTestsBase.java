package io.github.jkindwall.tabledriver.tablerow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import io.github.jkindwall.tabledriver.TableCell;
import io.github.jkindwall.tabledriver.TableRow;
import io.github.jkindwall.tabledriver.TableSamples;
import io.github.jkindwall.tabledriver.WebDriverTestsBase;

public abstract class TableRowTestsBase extends WebDriverTestsBase
{
    protected abstract TableRow getTestTableRow();

    protected void testTableRowProperties()
    {
        TableSamples.goToTestPage(this.getDriver());
        TableRow row = this.getTestTableRow();

        assertEquals(10, row.getRowIndex());
        assertEquals(5, row.getCellCount());
        assertEquals("tr", row.getElement().getTagName());
        assertEquals("10", row.getElement().getAttribute("name"));
    }

    protected void testGetCells()
    {
        TableSamples.goToTestPage(this.getDriver());
        TableRow row = this.getTestTableRow();

        List<TableCell> cells = row.getCells();
        assertNotNull(cells);
        assertEquals(5, cells.size());
        assertEquals("Mangosteen", cells.get(0).getElement().getText());
        assertEquals("Dark Red", cells.get(1).getElement().getText());
        assertEquals("Medium", cells.get(2).getElement().getText());
        assertEquals("No", cells.get(3).getElement().getText());
        assertEquals("11.0", cells.get(4).getElement().getText());
    }

    protected void testFindCell()
    {
        TableSamples.goToTestPage(this.getDriver());
        TableRow row = this.getTestTableRow();

        TableCell cell = row.findCell("Size");
        assertNotNull(cell);
        assertEquals("Medium", cell.getElement().getText());

        cell = row.findCell(1);
        assertNotNull(cell);
        assertEquals("Dark Red", cell.getElement().getText());

        boolean exceptionThrown = false;
        try
        {
            cell = row.findCell("Alias");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: columnHeaderText.  The table does not contain a column with the header caption of 'Alias'.",
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
