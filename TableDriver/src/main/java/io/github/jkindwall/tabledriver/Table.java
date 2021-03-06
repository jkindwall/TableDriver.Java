package io.github.jkindwall.tabledriver;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Class rerpresenting a table html element and all its contents
 */
public class Table
{
    private static final class FindHeaderResults
    {
        public final WebElement headerRow;
        public final int skipRows;

        public FindHeaderResults(WebElement headerRow, int skipRows)
        {
            this.headerRow = headerRow;
            this.skipRows = skipRows;
        }
    }

    private static final String INVALID_COLUMN_HEADER_EXCEPTION_TEMPLATE = 
        "Argument: columnHeaderText.  The table does not contain a column with the header caption of '%s'.";
    private static final String FIND_CELL_XPATH_TEMPLATE = "(%s)/*[self::th or self::td][%d]";

    private final Map<String, Integer> headers;
    private final int skipRows;
    private final String rowXPathPrefix;

    private final WebElement element;
    /**
     * Gets the WebElement representing the root "table" element
     * 
     * @return  The table WebElement
     */
    public WebElement getElement() 
    { 
        return this.element; 
    }

    private final WebElement headerRow;
    /**
     * Gets the WebElement representing the header row (if any) of the table (the "tr" element that contains the column
     * header captions)
     * 
     * @return  The header row WebElement
     */
    public WebElement getHeaderRow() 
    { 
        return this.headerRow; 
    }

    private List<WebElement> headerElements;
    /**
     * Gets the collection of WebElements representing header elements not contained within the main table element
     * 
     * @return  Collection of header WebElements
     */
    public List<WebElement> getHeaderElements() 
    { 
        return this.headerElements;
    }

    private static FindHeaderResults tryFindHeaderRow(WebElement element)
    {
        List<WebElement> theadRows = element.findElements(By.xpath("thead/tr"));
        if (!theadRows.isEmpty())
        {
            return new FindHeaderResults(theadRows.get(theadRows.size() - 1), 0);
        }

        List<WebElement> thRows = element.findElements(By.xpath("tbody/tr[th and not(td)] | tr[th and not(td)]"));
        if (!thRows.isEmpty())
        {
            return new FindHeaderResults(thRows.get(thRows.size() - 1), thRows.size());
        }

        return new FindHeaderResults(element.findElement(By.xpath("tbody/tr[1] | tr[1]")), 1);
    }

    private static String buildRowXPathPrefix(WebElement element, int skipRows)
    {
        StringBuilder builder = new StringBuilder();
        if (!element.findElements(By.xpath("tbody")).isEmpty())
        {
            builder.append("tbody/");
        }

        if (skipRows > 0)
        {
            builder.append(String.format("tr[%d]/following-sibling::tr", skipRows));
        }
        else
        {
            builder.append("tr");
        }

        return builder.toString();
    }

    private Map<String, Integer> loadHeaders()
    {
        List<WebElement> localHeaderElements = this.headerRow != null
            ? this.headerRow.findElements(By.xpath("th | td"))
            : this.headerElements;

        Map<String, Integer> localHeaders = new HashMap<>();
        if (localHeaderElements != null)
        {
            int columnIndex = 0;
            for (WebElement headerElement : localHeaderElements)
            {
                String headerText = headerElement.getText();
                int headerSuffix = 0;
                while (localHeaders.containsKey(headerText))
                {
                    headerText = String.format("%s-%s", headerElement.getText(), ++headerSuffix);
                }

                localHeaders.put(headerText, columnIndex++);
            }
        }
        else
        {
            List<WebElement> columns = this.element.findElements(
                By.xpath(String.format("%s[1]/*[self::th or self::td]", this.rowXPathPrefix)));
            for (int i = 0; i < columns.size(); i++)
            {
                String indexText = String.valueOf(i);
                localHeaders.put(indexText, i);
            }
        }

        return localHeaders;
    }

    /**
     * Creates a new instance of the Table class based on the specified "table" element
     * 
     * The Table class will attempt to infer the structure of the table based on a few standard table layouts.  If this 
     * does not work, try using one of the alternative Create methods that allow you to specify how to handle table
     * headers.
     * 
     * @param element   WebElement representing the "table" element
     * @return          The new Table instance
     */
    public static Table create(WebElement element)
    {
        FindHeaderResults findHeaderResults = Table.tryFindHeaderRow(element);
        return new Table(element, findHeaderResults.headerRow, null, findHeaderResults.skipRows);
    }

