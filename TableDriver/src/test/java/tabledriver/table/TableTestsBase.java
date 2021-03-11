package tabledriver.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import tabledriver.Table;
import tabledriver.TableCell;
import tabledriver.TableRow;
import tabledriver.TableSamples;
import tabledriver.WebDriverTestsBase;

public abstract class TableTestsBase extends WebDriverTestsBase
{
    protected abstract Table getTestTable();

    protected void testTableProperties(String tableId)
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        assertEquals(5, table.getColumnCount());
        assertEquals(12, table.getRowCount());
        assertEquals("table", table.getElement().getTagName());
        assertEquals(tableId, table.getElement().getAttribute("id"));
        assertEquals("tr", table.getHeaderRow().getTagName());
        assertEquals("headerRow", table.getHeaderRow().getAttribute("name"));
        assertNull(table.getHeaderElements());
    }

    protected void testGetRows()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableRow> rows = table.getRows();
        assertNotNull(rows);
        assertEquals(12, rows.size());
        for (int i = 0; i < 12; i++)
        {
            assertEquals(String.valueOf(i), rows.get(i).getElement().getAttribute("name"));
        }
    }

    protected void testFindRow()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        TableRow row = table.findRow("Name=Grape");
        assertNotNull(row);
        assertEquals("3", row.getElement().getAttribute("name"));

        row = table.findRow("Size=Large");
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
            row = table.findRow("Name=Lemon");
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
            row = table.findRow("Style=Fancy");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: fieldCondition.  The table does not contain a column with the header caption of 'Style'.",
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

        List<TableRow> rows = table.findRows("Citrus?=Yes");
        assertNotNull(rows);
        assertEquals(3, rows.size());
        assertEquals("1", rows.get(0).getElement().getAttribute("name"));
        assertEquals("2", rows.get(1).getElement().getAttribute("name"));
        assertEquals("4", rows.get(2).getElement().getAttribute("name"));

        rows = table.findRows("Name=Mango|Color=Red&Size=Small|\\4=5.0");
        assertNotNull(rows);
        assertEquals(4, rows.size());
        assertEquals("0", rows.get(0).getElement().getAttribute("name"));
        assertEquals("6", rows.get(1).getElement().getAttribute("name"));
        assertEquals("9", rows.get(2).getElement().getAttribute("name"));
        assertEquals("11", rows.get(3).getElement().getAttribute("name"));

        rows = table.findRows("Color=Purple&Size=Large");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        rows = table.findRows("\\13=Fred");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            rows = table.findRows("Proximity=Near");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: fieldCondition.  The table does not contain a column with the header caption of 'Proximity'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);
    }

    protected void testFindCell()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        TableCell cell = table.findCell("Name=Watermelon", "Size");
        assertNotNull(cell);
        assertEquals("Large", cell.getElement().getText());

        cell = table.findCell("\\2=Small", 0);
        assertNotNull(cell);
        assertEquals("Grape", cell.getElement().getText());

        cell = table.findCell(10, "Name");
        assertNotNull(cell);
        assertEquals("Mangosteen", cell.getElement().getText());

        cell = table.findCell(8, 4);
        assertNotNull(cell);
        assertEquals("4.7", cell.getElement().getText());

        boolean exceptionThrown = false;
        try
        {
            cell = table.findCell("Name=Mulberry", "Rating");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            cell = table.findCell("\\6=Zesty", "Rating");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            cell = table.findCell("Fame=Obscure", "Rating");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: fieldCondition.  The table does not contain a column with the header caption of 'Fame'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            cell = table.findCell("Name=Apple", "Weight");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: columnHeaderText.  The table does not contain a column with the header caption of 'Weight'.",
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

        List<TableCell> cells = table.findCells("Color=Green", "Name");
        assertNotNull(cells);
        assertEquals(2, cells.size());
        assertEquals("Watermelon", cells.get(0).getElement().getText());
        assertEquals("Kiwi", cells.get(1).getElement().getText());

        cells = table.findCells("Name=Orange|Color=Red", 4);
        assertNotNull(cells);
        assertEquals(4, cells.size());
        assertEquals("5.0", cells.get(0).getElement().getText());
        assertEquals("6.7", cells.get(1).getElement().getText());
        assertEquals("3.2", cells.get(2).getElement().getText());
        assertEquals("7.7", cells.get(3).getElement().getText());

        cells = table.findCells("Citrus?=Maybe", "Name");
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        cells = table.findCells("\\9=Fluffy", 2);
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        cells = table.findCells("Size=Large", 42);
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            cells = table.findCells("Skin Transparency=50%", 1);
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: fieldCondition.  The table does not contain a column with the header caption of 'Skin Transparency'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            cells = table.findCells("\\2=Medium", "Sweetness");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: columnHeaderText.  The table does not contain a column with the header caption of 'Sweetness'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);
    }

    protected void testGetHeader()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        WebElement header = table.getHeader("Size");
        assertNotNull(header);
        assertEquals("Size", header.getText());

        header = table.getHeader(0);
        assertNotNull(header);
        assertEquals("Name", header.getText());

        boolean exceptionThrown = false;
        try
        {
            header = table.getHeader("Quantity");
        }
        catch (IllegalArgumentException ex)
        {
            exceptionThrown = true;
            assertEquals(
                "Argument: headerText.  The table does not contain a column with the header caption of 'Quantity'.",
                ex.getMessage());
        }
        assertTrue(exceptionThrown);

        exceptionThrown = false;
        try
        {
            header = table.getHeader(8);
        }
        catch (NoSuchElementException ex)
        {
            if (table.getHeaderRow() == null) { throw ex; }
            exceptionThrown = true;
        }
        catch (IndexOutOfBoundsException ex)
        {
            if (table.getHeaderElements() == null) { throw ex; }
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    protected void testNotEqualInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableRow> rows = table.findRows("Citrus?!=No");
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

        List<TableRow> rows = table.findRows("Rating<3");
        assertNotNull(rows);
        assertEquals(2, rows.size());
        assertEquals("2", rows.get(0).getElement().getAttribute("name"));
        assertEquals("7", rows.get(1).getElement().getAttribute("name"));

        rows = table.findRows("Rating<1");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        rows = table.findRows("Rating<Schmoop");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            table.findRow("Color<8");
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

        List<TableCell> cells = table.findCells("Rating<=3.2", "Name");
        assertNotNull(cells);
        assertEquals(3, cells.size());
        assertEquals("Grapefruit", cells.get(0).getElement().getText());
        assertEquals("Strawberry", cells.get(1).getElement().getText());
        assertEquals("Watermelon", cells.get(2).getElement().getText());

        boolean exceptionThrown = false;
        try
        {
            table.findCell("Rating<=1.1", "Name");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        cells = table.findCells("Rating<=Floofy", "Name");
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        cells = table.findCells("Size<=90", "Name");
        assertNotNull(cells);
        assertTrue(cells.isEmpty());
    }

    protected void testGreaterThanInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableRow> rows = table.findRows("Rating>7");
        assertNotNull(rows);
        assertEquals(5, rows.size());
        assertEquals("3", rows.get(0).getElement().getAttribute("name"));
        assertEquals("4", rows.get(1).getElement().getAttribute("name"));
        assertEquals("9", rows.get(2).getElement().getAttribute("name"));
        assertEquals("10", rows.get(3).getElement().getAttribute("name"));
        assertEquals("11", rows.get(4).getElement().getAttribute("name"));

        rows = table.findRows("Rating>100");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            table.findRow("Rating>_____T+++");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        rows = table.findRows("Name>3");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());
    }

    protected void testGreaterThanOrEqualInRowQuery()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        List<TableCell> cells = table.findCells("Rating>=7.7", "Name");
        assertNotNull(cells);
        assertEquals(4, cells.size());
        assertEquals("Grape", cells.get(0).getElement().getText());
        assertEquals("Pineapple", cells.get(1).getElement().getText());
        assertEquals("Mangosteen", cells.get(2).getElement().getText());
        assertEquals("Lychee", cells.get(3).getElement().getText());

        cells = table.findCells("Rating>=12.2", "Name");
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        cells = table.findCells("Rating>=Six", "Name");
        assertNotNull(cells);
        assertTrue(cells.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            table.findCell("Citrus?>=4.2", "Name");
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

        List<TableRow> rows = table.findRows("Name^=Mango");
        assertNotNull(rows);
        assertEquals(2, rows.size());
        assertEquals("9", rows.get(0).getElement().getAttribute("name"));
        assertEquals("10", rows.get(1).getElement().getAttribute("name"));

        rows = table.findRows("Name^=ape");
        assertNotNull(rows);
        assertTrue(rows.isEmpty());

        boolean exceptionThrown = false;
        try
        {
            table.findRow("Color^=Big");
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

        List<TableCell> cells = table.findCells("Name*=Grape", "Rating");
        assertNotNull(cells);
        assertEquals(2, cells.size());
        assertEquals("2.8", cells.get(0).getElement().getText());
        assertEquals("7.8", cells.get(1).getElement().getText());

        cells = table.findCells("Color*=l", "Name");
        assertNotNull(cells);
        assertEquals(5, cells.size());
        assertEquals("Grapefruit", cells.get(0).getElement().getText());
        assertEquals("Grape", cells.get(1).getElement().getText());
        assertEquals("Pineapple", cells.get(2).getElement().getText());
        assertEquals("Blueberry", cells.get(3).getElement().getText());
        assertEquals("Mango", cells.get(4).getElement().getText());

        cells = table.findCells("Name*=berry", "Color");
        assertNotNull(cells);
        assertEquals(2, cells.size());
        assertEquals("Blue", cells.get(0).getElement().getText());
        assertEquals("Red", cells.get(1).getElement().getText());

        boolean exceptionThrown = false;
        try
        {
            table.findCell("Name*=q", "Name");
        }
        catch (NoSuchElementException ex)
        {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);

        cells = table.findCells("Name*=Snapple", "Citrus?");
        assertNotNull(cells);
        assertTrue(cells.isEmpty());
    }
}
