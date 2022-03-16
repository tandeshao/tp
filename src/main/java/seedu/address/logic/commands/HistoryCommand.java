package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandList;
import seedu.address.logic.CommandRemark;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Invokes the most recent command that was typed in by user and auto-fill in the text box.
 * The most 3 commands will be showed in message box.
 * History will not count "history" as a result, it will return the most recent command that is not "invoke".
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";
    public static final String MESSAGE_ON_HISTORY_SUCCESS = "These are the recent commands:\n";
    public static final String MESSAGE_ON_EMPTY_HISTORY = "Your history is blank.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Invoke the most recently used command "
            + "Parameters: none\n"
            + "Example: " + COMMAND_WORD;

    public HistoryCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (CommandList.isEmpty()) {
            throw new CommandException(MESSAGE_ON_EMPTY_HISTORY);
        }
        String message = MESSAGE_ON_HISTORY_SUCCESS + CommandList.getRecentCommands();
        return new CommandResult(message, CommandRemark.HISTORY);
    }

    /**
     * Checks if two HistoryCommand methods is equal.
     *
     * @param other The other HistoryCommand object.
     * @return Result of the check.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof HistoryCommand; //Any two HistoryCommands should be identical.
    }
}
