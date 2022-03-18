package seedu.address.model.exceptions;

/**
 * Signals that the operation will fail as there is nothing to undo.
 */
public class EmptyUndoStateDequeException extends RuntimeException {
    public EmptyUndoStateDequeException() {
        super("undoStateDeque is empty, there is nothing to undo.");
    }
}
