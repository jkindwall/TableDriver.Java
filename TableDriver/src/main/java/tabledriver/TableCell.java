package tabledriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Class rerpresenting a table cell html element
 */
public class TableCell
{
    private final int columnIndex; 
    /**
     * Gets the index of the column under which this cell is found
     * 
     * @return  Column index of this cell
     */
    public int getColumnIndex() 
    { 
        return this.columnIndex; 
    }

    private final WebElement element;
    /**
     * Gets the WebElement representing the "td" or "th" element
     * 
     * @return  WebElement representing the "td" or "th" element
     */
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

    /**
     * Gets the index of this cell's row in the content region of the table
     * 
     * @return Row index of this cell
     */
    public int getRowIndex()
    {
        int precedingRowCount = this.element.findElements(By.xpath("../preceding-sibling::tr")).size();
        return precedingRowCount - this.skipRows;
    }
}
