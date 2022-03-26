package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class ContactStatusMatchPredicateTest {

    @Test
    void test_validCases_returnsTrue() {
        Person person = new PersonBuilder().withContactedDate("").build();
        String arg = " c/";
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(arg, ARRAY_OF_PREFIX);
        ContactStatusMatchPredicate predicate = new ContactStatusMatchPredicate(descriptor);
        assertTrue(predicate.test(person));


        Person personWithTodayDate = new PersonBuilder().withContactedDate("27-03-2022").build();
        String validArg = " c/0";
        ArgumentMultimap personWithTodayDateMap = ArgumentTokenizer.tokenize(validArg, ARRAY_OF_PREFIX);
        ContactStatusMatchPredicate personWithTodayDatePredicate =
                new ContactStatusMatchPredicate(personWithTodayDateMap);
        assertTrue(personWithTodayDatePredicate.test(personWithTodayDate));
    }
}