    /**
     * Creates a new instance of the Table class based on the specified "table" element
     * 
     * @param element           WebElement representing the "table" element
     * @param headerRowElement  WebElement representing the header row of the table
     * @param skipRows          The number of rows at the top of the table body that do not represent the table content
     *                          (usually because they contain a title row or column headers)
     * @return                  The new Table instance
     */
    public static Table createWithHeaderRow(WebElement element, WebElement headerRowElement, int skipRows)
    {
        return new Table(element, headerRowElement, null, skipRows);
    }

    /**
     * Creates a new instance of the Table class based on the specified "table" element, where the column headers are
     * located outside the table tag
     * 
     * Use this Create method for situatoins where the table headers are contained in a separate table element or some 
     * other type of web element container external to the main table element.
     * 
     * @param element           WebElement representing the "table" element
     * @param headerElements    Collection of WebElements representing column headers that are not contained within the
     *                          specified table element
     * @param skipRows          The number of rows at the top of the table body that do not represent the table content
     *                          (usually because they contain a title row or column headers)
     * @return                  The new Table instance
     */
    public static Table createWithExternalHeaders(WebElement element, List<WebElement> headerElements, int skipRows)
    {
        return new Table(element, null, headerElements, skipRows);
    }

    /**
     * Creates a new instance of the Table class based on the specified "table" element, with no displayed column headers
     * 
     * Table instances created with this method will only allow specifying columns by their numeric index.
     * 
     * @param element   WebElement representing the "table" element
     * @param skipRows  The number of rows at the top of the table body that do not represent the table content (usually 
     *                  because they contain a title row or column headers)
     * @return          The new Table instance
     */
    public static Table createWithNoHeaders(WebElement element, int skipRows)
    {
        return new Table(element, null, null, skipRows);
    }

    private Table(WebElement element, WebElement headerRowElement, List<WebElement> headerElements, int skipRows)
    {
        if (!"TABLE".equalsIgnoreCase(element.getTagName()))
        {
            throw new IllegalArgumentException("Argument: element.  Must specify a <table> element.");
        }

        if (headerRowElement != null && !"TR".equalsIgnoreCase(headerRowElement.getTagName()))
        {
            throw new IllegalArgumentException("Argument: headerRowElement. Must specify a <tr> element.");
        }

        this.element = element;
        this.headerRow = headerRowElement;
        this.headerElements = headerElements;
        this.skipRows = skipRows;
        this.rowXPathPrefix = Table.buildRowXPathPrefix(element, skipRows);
        this.headers = Collections.unmodifiableMap(this.loadHeaders());
    }

    /**
     * Gets all the content rows of the table
     * 
     * @return  Collection of TableRow objects representing all rows in the table
     */
    public List<TableRow> getRows()
    {
        List<TableRow> tableRows = this.element
            .findElements(By.xpath(this.rowXPathPrefix))
            .stream()
            .map(e -> new TableRow(e, this.headers, this.skipRows))
            .collect(Collectors.toList());
        return Collections.unmodifiableList(tableRows);
    }

    /**
     * Gets the first row in the table that matches the specified rowQuery string
     * 
     * @param rowQuery  A RowQuery string identifying one or more rows by their contents
     * @return          TableRow representing the first row that matches the specified rowQuery string
     */
    public TableRow findRow(String rowQuery)
    {
        String rowXPath = this.buildXPath(rowQuery);
        WebElement rowElement = this.element.findElement(By.xpath(rowXPath));
        return new TableRow(rowElement, this.headers, this.skipRows);
    }

    /**
     * Gets the specified row in the table
     * 
     * @param rowIndex  Index of the row
     * @return          TableRow representing the specified row in the table
     */
    public TableRow findRow(int rowIndex)
    {
        String rowXPath = this.buildXPath(rowIndex);
        WebElement rowElement = this.element.findElement(By.xpath(rowXPath));
        return new TableRow(rowElement, this.headers, this.skipRows);
    }

    /**
     * Gets all rows in the table that matches the specified rowQuery string
     * 
     * @param rowQuery  A RowQuery string identifying one or more rows by their contents
     * @return          Collection of TableRows representing all rows that match the specified rowQuery string
     */
    public List<TableRow> findRows(String rowQuery)
    {
        String rowXPath = this.buildXPath(rowQuery);
        List<TableRow> tableRows = this.element
            .findElements(By.xpath(rowXPath))
            .stream()
            .map(e -> new TableRow(e, this.headers, this.skipRows))
            .collect(Collectors.toList());
        return Collections.unmodifiableList(tableRows);
    }

