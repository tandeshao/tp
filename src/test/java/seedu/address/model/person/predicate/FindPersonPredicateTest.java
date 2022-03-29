package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.testutil.PersonBuilder;

class FindPersonPredicateTest {

    @Test
    void test_validPerson_returnsTrue() {
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(" n/alex t/colleague p/9040", ARRAY_OF_PREFIX);
        FindPersonPredicate predicate = new FindPersonPredicate(descriptor);
        assertTrue(predicate.test(new PersonBuilder().withName("a").withPhone("90400204").build()));
        assertTrue(predicate.test(new PersonBuilder().withName("Alex Yeoh").withPhone("123").build()));
    }

    @Test
    void test_invalidCases_returnsFalse() {
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(" a/st t/colleague p/9040", ARRAY_OF_PREFIX);
        FindPersonPredicate predicate = new FindPersonPredicate(descriptor);
        assertFalse(predicate.test(new PersonBuilder().withAddress("street").withPhone("904").build()));
        assertFalse(predicate.test(new PersonBuilder().withName("a").withPhone("123").withTags("colle").build()));
    }

    @Test
    void testEquals() {
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(" n/ test", ARRAY_OF_PREFIX);
        ArgumentMultimap duplicatedDescriptor = ArgumentTokenizer.tokenize(" n/ test", ARRAY_OF_PREFIX);
        FindPersonPredicate predicate = new FindPersonPredicate(descriptor);
        assertEquals(predicate, new FindPersonPredicate(duplicatedDescriptor));
    }
}
