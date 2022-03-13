package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsKeywordPredicate;

import static java.util.Objects.requireNonNull;

public class SpecialFindCommand extends Command {
    public static final String COMMAND_WORD = "sfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose contact details matches any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/ alice bob charlie" + " e/ alice@gmail.com";

    private final PersonContainsKeywordPredicate predicate;

    public SpecialFindCommand(PersonContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SpecialFindCommand // instanceof handles nulls
                && predicate.equals(((SpecialFindCommand) other).predicate)); // state check
    }
}
