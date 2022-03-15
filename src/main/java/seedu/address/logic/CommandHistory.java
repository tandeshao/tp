package seedu.address.logic;

import java.util.ArrayList;

/**
 * Record the latest user input for HistoryCommand.
 */
public class CommandHistory {
    private static final int MAX_RECORD_NUMBER = 3;
    private static final ArrayList<String> inputHistory = new ArrayList<>();

    /**
     * Record the latest user input.
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

    /**
     * Get the recorded user input for HistoryCommand.
     */
    public static String getLastCommand() {
        if (inputHistory.size() == 0) {
            return "";
        }
        return inputHistory.get(inputHistory.size() - 1);
    }

    /**
     * Get the most recent commands(number capped at MAX_RECORD_NUMBER).
     */
    public static String getRecentCommands() {
        StringBuilder result = new StringBuilder(inputHistory.get(0));
        for (int i = 1; i < inputHistory.size(); i++) {
            result.append("\n");
            result.append(inputHistory.get(inputHistory.size() - i));
        }
        return result.toString();
    }

    public static boolean isEmpty() {
        return inputHistory.size() == 0;
    }

    private static void clearOldCommand() {
        inputHistory.remove(0);
    }
}
