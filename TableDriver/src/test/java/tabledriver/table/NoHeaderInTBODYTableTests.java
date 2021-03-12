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
    public void noHeaderInTBODYTablePropertiesTest()
    {
        this.testTableProperties(NoHeaderInTBODYTableTests.TABLE_ID);
    }

    @Test
    public void noHeaderInTBODYTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void noHeaderInTBODYTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void noHeaderInTBODYTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void noHeaderInTBODYTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void noHeaderInTBODYTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void noHeaderInTBODYTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void noHeaderInTBODYTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void noHeaderInTBODYTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void noHeaderInTBODYTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void noHeaderInTBODYTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void noHeaderInTBODYTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void noHeaderInTBODYTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void noHeaderInTBODYTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
