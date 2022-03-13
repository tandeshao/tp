package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class PersonContainsKeywordPredicate implements Predicate<Person> {
    private final ArgumentMultimap tokenizedInput;

    public PersonContainsKeywordPredicate(ArgumentMultimap tokenizedInput) {
        // To trim off Preamble dummy in ArgumentMultiMap since user input for
        // the SpecialFindCommand do not need a preamble index unlike
        // the edit command.
        tokenizedInput.trim();
        this.tokenizedInput = tokenizedInput;
    }

    private boolean personContainsKeyWord(Person person) {
        List<Prefix> prefixKeys = tokenizedInput.getAllKeys();
        for (Prefix prefix : prefixKeys) {
            Set<PersonAttribute> personAttributes = person.getCorrespondingAttribute(prefix);
            if (checksAnyTokenMatches(prefix, personAttributes)) {
                return true;
            }
        }
        return false;
    }

    private boolean checksAnyTokenMatches(Prefix prefix, Set<PersonAttribute> personAttributes) {
        for (PersonAttribute attribute : personAttributes) {
            if (anyMatch(prefix, attribute)) {
                return true;
            }
        }
        return false;
    }

    private boolean anyMatch(Prefix prefix, PersonAttribute personAttribute) {
        return tokenizedInput.getAllValues(prefix).stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseForTwoSentence(personAttribute.toString(), keyword));
    }

    @Override
    public boolean test(Person person) {
        return personContainsKeyWord(person);
    }
}
