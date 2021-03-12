package tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;

public class ExternalHeadersInTBODYTableTests extends ExternalHeadersTableTestsBase
{
    private static final String TABLE_ID = "with-tbody-and-external-headers";
    private static final String HEADER_CSS = "#table-headers > span";

    @Override
    protected Table getTestTable()
    {
        return Table.createWithExternalHeaders(
            this.getDriver().findElement(By.id(ExternalHeadersInTBODYTableTests.TABLE_ID)),
            this.getDriver().findElements(By.cssSelector(ExternalHeadersInTBODYTableTests.HEADER_CSS)),
            0);
    }

    @Test
    public void ExternalHeadersInTBODYTablePropertiesTest()
    {
        this.testTableProperties(ExternalHeadersInTBODYTableTests.TABLE_ID);
    }

    @Test
    public void ExternalHeadersInTBODYTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void ExternalHeadersInTBODYTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void ExternalHeadersInTBODYTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void ExternalHeadersInTBODYTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void ExternalHeadersInTBODYtheadTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void ExternalHeadersInTBODYTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void ExternalHeadersInTBODYTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void ExternalHeadersInTBODYTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void ExternalHeadersInTBODYTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void ExternalHeadersInTBODYTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void ExternalHeadersInTBODYTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void ExternalHeadersInTBODYTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void ExternalHeadersInTBODYTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
