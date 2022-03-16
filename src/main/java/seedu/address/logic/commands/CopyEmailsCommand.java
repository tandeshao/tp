package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Copies the emails of the currently displayed list of people to clipboard.
 */
public class CopyEmailsCommand extends Command{
    public static final String COMMAND_WORD = "copyemails";
    public static final String MESSAGE_COPY_EMAILS_FAILURE = "Unable to copy to clipboard.";
    public static final String MESSAGE_EMPTY_EMAIL_LIST = "There are no emails to copy!";
    public static final String MESSAGE_COPY_EMAILS_SUCCESS = "Successfully copied \"%s\" to clipboard!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        String emailString = getAllEmails(lastShownList);
        if (emailString.length() == 0) {
            throw new CommandException(MESSAGE_EMPTY_EMAIL_LIST);
        }

        try {
            StringSelection stringSelection = new StringSelection(emailString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            String message = String.format(MESSAGE_COPY_EMAILS_SUCCESS, emailString);
            return new CommandResult(message);
        } catch (IllegalStateException e) {
            throw new CommandException(MESSAGE_COPY_EMAILS_FAILURE);
        }
    }

    /**
     * Extracts the emails from a list of {@link seedu.address.model.person.Person Persons}
     *
     * @param lastShownList A list of {@link seedu.address.model.person.Person Person} objects.
     * @return A comma separated list of emails.
     */
    private String getAllEmails(List<Person> lastShownList) {
        StringBuilder emailString = new StringBuilder();
        for (Person person: lastShownList) {
            if (emailString.length() != 0) {
                emailString.append(", ");
            }
            emailString.append(person.getEmail().toString());
        }
        return emailString.toString();
    }
}
