package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.InvokeCommandException;
import seedu.address.logic.parser.CommandRecorder;
import seedu.address.model.Model;

/**
 * Invoke the most recent command that was typed in by user.
 * Invoke will not count "invoke" as a result, it will return the most recent command that is not "invoke".
 */
public class InvokeCommand extends Command {

    public static final String COMMAND_WORD = "invoke";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Invoke the most recently used command "
            + "Parameters: none\n"
            + "Example: " + COMMAND_WORD;

    public InvokeCommand() {}

    @Override
    public CommandResult execute(Model model) throws InvokeCommandException {
        requireNonNull(model);
        throw new InvokeCommandException(CommandRecorder.getLastCommand());
    }

    /**
     * Checks if two InvokeCommand methods is equal.
     *
     * @param other The other InvokeCommand object.
     * @return Result of the check.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof InvokeCommand; // instanceof handles nulls
    }
}
