package seedu.address.model.exceptions;

/**
 * Signals that the operation will fail as there is nothing to redo.
 */
public class EmptyRedoStateDequeException extends RuntimeException {
    public EmptyRedoStateDequeException() {
        super("redoStateDeque is empty, there is nothing to redo.");
    }
}
