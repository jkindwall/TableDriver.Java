package io.github.jkindwall.tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;

public class THEADTableTests extends TableTestsBase
{
    private static final String TABLE_ID = "with-thead-tbody";

    @Override
    protected Table getTestTable()
    {
        return Table.create(this.getDriver().findElement(By.id(THEADTableTests.TABLE_ID)));
    }

    @Test
    public void theadTablePropertiesTest()
    {
        this.testTableProperties(THEADTableTests.TABLE_ID);
    }

    @Test
    public void theadTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void theadTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void theadTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void theadTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void theadTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void theadTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void theadTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void theadTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void theadTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void theadTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void theadTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void theadTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void theadTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
