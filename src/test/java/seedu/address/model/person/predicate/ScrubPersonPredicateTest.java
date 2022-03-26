package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.PersonDescriptor;
import seedu.address.testutil.PersonBuilder;

class ScrubPersonPredicateTest {

    @Test
    void test() {
        PersonDescriptor singleArgDescriptor = new PersonDescriptor("e/@mail p/90400204 t/tag1");
        PersonDescriptor multipleArgDescriptor = new PersonDescriptor("e/@mail @gmail p/90400204 90400203 t/tag1 tag2");
        ScrubPersonPredicate singleArgPredicate = new ScrubPersonPredicate(singleArgDescriptor);
        assertTrue(singleArgPredicate.test(new PersonBuilder().withEmail("tester@mail.com").build()));
        assertTrue(singleArgPredicate.test(new PersonBuilder().withPhone("90400204").build()));
        assertTrue(singleArgPredicate.test(new PersonBuilder().withTags("tag1").build()));
        assertFalse(singleArgPredicate.test(new PersonBuilder().withTags("tag22").build()));

        ScrubPersonPredicate multipleArgPredicate = new ScrubPersonPredicate(multipleArgDescriptor);
        assertTrue(multipleArgPredicate.test(new PersonBuilder().withEmail("tester@gmail.com").build()));
        assertTrue(multipleArgPredicate.test(new PersonBuilder().withPhone("90400203").build()));
        assertTrue(multipleArgPredicate.test(new PersonBuilder().withTags("tag2").build()));

        PersonDescriptor multipleTagDescriptor = new PersonDescriptor("t/tag1 tag2");
        ScrubPersonPredicate multipleTagPredicate = new ScrubPersonPredicate(multipleTagDescriptor);
        assertFalse(multipleTagPredicate.test(new PersonBuilder().withTags("tag22").build()));
    }
}
