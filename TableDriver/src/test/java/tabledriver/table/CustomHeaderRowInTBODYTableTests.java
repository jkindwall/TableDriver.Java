package tabledriver.table;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;

public class CustomHeaderRowInTBODYTableTests extends TableTestsBase
{
    private static final String TABLE_ID = "custom-header-row-in-tbody";
    private static final String HEADER_CSS = "#custom-header-row-in-tbody tr[name=headerRow]";

    @Override
    protected Table getTestTable()
    {
        return Table.createWithHeaderRow(
            this.getDriver().findElement(By.id(CustomHeaderRowInTBODYTableTests.TABLE_ID)),
            this.getDriver().findElement(By.cssSelector(CustomHeaderRowInTBODYTableTests.HEADER_CSS)),
            2);
    }

    @Test
    public void customHeaderRowInTBODYTablePropertiesTest()
    {
        this.testTableProperties(CustomHeaderRowInTBODYTableTests.TABLE_ID);
    }

    @Test
    public void customHeaderRowInTBODYTableGetRowsTest()
    {
        this.testGetRows();
    }

    @Test
    public void customHeaderRowInTBODYTableFindRowTest()
    {
        this.testFindRow();
    }

    @Test
    public void customHeaderRowInTBODYTableFindRowsTest()
    {
        this.testFindRows();
    }

    @Test
    public void customHeaderRowInTBODYTableFindCellTest()
    {
        this.testFindCell();
    }

    @Test
    public void customHeaderRowInTBODYtheadTableFindCellsTest()
    {
        this.testFindCells();
    }

    @Test
    public void customHeaderRowInTBODYTableGetHeaderTest()
    {
        this.testGetHeader();
    }

    @Test
    public void customHeaderRowInTBODYTableNotEqualInRowQueryTest()
    {
        this.testNotEqualInRowQuery();
    }

    @Test
    public void customHeaderRowInTBODYTableLessThanInRowQueryTest()
    {
        this.testLessThanInRowQuery();
    }

    @Test
    public void customHeaderRowInTBODYTableLessThanOrEqualInRowQueryTest()
    {
        this.testLessThanOrEqualInRowQuery();
    }

    @Test
    public void customHeaderRowInTBODYTableGreaterThanInRowQueryTest()
    {
        this.testGreaterThanInRowQuery();
    }

    @Test
    public void customHeaderRowInTBODYTableGreaterThanOrEqualInRowQueryTest()
    {
        this.testGreaterThanOrEqualInRowQuery();
    }

    @Test
    public void customHeaderRowInTBODYTableStartsWithInRowQueryTest()
    {
        this.testStartsWithInRowQuery();
    }

    @Test
    public void customHeaderRowInTBODYTableContainsInRowQueryTest()
    {
        this.testContainsInRowQuery();
    }
}