    /**
     * Gets the cell in the specified column from the first row that matches the specified rowQuery string
     * 
     * @param rowQuery          A RowQuery string identifying one or more rows by their contents
     * @param columnHeaderText  Identifies the column under which to find cells by the column's header text
     * @return                  TableCell representing the cell under the specified column from the specified row
     */
    public TableCell findCell(String rowQuery, String columnHeaderText)
    {
        if (!this.headers.containsKey(columnHeaderText))
        {
            throw new IllegalArgumentException(String.format(
                Table.INVALID_COLUMN_HEADER_EXCEPTION_TEMPLATE, 
                columnHeaderText));
        }

        return this.findCell(rowQuery, this.headers.get(columnHeaderText));
    }

    /**
     * Gets the cell in the specified column from the first row that matches the specified rowQuery string
     * 
     * @param rowQuery      A RowQuery string identifying one or more rows by their contents
     * @param columnIndex   Identifies the column under which to find cells by the column's index
     * @return              TableCell representing the cell under the specified column from the specified row
     */
    public TableCell findCell(String rowQuery, int columnIndex)
    {
        String rowXPath = this.buildXPath(rowQuery);
        int xpathCellIndex = columnIndex + 1;
        String cellXPath = String.format(Table.FIND_CELL_XPATH_TEMPLATE, rowXPath, xpathCellIndex);
        WebElement cellElement = this.element.findElement(By.xpath(cellXPath));
        return new TableCell(cellElement, columnIndex, this.skipRows);
    }

    /**
     * Gets the cell in the specified column from the specified row
     * 
     * @param rowIndex          Index of the row
     * @param columnHeaderText  Identifies the column under which to find cells by the column's header text
     * @return                  TableCell representing the cell under the specified column from the specified row
     */
    public TableCell findCell(int rowIndex, String columnHeaderText)
    {
        if (!this.headers.containsKey(columnHeaderText))
        {
            throw new IllegalArgumentException(String.format(
                Table.INVALID_COLUMN_HEADER_EXCEPTION_TEMPLATE, 
                columnHeaderText));
        }

        return this.findCell(rowIndex, this.headers.get(columnHeaderText));
    }

    /**
     * Gets the cell in the specified column from the specified row
     * 
     * @param rowIndex      Index of the row
     * @param columnIndex   Identifies the column under which to find cells by the column's index
     * @return              TableCell representing the cell under the specified column from the specified row
     */
    public TableCell findCell(int rowIndex, int columnIndex)
    {
        String rowXPath = this.buildXPath(rowIndex);
        int xpathCellIndex = columnIndex + 1;
        String cellXPath = String.format(Table.FIND_CELL_XPATH_TEMPLATE, rowXPath, xpathCellIndex);
        WebElement cellElement = this.element.findElement(By.xpath(cellXPath));
        return new TableCell(cellElement, columnIndex, this.skipRows);
    }

    /**
     * Gets the cells in the specified column from the all rows that match the specified rowQuery string
     * 
     * @param rowQuery          A RowQuery string identifying one or more rows by their contents
     * @param columnHeaderText  Identifies the column under which to find cells by the column's header text
     * @return                  Collection of TableCells representing the cells under the specified column from the
     *                          specified rows
     */
    public List<TableCell> findCells(String rowQuery, String columnHeaderText)
    {
        if (!this.headers.containsKey(columnHeaderText))
        {
            throw new IllegalArgumentException(String.format(
                Table.INVALID_COLUMN_HEADER_EXCEPTION_TEMPLATE, 
                columnHeaderText));
        }

        return this.findCells(rowQuery, this.headers.get(columnHeaderText));
    }

    /// <summary>
    /// Gets the cells in the specified column from the all rows that match the specified rowQuery string
    /// </summary>
    /// <param name="rowQuery">A RowQuery string identifying one or more rows by their contents</param>
    /// <param name="columnIndex">Identifies the column under which to find cells by the column's index</param>
    /// <returns>Collection of TableCells representing the cells under the specified column from the specified rows</returns>
    /**
     * Gets the cells in the specified column from the all rows that match the specified rowQuery string
     * 
     * @param rowQuery      A RowQuery string identifying one or more rows by their contents
     * @param columnIndex   Identifies the column under which to find cells by the column's index
     * @return              Collection of TableCells representing the cells under the specified column from the specified
     *                      rows
     */
    public List<TableCell> findCells(String rowQuery, int columnIndex)
    {
        String rowXPath = this.buildXPath(rowQuery);
        int xpathCellIndex = columnIndex + 1;
        String cellXPath = String.format(Table.FIND_CELL_XPATH_TEMPLATE, rowXPath, xpathCellIndex);
        List<WebElement> tableCellElements = this.element
            .findElements(By.xpath(cellXPath));
        List<TableCell> tableCells = IntStream.range(0, tableCellElements.size())
            .mapToObj(i -> new TableCell(tableCellElements.get(i), i, this.skipRows))
            .collect(Collectors.toList());
        return Collections.unmodifiableList(tableCells);
    }

