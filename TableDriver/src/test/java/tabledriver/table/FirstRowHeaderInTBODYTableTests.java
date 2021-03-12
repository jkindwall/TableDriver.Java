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
    public void FirstRowHeaderInTBODYTablePropertiesTest()
    {
        this.testTableProperties(FirstRowHeaderInTBODYTableTests.TABLE_ID);
    }

    @Test
    public void FirstRowHeaderInTBODYTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void FirstRowHeaderInTBODYTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void FirstRowHeaderInTBODYTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void FirstRowHeaderInTBODYTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void FirstRowHeaderInTBODYTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void FirstRowHeaderInTBODYTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void FirstRowHeaderInTBODYTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void FirstRowHeaderInTBODYTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void FirstRowHeaderInTBODYTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void FirstRowHeaderInTBODYTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void FirstRowHeaderInTBODYTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void FirstRowHeaderInTBODYTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void FirstRowHeaderInTBODYTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
