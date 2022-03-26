package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.PersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.ScrubPersonPredicate;


/**
 * Command that encapsulates the logic of removing contacts that matches the description provided
 * by the descriptor.
 */
public class ScrubCommand extends Command {
    public static final String COMMAND_WORD = "scrub";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person if that person contains the phone number specified by the user.\n"
            + "Multiple phone numbers are separated by a whitespace. \n"
            + "Parameters: Phone Number\n"
            + "Example: " + COMMAND_WORD + " 90400201 90400202 90400203";

    public static final String MESSAGE_SCRUB_SUCCESS = "Successfully scrubbed %s person";
    public static final String MESSAGE_WRONG_DOMAIN_FORMAT = "Email scrubbing allows only domain name as a parameter."
            + "\nFor example: @domain or @domain.com";
    private final PersonDescriptor descriptor;

    public ScrubCommand(PersonDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    /**
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of the execution.
     * @throws CommandException If an error occurs during the execution of the ScrubCommand.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int numberOfDeletedPerson = removePersonThatMatchesDescription(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS); // To show all currently available person
        model.saveAddressBookState();
        return new CommandResult(String.format(MESSAGE_SCRUB_SUCCESS, numberOfDeletedPerson));
    }

    /**
     * Removes any person from the address book that matches the description described by the {@link #descriptor}.
     * @param model {@code Model} which the deletion should happen in.
     * @return Number of person that has been deleted from the address book.
     */
    private int removePersonThatMatchesDescription(Model model) {
        ScrubPersonPredicate predicate = new ScrubPersonPredicate(descriptor);
        model.updateFilteredPersonList(predicate);
        ObservableList<Person> personsToDelete = model.getFilteredPersonList();
        int numberOfDeletedPerson = personsToDelete.size();
        model.deleteAllPerson(personsToDelete);
        return numberOfDeletedPerson;
    }

    /**
     * Checks if two ScrubCommand are the same.
     * @param other ScrubCommand to be checked against.
     * @return True if both are the same ScrubCommand, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScrubCommand // instanceof handles nulls
                && descriptor.equals(((ScrubCommand) other).descriptor)); // state check
    }
}
