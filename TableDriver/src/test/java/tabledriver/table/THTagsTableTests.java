package tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;

public class THTagsTableTests extends TableTestsBase
{
    private static final String TABLE_ID = "with-th-tags";

    @Override
    protected Table getTestTable()
    {
        return Table.create(this.getDriver().findElement(By.id(THTagsTableTests.TABLE_ID)));
    }

    @Test
    public void THTagsTablePropertiesTest()
    {
        this.testTableProperties(THTagsTableTests.TABLE_ID);
    }

    @Test
    public void THTagsTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void THTagsTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void THTagsTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void THTagsTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void THTagsTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void THTagsTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void THTagsTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void THTagsTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void THTagsTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void THTagsTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void THTagsTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void THTagsTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void THTagsTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
