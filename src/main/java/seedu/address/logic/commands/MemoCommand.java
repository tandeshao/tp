package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Memo;
import seedu.address.model.person.Person;


/**
 * Changes the memo of an existing person in the address book.
 */
public class MemoCommand extends Command {

    /**
     * Command word to invoke the memo command.
     */
    public static final String COMMAND_WORD = "memo";

    /**
     * Instructions on how to use the memo command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the memo of the person identified " + "by the "
            + "index number used in the last person listing. " + "Existing memo will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) " + PREFIX_MEMO + "[MEMO]\n" + "Example: " + COMMAND_WORD
            + " 1 " + PREFIX_MEMO + "Likes to swim.";

    /**
     * Success add message.
     */
    public static final String MESSAGE_ADD_MEMO_SUCCESS = "Added memo to Person: %1$s";

    /**
     * Success delete message.
     */
    public static final String MESSAGE_DELETE_MEMO_SUCCESS = "Removed memo from Person: %1$s";

    private final Index index;
    private final Memo memo;

    /**
     * @param index of the person in the filtered person list to edit the memo
     * @param memo  of the person to be updated to
     */
    public MemoCommand(Index index, Memo memo) {
        requireAllNonNull(index, memo);
        this.index = index;
        this.memo = memo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), memo, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the memo is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !memo.memo.isEmpty() ? MESSAGE_ADD_MEMO_SUCCESS : MESSAGE_DELETE_MEMO_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MemoCommand)) {
            return false;
        }

        // state check
        MemoCommand e = (MemoCommand) other;
        return index.equals(e.index) && memo.equals(e.memo);
    }
}
