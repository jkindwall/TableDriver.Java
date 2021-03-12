package tabledriver.table;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import tabledriver.Table;
import tabledriver.TableSamples;

public abstract class ExternalHeadersTableTestsBase extends TableTestsBase
{
    protected void testTableProperties(String tableId)
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        assertEquals(5, table.getColumnCount());
        assertEquals(12, table.getRowCount());
        assertEquals("table", table.getElement().getTagName());
        assertEquals(tableId, table.getElement().getAttribute("id"));
        assertNull(table.getHeaderRow());
        assertEquals(5, table.getHeaderElements().size());
    }
}
