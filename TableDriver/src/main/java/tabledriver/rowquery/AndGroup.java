package tabledriver.rowquery;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AndGroup
{
    private final List<FieldCondition> conditions;
    public List<FieldCondition> getConditions()
    { 
        return this.conditions;
    }

    private AndGroup(List<FieldCondition> conditions)
    {
        this.conditions = Collections.unmodifiableList(conditions);
    }

    public static AndGroup parse(String andGroupString)
    {
        String[] conditionStrings = ParseHelper.splitByToken(andGroupString, '&');

        List<FieldCondition> conditions = Stream.of(conditionStrings)
            .map(cs -> FieldCondition.parse(cs))
            .collect(Collectors.toList());

        return new AndGroup(conditions);
    }
}

