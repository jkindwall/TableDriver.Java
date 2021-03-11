package tabledriver;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OrGroup
{
    private final List<AndGroup> andGroups;
    public List<AndGroup> getAndGroups() 
    { 
        return this.andGroups; 
    }

    private OrGroup(List<AndGroup> andGroups)
    {
        this.andGroups = Collections.unmodifiableList(andGroups);
    }

    public static OrGroup parse(String orGroupString)
    {
        String[] andGroupStrings = ParseHelper.splitByToken(orGroupString, '|');

        List<AndGroup> groups = Stream.of(andGroupStrings)
            .map(AndGroup::parse)
            .collect(Collectors.toList());

        return new OrGroup(groups);
    }
}
