package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * A class that encapsulates the logic of partial word match between a Person's attribute and the
 * description for that attribute (supplied by the user).
 */
public class PartialWordMatchPredicate implements Predicate<Person> {
    private final String description;
    private final Prefix prefix;

    /**
     * Constructor for PartialWordMatchPredicate
     * @param prefix use to retrieve the corresponding {@link Person} attribute.
     * @param description description that is tested against.
     */
    public PartialWordMatchPredicate(Prefix prefix, String description) {
        this.description = description;
        this.prefix = prefix;
    }

    /**
     * Conducts a case-insensitive partial match on both the attribute and description.
     * For example: "This is a memo" would match with "this".
     * @param attribute person attribute that is tested.
     * @param description description that is supplied by the user.
     * @return true if there is a partial match between attribute and description, false otherwise.
     */
    private boolean caseInsensitivePartialMatch(String attribute, String description) {
        return Arrays.stream(description.toLowerCase().split(" ")).anyMatch(attribute.toLowerCase()::contains);
    }

    /**
     * Conducts a case-insensitive check on the {@link seedu.address.model.person.Person Person}.
     * Checks if the Person's attribute (the attribute that corresponds to {@link #prefix})
     * has any word that partially matches the given description.
     *
     * @param person person to be tested.
     * @return true if person contains the word, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        if (PREFIX_EMAIL.equals(prefix)) {
            return caseInsensitivePartialMatch(person.getEmail().toString(), description);
        } else if (PREFIX_NAME.equals(prefix)) {
            return caseInsensitivePartialMatch(person.getName().toString(), description);
        } else if (PREFIX_PHONE.equals(prefix)) {
            return caseInsensitivePartialMatch(person.getPhone().toString(), description);
        } else if (PREFIX_MEMO.equals(prefix)) {
            return caseInsensitivePartialMatch(person.getMemo().toString(), description);
        } else if (PREFIX_TAG.equals(prefix)) {
            return person.getTags().stream().anyMatch(tag -> caseInsensitivePartialMatch(tag.toString(), description));
        } else {
            return caseInsensitivePartialMatch(person.getAddress().toString(), description);
        }
    }
}
