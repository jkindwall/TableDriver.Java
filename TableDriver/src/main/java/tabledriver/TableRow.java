package tabledriver;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Class rerpresenting a table row html element and all its contents
 */
public class TableRow
{
    private final Map<String, Integer> headers;
    private final int skipRows;

    private final WebElement element;
    /**
     * Gets the WebElement representing the "tr" element
     * 
     * @return  WebElement representing the "tr" element
     */
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

    /**
     * Gets the number of cells in this row
     * 
     * @return  The number of cells in this row
     */
    public int getCellCount()
    {
        return this.element.findElements(By.xpath("th | td")).size();
    }

    /**
     * Gets the index of this row in the content region of the table
     * 
     * @return  Gets the row index of this row
     */
    public int getRowIndex()
    {
        int precedingRowCount = this.element.findElements(By.xpath("preceding-sibling::tr")).size();
        return precedingRowCount - this.skipRows;
    }

    /**
     * Gets all TableCells in this row
     * 
     * @return  Collection of TableCells representing all cells in this row
     */
    public List<TableCell> getCells()
    {
        final List<WebElement> tableCellElements = this.element.findElements(By.xpath("th | td"));
        List<TableCell> tableCells = IntStream.range(0, tableCellElements.size())
            .mapToObj(i -> new TableCell(tableCellElements.get(i), i, this.skipRows))
            .collect(Collectors.toList());
        return Collections.unmodifiableList(tableCells);
    }

    /**
     * Gets the cell under the specified column from this row
     * 
     * @param columnHeaderText  Identifies the column under which to find cells by the column's header text
     * @return                  TableCell representing the cell under the specified column from this row
     */
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

    /**
     * Gets the cell under the specified column from this row
     * 
     * @param columnIndex   Identifies the column under which to find cells by the column's index
     * @return              TableCell representing the cell under the specified column from this row
     */
    public TableCell findCell(int columnIndex)
    {
        int xpathCellIndex = columnIndex + 1;
        return new TableCell(
            this.element.findElement(By.xpath(String.format("*[self::th or self::td][%d]", xpathCellIndex))), 
            columnIndex, 
            this.skipRows);
    }
}
