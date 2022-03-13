package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {
    private static final String EMPTY_TEST_USER_INPUT = "";
    private static final String POPULATED_TEST_USER_INPUT_WITH_NAME = " n/Alex bob";
    private static final String POPULATED_TEST_USER_INPUT_WITH_PHONE = " p/90100102";
    private static final String POPULATED_TEST_USER_INPUT_WITH_EMAIL = " e/tester@mail.com";
    private static final String POPULATED_TEST_USER_INPUT_WITH_ADDRESS = " a/QueensTown blk 200 singapore 123123";
    private static final String POPULATED_TEST_USER_INPUT_WITH_TAG = " t/friends colleagues";
    private static final String POPULATED_TEST_USER_INPUT_WITH_MEMO = " m/Close contract today";
    private static final String POPULATED_TEST_USER_INPUT_WITH_ALL = " n/Alex" + " p/90100102" + " e/tester@mail.com"
            + " a/QueensTown blk 200 singapore 123123" + " t/friends colleagues" + " m/Close contract today";

    // Create multiMap for predicate constructor.
    private static final ArgumentMultimap EMPTY_MULTI_MAP = ArgumentTokenizer.tokenize(EMPTY_TEST_USER_INPUT,
            PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
    private static final ArgumentMultimap POPULATED_MULTI_MAP_WITH_NAME = ArgumentTokenizer.tokenize(
            POPULATED_TEST_USER_INPUT_WITH_NAME, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
    private static final ArgumentMultimap POPULATED_MULTI_MAP_WITH_PHONE = ArgumentTokenizer.tokenize(
            POPULATED_TEST_USER_INPUT_WITH_PHONE, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
    private static final ArgumentMultimap POPULATED_MULTI_MAP_WITH_EMAIL = ArgumentTokenizer.tokenize(
            POPULATED_TEST_USER_INPUT_WITH_EMAIL, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
    private static final ArgumentMultimap POPULATED_MULTI_MAP_WITH_TAG = ArgumentTokenizer.tokenize(
            POPULATED_TEST_USER_INPUT_WITH_TAG, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
    private static final ArgumentMultimap POPULATED_MULTI_MAP_WITH_MEMO = ArgumentTokenizer.tokenize(
            POPULATED_TEST_USER_INPUT_WITH_MEMO, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
    private static final ArgumentMultimap POPULATED_MULTI_MAP_WITH_ALL = ArgumentTokenizer.tokenize(
            POPULATED_TEST_USER_INPUT_WITH_ALL, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

    // Create Person predicate with the argMultiMap.
    public static final PersonContainsKeywordsPredicate EMPTY_PREDICATE = new PersonContainsKeywordsPredicate(
            EMPTY_MULTI_MAP);
    public static final PersonContainsKeywordsPredicate NAME_PREDICATE = new PersonContainsKeywordsPredicate(
            POPULATED_MULTI_MAP_WITH_NAME);
    public static final PersonContainsKeywordsPredicate PHONE_PREDICATE = new PersonContainsKeywordsPredicate(
            POPULATED_MULTI_MAP_WITH_PHONE);
    public static final PersonContainsKeywordsPredicate EMAIL_PREDICATE = new PersonContainsKeywordsPredicate(
            POPULATED_MULTI_MAP_WITH_EMAIL);
    private static final ArgumentMultimap POPULATED_MULTI_MAP_WITH_ADDRESS = ArgumentTokenizer.tokenize(
            POPULATED_TEST_USER_INPUT_WITH_ADDRESS, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
            PREFIX_TAG);
    public static final PersonContainsKeywordsPredicate ADDRESS_PREDICATE = new PersonContainsKeywordsPredicate(
            POPULATED_MULTI_MAP_WITH_ADDRESS);
    public static final PersonContainsKeywordsPredicate TAG_PREDICATE = new PersonContainsKeywordsPredicate(
            POPULATED_MULTI_MAP_WITH_TAG);
    public static final PersonContainsKeywordsPredicate MEMO_PREDICATE = new PersonContainsKeywordsPredicate(
            POPULATED_MULTI_MAP_WITH_MEMO);
    public static final PersonContainsKeywordsPredicate ALL_PREDICATE = new PersonContainsKeywordsPredicate(
            POPULATED_MULTI_MAP_WITH_ALL);

    @Test
    public void equals_samePredicate_returnsTrue() {
        // same object -> returns true
        assertEquals(EMPTY_PREDICATE, EMPTY_PREDICATE);
        assertEquals(NAME_PREDICATE, NAME_PREDICATE);
        assertEquals(PHONE_PREDICATE, PHONE_PREDICATE);
        assertEquals(EMAIL_PREDICATE, EMAIL_PREDICATE);
        assertEquals(ADDRESS_PREDICATE, ADDRESS_PREDICATE);
        assertEquals(TAG_PREDICATE, TAG_PREDICATE);
        assertEquals(MEMO_PREDICATE, MEMO_PREDICATE);
        assertEquals(ALL_PREDICATE, ALL_PREDICATE);
    }

    @Test
    public void equals_differentPredicate_returnsFalse() {
        // null -> returns false
        assertNotEquals(null, EMPTY_PREDICATE);
        assertNotEquals(null, NAME_PREDICATE);
        assertNotEquals(null, PHONE_PREDICATE);
        assertNotEquals(null, EMAIL_PREDICATE);
        assertNotEquals(null, ADDRESS_PREDICATE);
        assertNotEquals(null, TAG_PREDICATE);
        assertNotEquals(null, MEMO_PREDICATE);
        assertNotEquals(null, ALL_PREDICATE);

        // different person -> returns false
        assertNotEquals(EMPTY_PREDICATE, NAME_PREDICATE);
        assertNotEquals(NAME_PREDICATE, PHONE_PREDICATE);
        assertNotEquals(PHONE_PREDICATE, EMAIL_PREDICATE);
        assertNotEquals(ADDRESS_PREDICATE, TAG_PREDICATE);
        assertNotEquals(TAG_PREDICATE, MEMO_PREDICATE);
        assertNotEquals(MEMO_PREDICATE, ALL_PREDICATE);
        assertNotEquals(ALL_PREDICATE, EMPTY_PREDICATE);
    }

    @Test
    public void personContainsKeyWord_attributeCheck_returnsTrue() {
        // Test is case-insensitive, should return true since person name contains the word: alex".
        assertTrue(NAME_PREDICATE.test(new PersonBuilder().withName("Alex").build()));
        assertTrue(NAME_PREDICATE.test(new PersonBuilder().withName("alex").build()));
        assertTrue(NAME_PREDICATE.test(new PersonBuilder().withName("aleX").build()));
        assertTrue(NAME_PREDICATE.test(new PersonBuilder().withName("alEx").build()));
        assertTrue(NAME_PREDICATE.test(new PersonBuilder().withName("aLex").build()));
        assertTrue(NAME_PREDICATE.test(new PersonBuilder().withName("ALEX").build()));

        // Search for multiple words should return true.
        assertTrue(NAME_PREDICATE.test(new PersonBuilder().withName("Alex Bob").build()));
        assertTrue(NAME_PREDICATE.test(new PersonBuilder().withName("alex bob").build()));

        // Same phone number -> returns true.
        assertTrue(PHONE_PREDICATE.test(new PersonBuilder().withPhone("90100102").build()));

        // Same email -> returns true.
        assertTrue(EMAIL_PREDICATE.test(new PersonBuilder().withEmail("tester@mail.com").build()));

        // Identical address -> returns true.
        assertTrue(
                ADDRESS_PREDICATE.test(new PersonBuilder().withAddress("QueensTown blk 200 singapore 123123").build()));

        // Partial address of the full address -> returns true.
        assertTrue(ADDRESS_PREDICATE.test(new PersonBuilder().withAddress("QueensTown").build()));

        // Person only contains one of the tags -> returns true.
        assertTrue(TAG_PREDICATE.test(new PersonBuilder().withTags("friends").build()));
        assertTrue(TAG_PREDICATE.test(new PersonBuilder().withTags("colleagues").build()));

        // Person contains both tags -> returns true.
        String[] personTags = {"colleagues", "friends"};
        assertTrue(TAG_PREDICATE.test(new PersonBuilder().withTags(personTags).build()));

    }

    @Test
    public void personContainsKeyWord_attributeCheck_returnsFalse() {
        // Test is case-insensitive, both should return true since person name contains the word: alex".
        assertFalse(NAME_PREDICATE.test(new PersonBuilder().withName("A").build()));
        assertFalse(NAME_PREDICATE.test(new PersonBuilder().withName("a").build()));
        assertFalse(NAME_PREDICATE.test(new PersonBuilder().withName("x").build()));
        assertFalse(NAME_PREDICATE.test(new PersonBuilder().withName("blank").build()));

        // Search for multiple words should return false.
        assertFalse(NAME_PREDICATE.test(new PersonBuilder().withName("Al").build()));

        // Different phone number -> returns false.
        assertFalse(PHONE_PREDICATE.test(new PersonBuilder().withPhone("90100202").build()));

        // Different email -> returns false.
        assertFalse(EMAIL_PREDICATE.test(new PersonBuilder().withEmail("test@mail.com").build()));

        // Different address -> returns false.
        assertFalse(ADDRESS_PREDICATE.test(new PersonBuilder().withAddress("QueensTowns").build()));

        // Person do not contain any of the tags -> returns false.
        assertFalse(TAG_PREDICATE.test(new PersonBuilder().withTags("friend").build()));
        assertFalse(TAG_PREDICATE.test(new PersonBuilder().withTags("colleague").build()));

        // Person do not contain both tags -> returns false.
        String[] personTags = {"colleague", "friend"};
        assertFalse(TAG_PREDICATE.test(new PersonBuilder().withTags(personTags).build()));

        // Person do not have that memo -> returns false.
        assertFalse(MEMO_PREDICATE.test(new PersonBuilder().withMemo("close").build()));
        assertFalse(MEMO_PREDICATE.test(new PersonBuilder().withMemo(" ").build()));
    }

}
