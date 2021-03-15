package io.github.jkindwall.tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import io.github.jkindwall.tabledriver.Table;

public class THTagsInTBODYTableTests extends TableTestsBase
{
    private static final String TABLE_ID = "with-th-tags-in-tbody";

    @Override
    protected Table getTestTable()
    {
        return Table.create(this.getDriver().findElement(By.id(THTagsInTBODYTableTests.TABLE_ID)));
    }

    @Test
    public void thTagsInTBODYTablePropertiesTest()
    {
        this.testTableProperties(THTagsInTBODYTableTests.TABLE_ID);
    }

    @Test
    public void thTagsInTBODYTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void thTagsInTBODYTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void thTagsInTBODYTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void thTagsInTBODYTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void thTagsInTBODYTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void thTagsInTBODYTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void thTagsInTBODYTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void thTagsInTBODYTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void thTagsInTBODYTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void thTagsInTBODYTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void thTagsInTBODYTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void thTagsInTBODYTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void thTagsInTBODYTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
