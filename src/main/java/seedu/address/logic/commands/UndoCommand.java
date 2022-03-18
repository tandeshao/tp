package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;

/**
 * Restores the {@code Model}'s address book to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Restores the address book to its previous state. "
            + "(Up to " + AddressBook.UNDO_REDO_CAPACITY + " undo)\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Undo success! Undid: '%1$s'";
    public static final String MESSAGE_UNDO_EMPTY = "There is nothing to undo.";

    private String previousInput;

    /**
     * Executes the undo command and returns the result message.
     * Restores the {@code Model}'s address book to its previous state.
     *
     * @param model {@code Model} which the undo command should operate on.
     * @return feedback message of the undo operation result for display.
     * @throws CommandException If an error occurs during undo command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoAddressBook()) {
            throw new CommandException(MESSAGE_UNDO_EMPTY);
        }

        setUndoStatePreviousInput(model);
        model.undoAddressBook();
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_UNDO_SUCCESS, previousInput));
    }

    /**
     * Sets {@code previousInput} that resulted in the address book state.
     *
     * @param model {@code model} of the address book.
     */
    private void setUndoStatePreviousInput(Model model) {
        this.previousInput = model.getUndoStatePreviousInput();
    }

    /**
     * Checks if two {@code UndoCommand} is equal.
     *
     * @param other the other {@code UndoCommand} object.
     * @return true if equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof UndoCommand; // instanceof handles nulls
    }
}
