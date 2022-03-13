package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Memo;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MemoCommand.
 */
public class MemoCommandTest {
    private static final String MEMO_STUB = "Some memo";

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addMemoUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withMemo(MEMO_STUB).build();

        MemoCommand memoCommand = new MemoCommand(INDEX_FIRST_PERSON, new Memo(editedPerson.getMemo().memo));

        String expectedMessage = String.format(MemoCommand.MESSAGE_ADD_MEMO_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(memoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteMemoUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withMemo("").build();

        MemoCommand memoCommand = new MemoCommand(INDEX_FIRST_PERSON, new Memo(editedPerson.getMemo().toString()));

        String expectedMessage = String.format(MemoCommand.MESSAGE_DELETE_MEMO_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(memoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(
                model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased())).withMemo(MEMO_STUB).build();

        MemoCommand memoCommand = new MemoCommand(INDEX_FIRST_PERSON, new Memo(editedPerson.getMemo().memo));

        String expectedMessage = String.format(MemoCommand.MESSAGE_ADD_MEMO_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(memoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MemoCommand memoCommand = new MemoCommand(outOfBoundIndex, new Memo(VALID_MEMO_BOB));

        assertCommandFailure(memoCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        MemoCommand memoCommand = new MemoCommand(outOfBoundIndex, new Memo(VALID_MEMO_BOB));
        assertCommandFailure(memoCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final MemoCommand standardCommand = new MemoCommand(INDEX_FIRST_PERSON, new Memo(VALID_MEMO_AMY));

        // same values -> returns true
        MemoCommand commandWithSameValues = new MemoCommand(INDEX_FIRST_PERSON, new Memo(VALID_MEMO_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MemoCommand(INDEX_SECOND_PERSON, new Memo(VALID_MEMO_AMY))));

        // different memo -> returns false
        assertFalse(standardCommand.equals(new MemoCommand(INDEX_FIRST_PERSON, new Memo(VALID_MEMO_BOB))));
    }
}
