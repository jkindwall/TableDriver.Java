package io.github.jkindwall.tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;

public class THTagsTableTests extends TableTestsBase
{
    private static final String TABLE_ID = "with-th-tags";

    @Override
    protected Table getTestTable()
    {
        return Table.create(this.getDriver().findElement(By.id(THTagsTableTests.TABLE_ID)));
    }

    @Test
    public void thTagsTablePropertiesTest()
    {
        this.testTableProperties(THTagsTableTests.TABLE_ID);
    }

    @Test
    public void thTagsTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void thTagsTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void thTagsTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void thTagsTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void thTagsTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void thTagsTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void thTagsTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void thTagsTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void thTagsTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void thTagsTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void thTagsTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void thTagsTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void thTagsTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
