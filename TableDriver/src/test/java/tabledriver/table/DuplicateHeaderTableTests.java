package tabledriver.table;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.openqa.selenium.By;

import tabledriver.Table;
import tabledriver.TableSamples;
import tabledriver.WebDriverTestsBase;

import static org.junit.Assert.assertEquals;

public class DuplicateHeaderTableTests extends WebDriverTestsBase
{
    private static final String TABLE_ID = "with-duplicate-headers";

    protected Table getTestTable()
    {
        return Table.create(this.getDriver().findElement(By.id(DuplicateHeaderTableTests.TABLE_ID)));
    }

    @Test
    public void duplicateCellTests()
    {
        TableSamples.goToTestPage(this.getDriver());
        Table table = this.getTestTable();

        assertEquals("Red", table.findCell("Color-1=Crimson", "Color").getElement().getText());
        assertEquals("or maybe", table.findCell("Color=Red", "").getElement().getText());
        assertEquals("Crimson", table.findCell("=or maybe", "Color-1").getElement().getText());
        assertEquals("or possibly", table.findCell("Color-2=Vermillion", "-1").getElement().getText());
        assertEquals("Vermillion", table.findCell("-1=or possibly", "Color-2").getElement().getText());

        assertEquals("Blue", table.findCell("Color-1=Azure", "Color").getElement().getText());
        assertEquals("or maybe", table.findCell("Color=Blue", "").getElement().getText());
        assertEquals("Azure", table.findCell(1, "Color-1").getElement().getText());
        assertEquals("or rather", table.findCell("Color-2=Indigo", "-1").getElement().getText());
        assertEquals("Indigo", table.findCell("-1=or rather", "Color-2").getElement().getText());

        assertEquals("Purple", table.findCell("Color-1=Violet", "Color").getElement().getText());
        assertEquals("no wait", table.findCell("Color=Purple", "").getElement().getText());
        assertEquals("Violet", table.findCell("=no wait", "Color-1").getElement().getText());
        assertEquals("or how about", table.findCell("Color-2=Lilac", "-1").getElement().getText());
        assertEquals("Lilac", table.findCell("-1=or how about", "Color-2").getElement().getText());

        List<String> thirdColors = table.findCells("=or maybe", "Color-2")
            .stream()
            .map(c -> c.getElement().getText())
            .collect(Collectors.toList());
        assertEquals("Vermillion", thirdColors.get(0));
        assertEquals("Indigo", thirdColors.get(1));
    }
}
