package tabledriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/// <summary>
/// Class rerpresenting a table cell html element
/// </summary>
public class TableCell
{
    private final int columnIndex; 
    /// <summary>
    /// Gets the index of the column under which this cell is found
    /// </summary>
    public int getColumnIndex() 
    { 
        return this.columnIndex; 
    }

    private final WebElement element;
    /// <summary>
    /// IWebElement representing the "td" element
    /// </summary>
    public WebElement getElement() 
    { 
        return this.element;
    }

    private final int skipRows;

    TableCell(WebElement element, int columnIndex, int skipRows)
    {
        if (!"TH".equalsIgnoreCase(element.getTagName()) && !"TD".equalsIgnoreCase(element.getTagName()))
        {
            throw new IllegalArgumentException("Argument: element.  Must specify a <td> or a <th> element.");
        }

        this.element = element;
        this.columnIndex = columnIndex;
        this.skipRows = skipRows;
    }

    /// <summary>
    /// Gets the index of this cell's row in the content region of the table.
    /// </summary>
    public int getRowIndex()
    {
        int precedingRowCount = this.element.findElements(By.xpath("../preceding-sibling::tr")).size();
        return precedingRowCount - this.skipRows;
    }
}
