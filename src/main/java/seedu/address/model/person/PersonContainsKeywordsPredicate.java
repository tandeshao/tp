package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@link seedu.address.model.person.Person Person}
 * {@link seedu.address.model.person.PersonAttribute PersonAttribute} matches any of
 * the keywords given.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {

    /**
     * Tokenized input from {@link seedu.address.logic.commands.FindCommand FindCommand}.
     */
    private final ArgumentMultimap tokenizedInput;

    /**
     * Constructor of Predicate function.
     *
     * @param tokenizedInput Takes in the tokenized input.
     */
    public PersonContainsKeywordsPredicate(ArgumentMultimap tokenizedInput) {
        // To trim off Preamble dummy in ArgumentMultiMap since user input for
        // the SpecialFindCommand do not need a preamble index unlike
        // the edit command.
        tokenizedInput.trimEmptyKeys();
        this.tokenizedInput = tokenizedInput;
    }

    /**
     * Checks if the {@link seedu.address.model.person.Person Person} contains any
     * of the {@link #tokenizedInput token}.
     *
     * @param person {@link Person} to be checked on.
     * @return true if person contains any keywords, false otherwise.
     */
    private boolean personContainsKeyWords(Person person) {
        List<Prefix> prefixKeys = tokenizedInput.getAllKeys();
        for (Prefix prefix : prefixKeys) {
            Set<PersonAttribute> personAttributes = person.getCorrespondingAttribute(prefix);
            if (checksAnyTokenMatches(prefix, personAttributes)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper function for {@link #personContainsKeyWords(Person)}.
     * When a token matches with any of the
     * {@link seedu.address.model.person.PersonAttribute Attribute},
     * function returns true.
     *
     * @param prefix           indicate which attribute of the person should the function check for.
     * @param personAttributes corresponding person attribute to be checked with.
     * @return true if any token matches any of the attributes, false otherwise.
     */
    private boolean checksAnyTokenMatches(Prefix prefix, Set<PersonAttribute> personAttributes) {
        for (PersonAttribute attribute : personAttributes) {
            if (anyMatch(prefix, attribute)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper function for {@link #checksAnyTokenMatches(seedu.address.logic.parser.Prefix, java.util.Set)}.
     * Checks if any of the token matches the person's attribute.
     *
     * @param prefix          used to get all the keywords from {@link #tokenizedInput}.
     * @param personAttribute person attribute to be checked with.
     * @return true if any of the token matches the specified person attribute, false otherwise.
     */
    private boolean anyMatch(Prefix prefix, PersonAttribute personAttribute) {
        return tokenizedInput.getAllValues(prefix).stream().anyMatch(
            keyword -> StringUtil.containsWordIgnoreCaseForTwoSentence(personAttribute.toString(), keyword));
    }

    /**
     * Test method for {@link javafx.collections.transformation.FilteredList}.
     * Conducts a {@link #personContainsKeyWords(Person)} check
     * on the {@link seedu.address.model.person.Person Person} in the list.
     *
     * @param person person to be tested.
     * @return true if person contains keywords, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        return personContainsKeyWords(person);
    }

    /**
     * Equal method to check if two {@link PersonContainsKeywordsPredicate} are equal.
     *
     * @param other other PersonContainsKeywordsPredicate object.
     * @return true if both {@link PersonContainsKeywordsPredicate} object are the same, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsKeywordsPredicate // instanceof handles nulls
                && tokenizedInput.equals(((PersonContainsKeywordsPredicate) other).tokenizedInput)); // state check
    }
}
