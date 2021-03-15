package io.github.jkindwall.tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;

public class NoHeaderTableTests extends NoHeaderTableTestsBase
{
    private static final String TABLE_ID = "no-headers";

    @Override
    protected Table getTestTable()
    {
        return Table.createWithNoHeaders(this.getDriver().findElement(By.id(NoHeaderTableTests.TABLE_ID)), 0);
    }

    @Test
    public void noHeaderTablePropertiesTest()
    {
        this.testTableProperties(NoHeaderTableTests.TABLE_ID);
    }

    @Test
    public void noHeaderTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void noHeaderTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void noHeaderTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void noHeaderTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void noHeaderTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void noHeaderTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void noHeaderTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void noHeaderTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void noHeaderTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void noHeaderTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void noHeaderTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void noHeaderTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void noHeaderTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
