package io.github.jkindwall.tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;

public class CustomHeaderRowTableTests extends TableTestsBase
{
    private static final String TABLE_ID = "custom-header-row";
    private static final String HEADER_CSS = "#custom-header-row tr[name=headerRow]";

    @Override
    protected Table getTestTable()
    {
        return Table.createWithHeaderRow(
            this.getDriver().findElement(By.id(CustomHeaderRowTableTests.TABLE_ID)),
            this.getDriver().findElement(By.cssSelector(CustomHeaderRowTableTests.HEADER_CSS)),
            2);
    }

    @Test
    public void customHeaderRowTablePropertiesTest()
    {
        this.testTableProperties(CustomHeaderRowTableTests.TABLE_ID);
    }

    @Test
    public void customHeaderRowTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void customHeaderRowTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void customHeaderRowTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void customHeaderRowTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void customHeaderRowtheadTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void customHeaderRowTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void customHeaderRowTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void customHeaderRowTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void customHeaderRowTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void customHeaderRowTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void customHeaderRowTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void customHeaderRowTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void customHeaderRowTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
