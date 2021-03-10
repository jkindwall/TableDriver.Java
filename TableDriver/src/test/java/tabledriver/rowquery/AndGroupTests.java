package tabledriver.rowquery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AndGroupTests
{
    @Test
    public void andGroup_SingleCondition()
    {
        String andGroupString = "foo=bar";
        AndGroup andGroup = AndGroup.parse(andGroupString);

        assertNotNull(andGroup.getConditions());
        assertEquals(1, andGroup.getConditions().size());

        assertEquals("foo", andGroup.getConditions().get(0).getField());
        assertEquals("bar", andGroup.getConditions().get(0).getValue());
        assertFalse(andGroup.getConditions().get(0).getFieldIndex().isPresent());
        assertFalse(andGroup.getConditions().get(0).isFieldByIndex());
    }

    @Test
    public void andGroup_MultipleConditions()
    {
        String andGroupString = "foo=bar&123=456&\\789=987&\\\\\\=\\&\\|=\\|\\&\\=\\\\";
        AndGroup andGroup = AndGroup.parse(andGroupString);

        assertNotNull(andGroup.getConditions());
        assertEquals(4, andGroup.getConditions().size());

        assertEquals("foo", andGroup.getConditions().get(0).getField());
        assertEquals("bar", andGroup.getConditions().get(0).getValue());
        assertFalse(andGroup.getConditions().get(0).getFieldIndex().isPresent());
        assertFalse(andGroup.getConditions().get(0).isFieldByIndex());

        assertEquals("123", andGroup.getConditions().get(1).getField());
        assertEquals("456", andGroup.getConditions().get(1).getValue());
        assertFalse(andGroup.getConditions().get(1).getFieldIndex().isPresent());
        assertFalse(andGroup.getConditions().get(1).isFieldByIndex());

        assertEquals("789", andGroup.getConditions().get(2).getField());
        assertEquals("987", andGroup.getConditions().get(2).getValue());
        assertTrue(andGroup.getConditions().get(2).getFieldIndex().isPresent());
        assertEquals(789, andGroup.getConditions().get(2).getFieldIndex().get().intValue());
        assertTrue(andGroup.getConditions().get(2).isFieldByIndex());

        assertEquals("\\=&|", andGroup.getConditions().get(3).getField());
        assertEquals("|&=\\", andGroup.getConditions().get(3).getValue());
        assertFalse(andGroup.getConditions().get(3).getFieldIndex().isPresent());
        assertFalse(andGroup.getConditions().get(3).isFieldByIndex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void andGroup_MissingConditionStart()
    {
        String andGroupString = "&foo=bar";
        AndGroup.parse(andGroupString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void andGroup_MissingConditionEnd()
    {
        String andGroupString = "foo=bar&";
        AndGroup.parse(andGroupString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void andGroup_MissingConditionMiddle()
    {
        String andGroupString = "foo=bar&&bar=foo";
        AndGroup.parse(andGroupString);
    }
}
