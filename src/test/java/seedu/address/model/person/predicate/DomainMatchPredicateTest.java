package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class DomainMatchPredicateTest {

    @Test
    void test_validArgs() {
        DomainMatchPredicate partialEmailPredicate = new DomainMatchPredicate("@mail");
        DomainMatchPredicate fullEmailPredicate = new DomainMatchPredicate("@mail.com");

        assertTrue(partialEmailPredicate.test(new PersonBuilder().withEmail("tester@mail.com").build()));
        assertTrue(fullEmailPredicate.test(new PersonBuilder().withEmail("tester@mail.com").build()));

        assertFalse(partialEmailPredicate.test(new PersonBuilder().withEmail("tester@gmail.com").build()));
        assertFalse(fullEmailPredicate.test(new PersonBuilder().withEmail("tester@gmail.com").build()));

        DomainMatchPredicate multipleEmailPredicate = new DomainMatchPredicate("@mail @gmail");
        assertTrue(multipleEmailPredicate.test(new PersonBuilder().withEmail("tester@gmail.com").build()));
    }
}
