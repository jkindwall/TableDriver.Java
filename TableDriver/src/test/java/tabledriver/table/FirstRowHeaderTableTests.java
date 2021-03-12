package tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;

public class FirstRowHeaderTableTests extends TableTestsBase
{
    private static final String TABLE_ID = "first-row-headers-in-tbody";

    @Override
    protected Table getTestTable()
    {
        return Table.create(this.getDriver().findElement(By.id(FirstRowHeaderTableTests.TABLE_ID)));
    }

    @Test
    public void FirstRowHeaderTablePropertiesTest()
    {
        this.testTableProperties(FirstRowHeaderTableTests.TABLE_ID);
    }

    @Test
    public void FirstRowHeaderTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void FirstRowHeaderTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void FirstRowHeaderTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void FirstRowHeaderTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void FirstRowHeaderTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void FirstRowHeaderTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void FirstRowHeaderTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void FirstRowHeaderTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void FirstRowHeaderTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void FirstRowHeaderTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void FirstRowHeaderTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void FirstRowHeaderTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void FirstRowHeaderTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