    private String buildXPath(int rowIndex)
    {
        int xpathRowIndex = rowIndex + 1;
        return String.format("%s[%d]", this.rowXPathPrefix, xpathRowIndex);
    }

    private String buildXPath(String rowQuery)
    {
        return this.buildXPath(OrGroup.parse(rowQuery));
    }

    private String buildXPath(OrGroup orGroup)
    {
        return String.join(
            " | ", 
            orGroup.getAndGroups()
                .stream()
                .map(this::buildXPath)
                .toArray(String[]::new));
    }

    private String buildXPath(AndGroup andGroup)
    {
        List<String> predicates = andGroup.getConditions()
            .stream()
            .map(this::buildXPathPredicate)
            .collect(Collectors.toList());
        String[] parts = Stream.concat(Stream.of(rowXPathPrefix), predicates.stream()).toArray(String[]::new);
        return String.join("", parts);
    }

    private String buildXPathPredicate(FieldCondition fieldCondition)
    {
        int xpathHeaderIndex = 0;
        if (fieldCondition.isFieldByIndex())
        {
            xpathHeaderIndex = fieldCondition.getFieldIndex().get() + 1;
        }
        else if (this.headers.containsKey(fieldCondition.getField()))
        {
            xpathHeaderIndex = this.headers.get(fieldCondition.getField()) + 1;
        }
        else
        {
            throw new IllegalArgumentException(String.format(
                "Argument: fieldCondition.  The table does not contain a column with the header caption of '%s'.", 
                fieldCondition.getField()));
        }

        if (fieldCondition.getOperation().equals(FieldOperation.STARTS_WITH))
        {
            return String.format(
                "[starts-with(*[self::th or self::td][%d],\"%s\")]", 
                xpathHeaderIndex, 
                fieldCondition.getValue());
        }
        else if (fieldCondition.getOperation().equals(FieldOperation.CONTAINS))
        {
            return String.format(
                "[contains(*[self::th or self::td][%d],\"%s\")]",
                xpathHeaderIndex,
                fieldCondition.getValue());
        }
        else
        {
            return String.format(
                "[*[self::th or self::td][%d]%s\"%s\"]",
                xpathHeaderIndex,
                fieldCondition.getOperation(),
                fieldCondition.getValue());
        }
    }

    /**
     * Gets the number of columns in the table
     * 
     * @return  The number of columns in the table
     */
    public int getColumnCount()
    {
        return this.headers.size();
    }

    /**
     * Gets the number of content rows in the table
     * 
     * @return  The number of content rows in the table
     */
    public int getRowCount()
    {
        return this.element.findElements(By.xpath(this.rowXPathPrefix)).size();
    }

    /**
     * Gets the specified column header element
     * 
     * @param headerText    Identifies the header to get by the header's text
     * @return              WebElement representing the specified column header
     */
    public WebElement getHeader(String headerText)
    {
        if (this.headerRow == null && this.headerElements == null)
        {
            throw new UnsupportedOperationException("This table does not have identified column headers.");
        }

        if (!this.headers.containsKey(headerText))
        {
            throw new IllegalArgumentException(String.format(
                "Argument: headerText.  The table does not contain a column with the header caption of '%s'.",
                headerText));
        }

        return this.getHeader(this.headers.get(headerText));
    }

    /**
     * Get sthe specified column header element
     * 
     * @param headerIndex   Identifies the header to get by the header's index
     * @return              WebElement representing the specified column header
     */
    public WebElement getHeader(int headerIndex)
    {
        if (this.headerRow != null)
        {
            int xpathIndex = headerIndex + 1;
            return this.headerRow.findElement(By.xpath(String.format("(td | th)[%d]", xpathIndex)));
        }
        else if (this.headerElements != null)
        {
            return this.headerElements.get(headerIndex);
        }

        throw new UnsupportedOperationException("This table does not have identified column headers.");
    }
}
