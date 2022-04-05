package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.predicate.FindPersonPredicate;

/**
 * Finds and lists all persons in address book whose attribute
 * contains any of the tokenized argument. Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose contact details matches any of "
            + "the specified keywords (case-insensitive).\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME]... "
            + "[" + PREFIX_PHONE + "PHONE]... "
            + "[" + PREFIX_EMAIL + "EMAIL]... "
            + "[" + PREFIX_ADDRESS + "ADDRESS]... "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_MEMO + "MEMO]... "
            + "[" + PREFIX_CONTACTED_DATE + "CONTACTED DATE]...\n"
            + "Example: " + COMMAND_WORD + " n/alice bob" + " e/alice@gmail.com";
    public static final String NO_PREFIX_MESSAGE = "At least one field to find must be provided.";
    private final FindPersonPredicate predicate;

    public FindCommand(FindPersonPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the main logic of the FindCommand.
     * Filters for {@link seedu.address.model.person.Person Persons} that meets the
     * {@link #predicate} condition.
     *
     * @param model {@code Model} which the command should operate on.
     * @return message that should be reflected in the {@link seedu.address.ui.Ui}.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        int numberOfFilteredPerson = model.getFilteredPersonList().size();
        if (numberOfFilteredPerson == 0) {
            return new CommandResult(MESSAGE_EMPTY_SEARCH);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, numberOfFilteredPerson));
    }

    /**
     * Checks if two {@code FindCommand} are equal.
     *
     * @param other the other {@code FindCommand} object.
     * @return true if equal; otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
