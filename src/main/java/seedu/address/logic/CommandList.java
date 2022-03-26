package seedu.address.logic;

import java.util.ArrayList;

import seedu.address.logic.commands.NextCommand;
import seedu.address.logic.commands.PreviousCommand;
import seedu.address.logic.commands.exceptions.HistoryException;


/**
 * Records the latest user inputs.
 * Only the latest MAX_RECORD_NUMBER inputs will be recorded.
 */
public class CommandList {
    private static CommandList theList = null;
    private static final int MAX_RECORD_NUMBER = 20;
    private int pointer = -1;
    private final ArrayList<String> inputHistory = new ArrayList<>();

    private CommandList() {}
    /**
     * Records the latest user input.
     * @param lastCommand the last user input.
     */
    public void record(String lastCommand) {
        if (lastCommand.equalsIgnoreCase(PreviousCommand.COMMAND_WORD)
                || lastCommand.equalsIgnoreCase(NextCommand.COMMAND_WORD)) {
            return;
        }
        inputHistory.add(lastCommand);
        if (inputHistory.size() > MAX_RECORD_NUMBER) {
            clearOldCommands();
            return;
        }
        pointer++;
    }

    /** Gets the latest user input. */
    public String getLastCommand() {
        return inputHistory.get(pointer);
    }

    /** Checks whether there is any command history or not. */
    public boolean isEmpty() {
        return inputHistory.size() == 0;
    }

    private void clearOldCommands() {
        inputHistory.remove(0);
    }

    public static CommandList getList() {
        if (theList == null) {
            theList = new CommandList();
        }
        return theList;
    }

    /** Increase the pointer value by 1. */
    public void increasePointer() throws HistoryException {
        if (pointer >= inputHistory.size() - 1) {
            throw new HistoryException(NextCommand.MESSAGE_ON_NO_NEXT);
        }
        pointer++;
    }

    /** Decreases the pointer value by 1. */
    public void decreasePointer() throws HistoryException {
        if (pointer <= 0) {
            throw new HistoryException(PreviousCommand.MESSAGE_ON_NO_PREVIOUS);
        }
        pointer--;
    }

    public void resetPointer() {
        pointer = inputHistory.size();
    }

    /**
     * Only used in test currently.
     */
    public void clearAllCommand() {
        inputHistory.clear();
    }
}
