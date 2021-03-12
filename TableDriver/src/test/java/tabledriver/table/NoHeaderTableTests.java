package tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;

public class NoHeaderTableTests extends NoHeaderTableTestsBase
{
    private static final String TABLE_ID = "no-headers";

    @Override
    protected Table getTestTable()
    {
        return Table.createWithNoHeaders(this.getDriver().findElement(By.id(NoHeaderTableTests.TABLE_ID)), 0);
    }

    @Test
    public void NoHeaderTablePropertiesTest()
    {
        this.testTableProperties(NoHeaderTableTests.TABLE_ID);
    }

    @Test
    public void NoHeaderTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void NoHeaderTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void NoHeaderTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void NoHeaderTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void NoHeaderTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void NoHeaderTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void NoHeaderTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void NoHeaderTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void NoHeaderTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void NoHeaderTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void NoHeaderTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void NoHeaderTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void NoHeaderTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
