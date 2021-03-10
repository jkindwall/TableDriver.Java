package tabledriver.rowquery;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldCondition
{
    private String field;
    public String getField() 
    { 
        return this.field; 
    }

    private String operation;
    public String getOperation()
    { 
        return this.operation;
    }

    private String value;
    public String getValue()
    { 
        return this.value; 
    }

    private Optional<Integer> fieldIndex;
    public Optional<Integer> getFieldIndex()
    { 
        return this.fieldIndex; 
    }

    public boolean isFieldByIndex()
    {
        return this.fieldIndex.isPresent();
    }

    private FieldCondition(String field, String operation, String value, Optional<Integer> fieldIndex)
    {
        this.field = field;
        this.operation = operation;
        this.value = value;
        this.fieldIndex = fieldIndex;
    }

    public static FieldCondition parse(String conditionString)
    {
        String[] parts = ParseHelper.splitByTokens(conditionString, FieldOperation.getAllOperations());

        if (parts.length != 3)
        {
            throw new IllegalArgumentException(
                "Each condition must be in the format <field name><operator><value> with the following characters escaped: " +
                "\\ = ! < > ^ * & |\n" +
                "Supported operators: = != < <= > >= ^= *=");
        }

        String escapePattern = "\\\\(.)";
        String value = parts[2].replaceAll(escapePattern, "$1");

        Pattern fieldByIndexPattern = Pattern.compile("^\\\\(\\d+)$");
        Matcher fieldByIndexMatcher = fieldByIndexPattern.matcher(parts[0]);
        if (fieldByIndexMatcher.matches())
        {
            String field = fieldByIndexMatcher.group(1);
            Integer fieldIndex = Integer.parseInt(field);
            return new FieldCondition(field, parts[1], value, Optional.of(fieldIndex));
        }
        else
        {
            String field = parts[0].replaceAll(escapePattern, "$1");
            return new FieldCondition(field, parts[1], value, Optional.empty());
        }
    }
}

