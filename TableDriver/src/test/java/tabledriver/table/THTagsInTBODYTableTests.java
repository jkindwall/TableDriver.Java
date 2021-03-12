package tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;

public class THTagsInTBODYTableTests extends TableTestsBase
{
    private static final String TABLE_ID = "with-th-tags-in-tbody";

    @Override
    protected Table getTestTable()
    {
        return Table.create(this.getDriver().findElement(By.id(THTagsInTBODYTableTests.TABLE_ID)));
    }

    @Test
    public void THTagsInTBODYTablePropertiesTest()
    {
        this.testTableProperties(THTagsInTBODYTableTests.TABLE_ID);
    }

    @Test
    public void THTagsInTBODYTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void THTagsInTBODYTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void THTagsInTBODYTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void THTagsInTBODYTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void THTagsInTBODYTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void THTagsInTBODYTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void THTagsInTBODYTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void THTagsInTBODYTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void THTagsInTBODYTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void THTagsInTBODYTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void THTagsInTBODYTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void THTagsInTBODYTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void THTagsInTBODYTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
