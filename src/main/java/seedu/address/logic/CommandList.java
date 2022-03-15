package seedu.address.logic;

import java.util.ArrayList;

/**
 * Records the latest user inputs.
 * Only the latest MAX_RECORD_NUMBER inputs will be recorded.
 */
public class CommandList {
    private static final int MAX_RECORD_NUMBER = 3;
    private static final ArrayList<String> inputHistory = new ArrayList<>();

    /**
     * Records the latest user input.
     * @param lastCommand the last user input.
     */
    public static void record(String lastCommand) {
        if (!lastCommand.equals("history")) {
            inputHistory.add(lastCommand);
        }
        if (inputHistory.size() > MAX_RECORD_NUMBER) {
            clearOldCommand();
        }
    }

    /** Gets the latest user input. */
    public static String getLastCommand() {
        if (inputHistory.size() == 0) {
            return "";
        }
        return inputHistory.get(inputHistory.size() - 1);
    }

    /** Gets the most recent commands(number capped at MAX_RECORD_NUMBER). */
    public static String getRecentCommands() {
        StringBuilder result = new StringBuilder("");
        for (int i = 1; i <= inputHistory.size(); i++) {
            result.append(inputHistory.get(inputHistory.size() - i));
            if (i != inputHistory.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /** Checks whether there is any command history or not. */
    public static boolean isEmpty() {
        return inputHistory.size() == 0;
    }

    private static void clearOldCommand() {
        inputHistory.remove(0);
    }

    /**
     * Only used in test currently.
     */
    public static void clearAllCommand() {
        inputHistory.clear();
    }
}
