package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.CommandRecorder;
import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book whose {@link seedu.address.model.person.PersonAttribute Attribute}
 * contains any of the tokenized argument. Keyword matching is case-insensitive.
 */
public class InvokeCommand extends Command {

    public static final String COMMAND_WORD = "invoke";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Invoke the most recently used command "
            + "Parameters: none\n"
            + "Example: " + COMMAND_WORD;

    public InvokeCommand() {}

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        return new CommandResult(CommandRecorder.getLastCommand());
    }

    /**
     * Checks if two FindCommand method is equal.
     *
     * @param other The other FindCommand object.
     * @return Result of the check.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof InvokeCommand; // instanceof handles nulls
    }
}
