package tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;

public class ExternalHeadersTableTests extends ExternalHeadersTableTestsBase
{
    private static final String TABLE_ID = "with-external-headers";
    private static final String HEADER_CSS = "#table-headers > span";

    @Override
    protected Table getTestTable()
    {
        return Table.createWithExternalHeaders(
            this.getDriver().findElement(By.id(ExternalHeadersTableTests.TABLE_ID)),
            this.getDriver().findElements(By.cssSelector(ExternalHeadersTableTests.HEADER_CSS)),
            0);
    }

    @Test
    public void externalHeadersTablePropertiesTest()
    {
        this.testTableProperties(ExternalHeadersTableTests.TABLE_ID);
    }

    @Test
    public void externalHeadersTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void externalHeadersTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void externalHeadersTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void externalHeadersTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void externalHeaderstheadTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void externalHeadersTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void externalHeadersTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void externalHeadersTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void externalHeadersTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void externalHeadersTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void externalHeadersTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void externalHeadersTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void externalHeadersTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
