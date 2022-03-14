package seedu.address.logic.parser;

public class CommandRecorder {
    private static String userInput = "";

    /**
     * Record the latest user input.
     * @param lastCommand the last user input.
     */
    public static void record(String lastCommand) {
        userInput = lastCommand.equals("invoke") ? userInput : lastCommand;
    }

    /**
     * Get the recorded user input for InvokeCommand.
     */
    public static String getLastCommand() {
        return userInput;
    }
}
