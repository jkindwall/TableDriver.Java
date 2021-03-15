package io.github.jkindwall.tabledriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrGroupTests
{
    @Test
    public void orGroup_SingleAndGroup()
    {
        String orGroupString = "foo=bar";
        OrGroup orGroup = OrGroup.parse(orGroupString);

        assertNotNull(orGroup.getAndGroups());
        assertEquals(1, orGroup.getAndGroups().size());

        assertEquals(1, orGroup.getAndGroups().get(0).getConditions().size());

        assertEquals("foo", orGroup.getAndGroups().get(0).getConditions().get(0).getField());
        assertEquals("bar", orGroup.getAndGroups().get(0).getConditions().get(0).getValue());
        assertFalse(orGroup.getAndGroups().get(0).getConditions().get(0).getFieldIndex().isPresent());
        assertFalse(orGroup.getAndGroups().get(0).getConditions().get(0).isFieldByIndex());
    }

    @Test
    public void orGroup_MultipleAndGroups()
    {
        String orGroupString = "foo=bar|123=456&\\789=987|\\\\\\=\\&\\|=\\|\\&\\=\\\\";
        OrGroup orGroup = OrGroup.parse(orGroupString);

        assertNotNull(orGroup.getAndGroups());
        assertEquals(3, orGroup.getAndGroups().size());

        assertEquals(1, orGroup.getAndGroups().get(0).getConditions().size());

        assertEquals("foo", orGroup.getAndGroups().get(0).getConditions().get(0).getField());
        assertEquals("bar", orGroup.getAndGroups().get(0).getConditions().get(0).getValue());
        assertFalse(orGroup.getAndGroups().get(0).getConditions().get(0).getFieldIndex().isPresent());
        assertFalse(orGroup.getAndGroups().get(0).getConditions().get(0).isFieldByIndex());

        assertEquals(2, orGroup.getAndGroups().get(1).getConditions().size());

        assertEquals("123", orGroup.getAndGroups().get(1).getConditions().get(0).getField());
        assertEquals("456", orGroup.getAndGroups().get(1).getConditions().get(0).getValue());
        assertFalse(orGroup.getAndGroups().get(1).getConditions().get(0).getFieldIndex().isPresent());
        assertFalse(orGroup.getAndGroups().get(1).getConditions().get(0).isFieldByIndex());

        assertEquals("789", orGroup.getAndGroups().get(1).getConditions().get(1).getField());
        assertEquals("987", orGroup.getAndGroups().get(1).getConditions().get(1).getValue());
        assertTrue(orGroup.getAndGroups().get(1).getConditions().get(1).getFieldIndex().isPresent());
        assertEquals(789, orGroup.getAndGroups().get(1).getConditions().get(1).getFieldIndex().get().intValue());
        assertTrue(orGroup.getAndGroups().get(1).getConditions().get(1).isFieldByIndex());

        assertEquals(1, orGroup.getAndGroups().get(2).getConditions().size());

        assertEquals("\\=&|", orGroup.getAndGroups().get(2).getConditions().get(0).getField());
        assertEquals("|&=\\", orGroup.getAndGroups().get(2).getConditions().get(0).getValue());
        assertFalse(orGroup.getAndGroups().get(2).getConditions().get(0).getFieldIndex().isPresent());
        assertFalse(orGroup.getAndGroups().get(2).getConditions().get(0).isFieldByIndex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void orGroup_MissingConditionStart()
    {
        String orGroupString = "|foo=bar";
        OrGroup.parse(orGroupString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void orGroup_MissingConditionEnd()
    {
        String orGroupString = "foo=bar|";
        OrGroup.parse(orGroupString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void orGroup_MissingConditionMiddle()
    {
        String orGroupString = "foo=bar||bar=foo";
        OrGroup.parse(orGroupString);
    }
}
