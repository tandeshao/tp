package seedu.address.logic.commands.exceptions;

/**
 * Represents the execution of a {@link seedu.address.logic.commands.HistoryCommand}.
 */
public class HistoryCommandException extends CommandException {
    public HistoryCommandException(String message) {
        super(message);
    }

}
