package tabledriver.rowquery;

public class FieldOperation
{
    private static final String[] allOperations = new String[]
    {
        FieldOperation.EQUAL,
        FieldOperation.NOT_EQUAL,
        FieldOperation.LESS_THAN,
        FieldOperation.LESS_THAN_OR_EQUAL,
        FieldOperation.GREATER_THAN,
        FieldOperation.GREATER_THAN_OR_EQUAL,
        FieldOperation.STARTS_WITH,
        FieldOperation.CONTAINS
    };

    public static String[] getAllOperations()
    {
        return FieldOperation.allOperations;
    }

    public static final String EQUAL = "=";
    public static final String NOT_EQUAL = "!=";
    public static final String LESS_THAN = "<";
    public static final String LESS_THAN_OR_EQUAL = "<=";
    public static final String GREATER_THAN = ">";
    public static final String GREATER_THAN_OR_EQUAL = ">=";
    public static final String STARTS_WITH = "^=";
    public static final String CONTAINS = "*=";

    private FieldOperation() { }
}
