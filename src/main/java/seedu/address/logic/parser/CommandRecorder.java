package seedu.address.logic.parser;

public class CommandRecorder {
    private static String userInput = "";

    public static void record(String lastCommand) {
        userInput = lastCommand.equals("invoke") ? userInput : lastCommand;
    }

    public static String getLastCommand() {
        return userInput;
    }
}
