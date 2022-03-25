package seedu.address.model.person.predicate;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.parser.PersonDescriptor;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;


/**
 * Tests if a {@link seedu.address.model.person.Person Person}
 * matches any of the description given for scrubbing.
 */
public class ScrubPersonPredicate implements Predicate<Person> {
    private final PersonDescriptor descriptor;

    /**
     * Constructor for ScrubPersonPredicate.
     * @param descriptor Description to scrub a person by.
     */
    public ScrubPersonPredicate(PersonDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Conducts a case-insensitive check on the {@link seedu.address.model.person.Person Person}.
     * Checks if the Person's attributes (the attribute that corresponds to {@link Prefix})
     * has any word that matches exactly to any word in the given description. Only three attributes
     * are allowed for scrubbing, and they are phone, email and tags. For email, a partial match between
     * the email description and person's email is used and for phone and tags, an exact match criteria is used.
     *
     * @param person person to be tested.
     * @return true if person contains the word, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        List<Prefix> listOfPrefixes = descriptor.getAllAvailablePrefix();
        return listOfPrefixes.stream().anyMatch(prefix -> createPredicate(prefix).test(person));
    }

    /**
     * Creates predicate that allows partial matching for email attributes and exact matching for phone and tag
     * attributes.
     * @param prefix Prefix representing the attribute that is target for testing.
     * @return Appropriate predicate that meets the matching requirement of different attributes (see
     * {@link #test(Person)} for more information).
     */
    private Predicate<Person> createPredicate(Prefix prefix) {
        Predicate<Person> predicateToTestPerson;
        if (prefix.equals(PREFIX_EMAIL)) {
            predicateToTestPerson = new DomainMatchPredicate(descriptor.getDescription(prefix));
        } else {
             predicateToTestPerson = new ExactWordMatchPredicate(prefix, descriptor.getDescription(prefix));
        }
        return predicateToTestPerson;
    }
}
