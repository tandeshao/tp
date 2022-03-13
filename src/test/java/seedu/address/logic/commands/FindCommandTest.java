package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.person.PersonContainsKeywordsPredicateTest.ADDRESS_PREDICATE;
import static seedu.address.model.person.PersonContainsKeywordsPredicateTest.ALL_PREDICATE;
import static seedu.address.model.person.PersonContainsKeywordsPredicateTest.EMAIL_PREDICATE;
import static seedu.address.model.person.PersonContainsKeywordsPredicateTest.EMPTY_PREDICATE;
import static seedu.address.model.person.PersonContainsKeywordsPredicateTest.MEMO_PREDICATE;
import static seedu.address.model.person.PersonContainsKeywordsPredicateTest.NAME_PREDICATE;
import static seedu.address.model.person.PersonContainsKeywordsPredicateTest.PHONE_PREDICATE;
import static seedu.address.model.person.PersonContainsKeywordsPredicateTest.TAG_PREDICATE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals_sameFindCommand() {
        FindCommand containsAllSAttributeFindCommand = new FindCommand(ALL_PREDICATE);
        FindCommand containsNameAttributeFindCommand = new FindCommand(NAME_PREDICATE);
        FindCommand containsPhoneAttributeFindCommand = new FindCommand(PHONE_PREDICATE);
        FindCommand containsEmailAttributeFindCommand = new FindCommand(EMAIL_PREDICATE);
        FindCommand containsAddressAttributeFindCommand = new FindCommand(ADDRESS_PREDICATE);
        FindCommand containsTagAttributeFindCommand = new FindCommand(TAG_PREDICATE);
        FindCommand containsMemoAttributeFindCommand = new FindCommand(MEMO_PREDICATE);

        // same object -> returns true
        assertEquals(containsAllSAttributeFindCommand, containsAllSAttributeFindCommand);
        assertEquals(containsNameAttributeFindCommand, containsNameAttributeFindCommand);
        assertEquals(containsPhoneAttributeFindCommand, containsPhoneAttributeFindCommand);
        assertEquals(containsEmailAttributeFindCommand, containsEmailAttributeFindCommand);
        assertEquals(containsAddressAttributeFindCommand, containsAddressAttributeFindCommand);
        assertEquals(containsTagAttributeFindCommand, containsTagAttributeFindCommand);
        assertEquals(containsMemoAttributeFindCommand, containsMemoAttributeFindCommand);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        FindCommand command = new FindCommand(EMPTY_PREDICATE);
        expectedModel.updateFilteredPersonList(EMPTY_PREDICATE);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonContainsKeywordsPredicate predicate = preparePredicate(" n/ Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PersonContainsKeywordsPredicate preparePredicate(String userInput) {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG);
        return new PersonContainsKeywordsPredicate(argMultimap);
    }
}
