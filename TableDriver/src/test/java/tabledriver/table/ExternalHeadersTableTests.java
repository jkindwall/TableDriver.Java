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
    public void ExternalHeadersTablePropertiesTest()
    {
        this.testTableProperties(ExternalHeadersTableTests.TABLE_ID);
    }

    @Test
    public void ExternalHeadersTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void ExternalHeadersTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void ExternalHeadersTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void ExternalHeadersTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void ExternalHeaderstheadTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void ExternalHeadersTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void ExternalHeadersTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void ExternalHeadersTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void ExternalHeadersTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void ExternalHeadersTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void ExternalHeadersTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void ExternalHeadersTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void ExternalHeadersTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
