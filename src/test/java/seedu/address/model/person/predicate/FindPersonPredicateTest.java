package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.PersonDescriptor;
import seedu.address.testutil.PersonBuilder;

class FindPersonPredicateTest {

    @Test
    void test_validPerson_returnsTrue() {
        PersonDescriptor descriptor = new PersonDescriptor(" n/alex t/colleague p/9040");
        FindPersonPredicate predicate = new FindPersonPredicate(descriptor);
        assertTrue(predicate.test(new PersonBuilder().withName("a").withPhone("90400204").build()));
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Yeoh").withPhone("123").build()));
    }

    @Test
    void test_invalidCases_returnsFalse() {
        PersonDescriptor descriptor = new PersonDescriptor(" a/st t/colleague p/9040");
        FindPersonPredicate predicate = new FindPersonPredicate(descriptor);
        assertFalse(predicate.test(new PersonBuilder().withAddress("street").withPhone("904").build()));
        assertFalse(predicate.test(new PersonBuilder().withName("a").withPhone("123").withTags("colle").build()));
    }

    @Test
    void testEquals() {
        PersonDescriptor descriptor = new PersonDescriptor(" n/ test");
        PersonDescriptor duplicatedDescriptor = new PersonDescriptor(" n/ test");
        FindPersonPredicate predicate = new FindPersonPredicate(descriptor);
        assertEquals(predicate, new FindPersonPredicate(duplicatedDescriptor));
    }
}
