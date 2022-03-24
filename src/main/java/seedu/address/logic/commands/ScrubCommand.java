package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.PersonDescriptor;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.ExactWordMatchPredicate;


/**
 * Command that encapsulates the logic of remove contacts that matches the description provided
 * by the descriptor.
 */
public class ScrubCommand extends Command {
    public static final String COMMAND_WORD = "scrub";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person if that person contains the phone number specified by the user.\n"
            + "Multiple phone numbers are separated by a whitespace. \n"
            + "Parameters: Phone Number\n"
            + "Example: " + COMMAND_WORD + " 90400201 90400202 90400203";

    public static final String MESSAGE_SCRUB_SUCCESS = "ADDRESS BOOK SUCCESSFULLY SCRUBBED";
    private PersonDescriptor descriptor;

    public ScrubCommand(PersonDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        deleteAllMatch(model);
        model.saveAddressBookState();
        return new CommandResult(MESSAGE_SCRUB_SUCCESS);
    }

    /**
     * Deletes all the contacts from the address book that matches the description from
     * PersonDescriptor.
     * @param model
     */
    private void deleteAllMatch(Model model) {
        List<Prefix> availablePrefixes = descriptor.getAllAvailablePrefix();
        for (Prefix prefix : availablePrefixes) {
            if (prefix.equals(PREFIX_PHONE) || prefix.equals(PREFIX_TAG)) {
                ExactWordMatchPredicate predicate = new ExactWordMatchPredicate(prefix, descriptor.getDescription(prefix));
                predicate
            } else {

            }
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
