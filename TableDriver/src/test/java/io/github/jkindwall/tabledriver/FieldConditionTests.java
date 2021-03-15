package io.github.jkindwall.tabledriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FieldConditionTests
{
    @Test
    public void fieldConditionParse_Basic()
    {
        String conditionString = "foo=bar";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("foo", fieldCondition.getField());
        assertEquals("=", fieldCondition.getOperation());
        assertEquals("bar", fieldCondition.getValue());
        assertFalse(fieldCondition.getFieldIndex().isPresent());
        assertFalse(fieldCondition.isFieldByIndex());
    }

    @Test
    public void fieldConditionParse_FieldNotByIndex()
    {
        String conditionString = "365=bar";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("365", fieldCondition.getField());
        assertEquals("=", fieldCondition.getOperation());
        assertEquals("bar", fieldCondition.getValue());
        assertFalse(fieldCondition.getFieldIndex().isPresent());
        assertFalse(fieldCondition.isFieldByIndex());
    }

    @Test
    public void fieldConditionParse_FieldByIndex()
    {
        String conditionString = "\\365=bar";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("365", fieldCondition.getField());
        assertEquals("=", fieldCondition.getOperation());
        assertEquals("bar", fieldCondition.getValue());
        assertTrue(fieldCondition.getFieldIndex().isPresent());
        assertEquals(365, fieldCondition.getFieldIndex().get().intValue());
        assertTrue(fieldCondition.isFieldByIndex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fieldCondition_NoOperator()
    {
        String conditionString = "foobar";
        FieldCondition.parse(conditionString);
    }

    @Test
    public void fieldCondition_EscapedChars()
    {
        String conditionString = "\\foo\\=bar\\\\\\&\\|\\\\=b\\ar\\=foo\\\\\\|\\&";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("foo=bar\\&|\\", fieldCondition.getField());
        assertEquals("=", fieldCondition.getOperation());
        assertEquals("bar=foo\\|&", fieldCondition.getValue());
        assertFalse(fieldCondition.getFieldIndex().isPresent());
        assertFalse(fieldCondition.isFieldByIndex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fieldCondition_EscapedCharsNoOperator()
    {
        String conditionString = "\\foo\\=bar\\\\\\&\\|\\\\\\=b\\ar\\=foo\\\\\\|\\&";
        FieldCondition.parse(conditionString);
    }

    @Test
    public void fieldCondition_NotEqual()
    {
        String conditionString = "\\!foo!=ba\\=r";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("!foo", fieldCondition.getField());
        assertEquals("!=", fieldCondition.getOperation());
        assertEquals("ba=r", fieldCondition.getValue());
        assertFalse(fieldCondition.getFieldIndex().isPresent());
        assertFalse(fieldCondition.isFieldByIndex());
    }

    @Test
    public void fieldCondition_LessThan()
    {
        String conditionString = "\\5<noo\\<dle";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("5", fieldCondition.getField());
        assertEquals("<", fieldCondition.getOperation());
        assertEquals("noo<dle", fieldCondition.getValue());
        assertTrue(fieldCondition.getFieldIndex().isPresent());
        assertEquals(5, fieldCondition.getFieldIndex().get().intValue());
        assertTrue(fieldCondition.isFieldByIndex());
    }

    @Test
    public void fieldCondition_LessThanOrEqual()
    {
        String conditionString = "f\\<o\\=o<=22";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("f<o=o", fieldCondition.getField());
        assertEquals("<=", fieldCondition.getOperation());
        assertEquals("22", fieldCondition.getValue());
        assertFalse(fieldCondition.getFieldIndex().isPresent());
        assertFalse(fieldCondition.isFieldByIndex());
    }

    @Test
    public void fieldCondition_GreaterThan()
    {
        String conditionString = "\\18\\>>19";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("18>", fieldCondition.getField());
        assertEquals(">", fieldCondition.getOperation());
        assertEquals("19", fieldCondition.getValue());
        assertFalse(fieldCondition.getFieldIndex().isPresent());
        assertFalse(fieldCondition.isFieldByIndex());
    }

    @Test
    public void fieldCondition_GreaterThanOrEqual()
    {
        String conditionString = "\\>\\=foo>=99";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals(">=foo", fieldCondition.getField());
        assertEquals(">=", fieldCondition.getOperation());
        assertEquals("99", fieldCondition.getValue());
        assertFalse(fieldCondition.getFieldIndex().isPresent());
        assertFalse(fieldCondition.isFieldByIndex());
    }

    @Test
    public void fieldCondition_StartsWith()
    {
        String conditionString = "\\24^=bar\\^\\=";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("24", fieldCondition.getField());
        assertEquals("^=", fieldCondition.getOperation());
        assertEquals("bar^=", fieldCondition.getValue());
        assertTrue(fieldCondition.getFieldIndex().isPresent());
        assertEquals(24, fieldCondition.getFieldIndex().get().intValue());
        assertTrue(fieldCondition.isFieldByIndex());
    }

    @Test
    public void fieldCondition_Contains()
    {
        String conditionString = "f\\*o\\=o*=b\\=a\\*r";
        FieldCondition fieldCondition = FieldCondition.parse(conditionString);

        assertEquals("f*o=o", fieldCondition.getField());
        assertEquals("*=", fieldCondition.getOperation());
        assertEquals("b=a*r", fieldCondition.getValue());
        assertFalse(fieldCondition.getFieldIndex().isPresent());
        assertFalse(fieldCondition.isFieldByIndex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void fieldCondition_IncompleteNotEqualOperator()
    {
        String conditionString = "foo!bar";
        FieldCondition.parse(conditionString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fieldCondition_IncompleteStartsWithOperator()
    {
        String conditionString = "foo^bar";
        FieldCondition.parse(conditionString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fieldCondition_IncompleteContainsOperator()
    {
        String conditionString = "foo*bar";
        FieldCondition.parse(conditionString);
    }
}
