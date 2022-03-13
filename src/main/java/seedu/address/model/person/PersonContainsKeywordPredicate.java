package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

import java.util.List;
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
        return value = prefixKeys.stream().anyMatch(prefixKey ->
                tokenizedInput.getAllValues(prefixKey).stream()
                 .anyMatch(keyword -> person.getCorrespondingAttribute(prefixKey)
                   .stream().anyMatch(personAttribute ->
                     StringUtil.containsWordIgnoreCaseForTwoSentence(personAttribute.toString(), keyword))));
    }

    @Override
    public boolean test(Person person) {
        return personContainsKeyWord(person);
    }
}
