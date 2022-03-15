package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.HistoryCommandException;
import seedu.address.logic.parser.CommandHistory;
import seedu.address.model.Model;

/**
 * Invoke the most recent command that was typed in by user.
 * History will not count "history" as a result, it will return the most recent command that is not "invoke".
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Invoke the most recently used command "
            + "Parameters: none\n"
            + "Example: " + COMMAND_WORD;

    public HistoryCommand() {}

    @Override
    public CommandResult execute(Model model) throws HistoryCommandException {
        requireNonNull(model);
        throw new HistoryCommandException(CommandHistory.getLastCommand());
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
