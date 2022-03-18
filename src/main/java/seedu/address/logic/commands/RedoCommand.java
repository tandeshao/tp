package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Restores the {@code Model}'s address book to its previous state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Restores the address book to its previous undid state. "
            + "(Up to " + AddressBook.UNDO_REDO_CAPACITY + " redo)\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_REDO_SUCCESS = "Redo success! Redid: '%1$s'";
    public static final String MESSAGE_REDO_EMPTY = "There is nothing to redo.";

    private String previousUndidInput;

    /**
     * Executes the redo command and returns the result message.
     * Restores the {@code Model}'s address book to its previous state.
     *
     * @param model {@code Model} which the redo command should operate on.
     * @return feedback message of the redo operation result for display.
     * @throws CommandException If an error occurs during redo command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoAddressBook()) {
            throw new CommandException(MESSAGE_REDO_EMPTY);
        }

        setRedoStatePreviousInput(model);
        model.redoAddressBook();
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REDO_SUCCESS, previousUndidInput));
    }

    /**
     * Sets {@code previousUndidInput} that resulted in the address book state.
     *
     * @param model {@code model} of the address book.
     */
    private void setRedoStatePreviousInput(Model model) {
        this.previousUndidInput = model.getRedoStatePreviousInput();
    }

    /**
     * Checks if two {@code RedoCommand} is equal.
     *
     * @param other the other {@code RedoCommand} object.
     * @return true if equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof RedoCommand; // instanceof handles nulls
    }
}
