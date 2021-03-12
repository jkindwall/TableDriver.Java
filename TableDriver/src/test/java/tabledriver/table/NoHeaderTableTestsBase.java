package tabledriver.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import tabledriver.Table;
import tabledriver.TableCell;
import tabledriver.TableRow;
import tabledriver.TableSamples;

public abstract class NoHeaderTableTestsBase extends TableTestsBase
{
    protected void testTableProperties(String tableId)
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        assertEquals(5, table.getColumnCount());
        assertEquals(12, table.getRowCount());
        assertEquals("table", table.getElement().getTagName());
        assertEquals(tableId, table.getElement().getAttribute("id"));
        assertNull(table.getHeaderRow());
        assertNull(table.getHeaderElements());
    }

    protected void testFindRow()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        TableRow row = table.findRow("0=Grape");
        assertNotNull(row);
        assertEquals("3", row.getElement().getAttribute("name"));

        row = table.findRow("2=Large");
        assertNotNull(row);
        assertEquals("4", row.getElement().getAttribute("name"));

        row = table.findRow("\\4=6.3");
        assertNotNull(row);
        assertEquals("5", row.getElement().getAttribute("name"));

        row = table.findRow(8);
        assertNotNull(row);
        assertEquals("8", row.getElement().getAttribute("name"));

        boolean exceptionThrown = false;
        try
        {
            row = table.findRow("0=Lemon");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            row = table.findRow(12);
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            row = table.findRow("6=Fancy");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: fieldCondition.  The table does not contain a column with the header caption of '6'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            row = table.findRow("\\8=32");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    protected void testFindRows()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableRow> rows = table.findRows("3=Yes");
        assertNotNull(rows);
        assertEquals(3, rows.size());
        assertEquals("1", rows.get(0).getElement().getAttribute("name"));
        assertEquals("2", rows.get(1).getElement().getAttribute("name"));
        assertEquals("4", rows.get(2).getElement().getAttribute("name"));

        rows = table.findRows("0=Mango|1=Red&2=Small|\\4=5.0");
        assertNotNull(rows);
        assertEquals(4, rows.size());
        assertEquals("0", rows.get(0).getElement().getAttribute("name"));
        assertEquals("6", rows.get(1).getElement().getAttribute("name"));
        assertEquals("9", rows.get(2).getElement().getAttribute("name"));
        assertEquals("11", rows.get(3).getElement().getAttribute("name"));

        rows = table.findRows("1=Purple&2=Large");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        rows = table.findRows("\\13=Fred");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            rows = table.findRows("7=Near");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: fieldCondition.  The table does not contain a column with the header caption of '7'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);
    }

    protected void testFindCell()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        TableCell cell = table.findCell("0=Watermelon", "2");
        assertNotNull(cell);
        assertEquals("Large", cell.getElement().getText());

        cell = table.findCell("\\2=Small", 0);
        assertNotNull(cell);
        assertEquals("Grape", cell.getElement().getText());

        cell = table.findCell(10, "0");
        assertNotNull(cell);
        assertEquals("Mangosteen", cell.getElement().getText());

        cell = table.findCell(8, 4);
        assertNotNull(cell);
        assertEquals("4.7", cell.getElement().getText());

        boolean exceptionThrown = false;
        try
        {
            cell = table.findCell("0=Mulberry", "4");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            cell = table.findCell("\\6=Zesty", "4");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            cell = table.findCell("9=Obscure", "4");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: fieldCondition.  The table does not contain a column with the header caption of '9'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            cell = table.findCell("0=Apple", "8");
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
            cell = table.findCell(7, 9);
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    protected void testFindCells()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableCell> cells = table.findCells("1=Green", "0");
        assertNotNull(cells);
        assertEquals(2, cells.size());
        assertEquals("Watermelon", cells.get(0).getElement().getText());
        assertEquals("Kiwi", cells.get(1).getElement().getText());

        cells = table.findCells("0=Orange|1=Red", 4);
        assertNotNull(cells);
        assertEquals(4, cells.size());
        assertEquals("5.0", cells.get(0).getElement().getText());
        assertEquals("6.7", cells.get(1).getElement().getText());
        assertEquals("3.2", cells.get(2).getElement().getText());
        assertEquals("7.7", cells.get(3).getElement().getText());

        cells = table.findCells("3=Maybe", "0");
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        cells = table.findCells("\\9=Fluffy", 2);
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        cells = table.findCells("2=Large", 42);
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            cells = table.findCells("11=50%", 1);
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: fieldCondition.  The table does not contain a column with the header caption of '11'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            cells = table.findCells("\\2=Medium", "23");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: columnHeaderText.  The table does not contain a column with the header caption of '23'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);
    }

    protected void testGetHeader()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        boolean exceptionThrown = false;
        try
        {
            table.getHeader("0");
        }
        catch (UnsupportedOperationException ex)
        {
            exceptionThrown = true;
            assertEquals("This table does not have identified column headers.", ex.getMessage());
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            table.getHeader(0);
        }
        catch (UnsupportedOperationException ex)
        {
            exceptionThrown = true;
            assertEquals("This table does not have identified column headers.", ex.getMessage());
        }
        assertTrue(exceptionThrown);
    }

    protected void testNotEqualInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableRow> rows = table.findRows("3!=No");
        assertNotNull(rows);
        assertEquals(3, rows.size());
        assertEquals("1", rows.get(0).getElement().getAttribute("name"));
        assertEquals("2", rows.get(1).getElement().getAttribute("name"));
        assertEquals("4", rows.get(2).getElement().getAttribute("name"));
    }

    protected void testLessThanInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableRow> rows = table.findRows("4<3");
        assertNotNull(rows);
        assertEquals(2, rows.size());
        assertEquals("2", rows.get(0).getElement().getAttribute("name"));
        assertEquals("7", rows.get(1).getElement().getAttribute("name"));

        rows = table.findRows("\\4<1");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        rows = table.findRows("4<Schmoop");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            table.findRow("\\1<8");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    protected void testLessThanOrEqualInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableCell> cells = table.findCells("\\4<=3.2", 0);
        assertNotNull(cells);
        assertEquals(3, cells.size());
        assertEquals("Grapefruit", cells.get(0).getElement().getText());
        assertEquals("Strawberry", cells.get(1).getElement().getText());
        assertEquals("Watermelon", cells.get(2).getElement().getText());

        boolean exceptionThrown = false;
        try
        {
            table.findCell("4<=1.1", 0);
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        cells = table.findCells("\\4<=Floofy", 0);
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        cells = table.findCells("2<=90", 0);
        assertNotNull(cells);
        assertTrue(cells.isEmpty());
    }

    protected void testGreaterThanInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableRow> rows = table.findRows("4>7");
        assertNotNull(rows);
        assertEquals(5, rows.size());
        assertEquals("3", rows.get(0).getElement().getAttribute("name"));
        assertEquals("4", rows.get(1).getElement().getAttribute("name"));
        assertEquals("9", rows.get(2).getElement().getAttribute("name"));
        assertEquals("10", rows.get(3).getElement().getAttribute("name"));
        assertEquals("11", rows.get(4).getElement().getAttribute("name"));

        rows = table.findRows("\\4>100");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            table.findRow("4>_____T+++");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        rows = table.findRows("\\0>3");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());
    }

    protected void testGreaterThanOrEqualInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableCell> cells = table.findCells("\\4>=7.7", 0);
        assertNotNull(cells);
        assertEquals(4, cells.size());
        assertEquals("Grape", cells.get(0).getElement().getText());
        assertEquals("Pineapple", cells.get(1).getElement().getText());
        assertEquals("Mangosteen", cells.get(2).getElement().getText());
        assertEquals("Lychee", cells.get(3).getElement().getText());

        cells = table.findCells("4>=12.2", 0);
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        cells = table.findCells("\\4>=Six", 0);
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            table.findCell("3>=4.2", 0);
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    protected void testStartsWithInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableRow> rows = table.findRows("0^=Mango");
        assertNotNull(rows);
        assertEquals(2, rows.size());
        assertEquals("9", rows.get(0).getElement().getAttribute("name"));
        assertEquals("10", rows.get(1).getElement().getAttribute("name"));

        rows = table.findRows("\\0^=ape");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            table.findRow("1^=Big");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    protected void testContainsInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableCell> cells = table.findCells("\\0*=Grape", 4);
        assertNotNull(cells);
        assertEquals(2, cells.size());
        assertEquals("2.8", cells.get(0).getElement().getText());
        assertEquals("7.8", cells.get(1).getElement().getText());

        cells = table.findCells("1*=l", 0);
        assertNotNull(cells);
        assertEquals(5, cells.size());
        assertEquals("Grapefruit", cells.get(0).getElement().getText());
        assertEquals("Grape", cells.get(1).getElement().getText());
        assertEquals("Pineapple", cells.get(2).getElement().getText());
        assertEquals("Blueberry", cells.get(3).getElement().getText());
        assertEquals("Mango", cells.get(4).getElement().getText());

        cells = table.findCells("\\0*=berry", 1);
        assertNotNull(cells);
        assertEquals(2, cells.size());
        assertEquals("Blue", cells.get(0).getElement().getText());
        assertEquals("Red", cells.get(1).getElement().getText());

        boolean exceptionThrown = false;
        try
        {
            table.findCell("0*=q", 0);
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        cells = table.findCells("\\0*=Snapple", 3);
        assertNotNull(cells);
        assertTrue(cells.isEmpty());
    }
}
