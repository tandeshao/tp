package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Tests if a {@link seedu.address.model.person.Person Person}
 * matches any of the description given.
 */
public class FindPersonPredicate implements Predicate<Person> {

    /**
     * Descriptor from {@link FindCommandParser}.
     */
    private final ArgumentMultimap descriptor;

    /**
     * Constructs Predicate function.
     *
     * @param descriptor description to search a person by.
     */
    public FindPersonPredicate(ArgumentMultimap descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Conducts a case-insensitive check on the {@link seedu.address.model.person.Person Person}.
     * Checks if the Person's attributes (the attribute that corresponds to {@link Prefix})
     * has any word that matches exactly to any word in the given description. Only three attributes
     * are allowed to have exact word checks, and they are address, memo and tags. The other three attributes
     * (name, phone and email) are only allowed to have partial word checks.
     *
     * @param person person to be tested.
     * @return true if person contains the word, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        List<Prefix> prefixes = descriptor.getAllAvailablePrefix();
        for (Prefix prefix : prefixes) {
            if (testPersonAttribute(person, prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the attribute that corresponds with the attribute matches with the predicate.
     * @param person person to be tested.
     * @param attribute the prefix that identifies which attribute of the person should the predicate be
     *               testing against.
     * @return true if the person's attribute passes the test, false otherwise.
     */
    private boolean testPersonAttribute(Person person, Prefix attribute) {
        Predicate<Person> predicateToTestAgainst;
        if (attribute.equals(PREFIX_CONTACTED_DATE)) {
            predicateToTestAgainst = new ContactedDateMatchPredicate(descriptor);
        } else if (attribute.equals(PREFIX_NAME) || attribute.equals(PREFIX_PHONE) || attribute.equals(PREFIX_EMAIL)) {
            predicateToTestAgainst = new PartialWordMatchPredicate(attribute,
                    descriptor.getAllValues(attribute));
        } else {
            predicateToTestAgainst = new ExactWordMatchPredicate(attribute,
                    descriptor.getAllValues(attribute));
        }
        return predicateToTestAgainst.test(person);
    }

    /**
     * Equal method to check if two {@link FindPersonPredicate} are equal.
     *
     * @param other other PersonContainsKeywordsPredicate object.
     * @return true if both {@link FindPersonPredicate} object are the same, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonPredicate // instanceof handles nulls
                && descriptor.equals(((FindPersonPredicate) other).descriptor)); // state check
    }
}
