package tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;

public class FirstRowHeaderInTBODYTableTests extends TableTestsBase
{
    private static final String TABLE_ID = "first-row-headers-in-tbody";

    @Override
    protected Table getTestTable()
    {
        return Table.create(this.getDriver().findElement(By.id(FirstRowHeaderInTBODYTableTests.TABLE_ID)));
    }

    @Test
    public void firstRowHeaderInTBODYTablePropertiesTest()
    {
        this.testTableProperties(FirstRowHeaderInTBODYTableTests.TABLE_ID);
    }

    @Test
    public void firstRowHeaderInTBODYTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void firstRowHeaderInTBODYTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void firstRowHeaderInTBODYTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void firstRowHeaderInTBODYTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void firstRowHeaderInTBODYTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void firstRowHeaderInTBODYTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void firstRowHeaderInTBODYTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void firstRowHeaderInTBODYTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void firstRowHeaderInTBODYTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void firstRowHeaderInTBODYTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void firstRowHeaderInTBODYTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void firstRowHeaderInTBODYTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void firstRowHeaderInTBODYTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
