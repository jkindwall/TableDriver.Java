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
    public void firstRowHeaderTablePropertiesTest()
    {
        this.testTableProperties(FirstRowHeaderTableTests.TABLE_ID);
    }

    @Test
    public void firstRowHeaderTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void firstRowHeaderTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void firstRowHeaderTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void firstRowHeaderTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void firstRowHeaderTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void firstRowHeaderTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void firstRowHeaderTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void firstRowHeaderTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void firstRowHeaderTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void firstRowHeaderTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void firstRowHeaderTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void firstRowHeaderTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void firstRowHeaderTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
