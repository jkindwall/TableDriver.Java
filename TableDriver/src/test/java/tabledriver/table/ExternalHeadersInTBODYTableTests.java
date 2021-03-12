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
    public void externalHeadersInTBODYTablePropertiesTest()
    {
        this.testTableProperties(ExternalHeadersInTBODYTableTests.TABLE_ID);
    }

    @Test
    public void externalHeadersInTBODYTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void externalHeadersInTBODYTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void externalHeadersInTBODYTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void externalHeadersInTBODYTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void externalHeadersInTBODYtheadTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void externalHeadersInTBODYTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void externalHeadersInTBODYTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void externalHeadersInTBODYTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void externalHeadersInTBODYTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void externalHeadersInTBODYTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void externalHeadersInTBODYTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void externalHeadersInTBODYTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void externalHeadersInTBODYTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
