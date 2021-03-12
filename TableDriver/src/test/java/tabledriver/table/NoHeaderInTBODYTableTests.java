package tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;

public class NoHeaderInTBODYTableTests extends NoHeaderTableTestsBase
{
    private static final String TABLE_ID = "no-headers-in-tbody";

    @Override
    protected Table getTestTable()
    {
        return Table.createWithNoHeaders(this.getDriver().findElement(By.id(NoHeaderInTBODYTableTests.TABLE_ID)), 0);
    }

    @Test
    public void NoHeaderInTBODYTablePropertiesTest()
    {
        this.testTableProperties(NoHeaderInTBODYTableTests.TABLE_ID);
    }

    @Test
    public void NoHeaderInTBODYTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void NoHeaderInTBODYTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void NoHeaderInTBODYTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void NoHeaderInTBODYTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void NoHeaderInTBODYTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void NoHeaderInTBODYTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void NoHeaderInTBODYTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void NoHeaderInTBODYTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void NoHeaderInTBODYTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void NoHeaderInTBODYTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void NoHeaderInTBODYTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void NoHeaderInTBODYTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void NoHeaderInTBODYTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
