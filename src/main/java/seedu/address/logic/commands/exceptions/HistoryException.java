package seedu.address.logic.commands.exceptions;

public class HistoryException extends CommandException {
    public HistoryException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public HistoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
