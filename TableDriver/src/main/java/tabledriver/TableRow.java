package tabledriver;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/// <summary>
/// Class rerpresenting a table row html element and all its contents
/// </summary>
public class TableRow
{
    private final Map<String, Integer> headers;
    private final int skipRows;

    private final WebElement element;
    /// <summary>
    /// IWebElement representing the "tr" element
    /// </summary>
    public WebElement getElement() 
    { 
        return this.element; 
    }

    TableRow(WebElement element, Map<String, Integer> headers, int skipRows)
    {
        if (!"TR".equalsIgnoreCase(element.getTagName()))
        {
            throw new IllegalArgumentException("Argument: element.  Must specify a <tr> element.");
        }

        this.element = element;
        this.headers = headers;
        this.skipRows = skipRows;
    }

    /// <summary>
    /// Gets the number of cells in the row
    /// </summary>
    public int getCellCount()
    {
        return this.element.findElements(By.xpath("th | td")).size();
    }

    /// <summary>
    /// Gets the index of the row in the content region of the table.
    /// </summary>
    public int getRowIndex()
    {
        int precedingRowCount = this.element.findElements(By.xpath("preceding-sibling::tr")).size();
        return precedingRowCount - this.skipRows;
    }

    /// <summary>
    /// Gets all TableCells in the row
    /// </summary>
    /// <returns>Collection of TableCells representing all cells in the row</returns>
    public List<TableCell> getCells()
    {
        final List<WebElement> tableCellElements = this.element.findElements(By.xpath("th | td"));
        List<TableCell> tableCells = IntStream.range(0, tableCellElements.size())
            .mapToObj(i -> new TableCell(tableCellElements.get(i), i, this.skipRows))
            .collect(Collectors.toList());
        return Collections.unmodifiableList(tableCells);
    }

    /// <summary>
    /// Gets the cell under the specified column from this row
    /// </summary>
    /// <param name="columnHeaderText">Identifies the column under which to find cells by the column's header text</param>
    /// <returns>TableCell representing the cell under the specified column from this row</returns>
    public TableCell findCell(String columnHeaderText)
    {
        if (!this.headers.containsKey(columnHeaderText))
        {
            throw new IllegalArgumentException(String.format(
                "Argument: columnHeaderText.  The table does not contain a column with the header caption of '%s'.",
                columnHeaderText));
        }

        return this.findCell(this.headers.get(columnHeaderText));
    }

    /// <summary>
    /// Gets the cell under the specified column from this row
    /// </summary>
    /// <param name="columnIndex">Identifies the column under which to find cells by the column's index</param>
    /// <returns>TableCell representing the cell under the specified column from this row</returns>
    public TableCell findCell(int columnIndex)
    {
        int xpathCellIndex = columnIndex + 1;
        return new TableCell(
            this.element.findElement(By.xpath(String.format("*[self::th or self::td][%d]", xpathCellIndex))), 
            columnIndex, 
            this.skipRows);
    }
}
