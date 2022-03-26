package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.NO_PREFIX_MESSAGE;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicate.FindPersonPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Logger LOGGER = Logger.getLogger(FindCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments into the FindCommand. Trims off all
     * leading and trailing white space and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        LOGGER.log(Level.INFO, "Parsing user input");
        String modifiedString = modifyUserInput(args);
        PersonDescriptor descriptor = createDescriptor(modifiedString);
        FindPersonPredicate predicate = new FindPersonPredicate(descriptor);
        return new FindCommand(predicate);
    }

    /**
     * Cleans up user input and make it appropriate to be parsed into a descriptor class.
     *
     * @param args unmodified user input.
     * @return valid input that can be used for the PersonDescriptor class.
     * @throws ParseException thrown when the user argument is empty.
     */
    private String modifyUserInput(String args) throws ParseException {
        // Removes leading and trailing whitespace from the user input
        String trimmedArgs = args.trim();
        // Regex to replace 2 or more consecutive whitespaces with a single whitespace between words
        String modifiedString = trimmedArgs.replaceAll("\\s{2,}", " ");
        if (modifiedString.isEmpty()) {
            LOGGER.log(Level.INFO, "Input is empty");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return modifiedString;
    }

    /**
     * Parses the given user argument and check if there is any valid prefix.
     * If no valid prefix is found, a parse exception is thrown. Else, a new descriptor is created.
     *
     * @param modifiedString user input that has its trailing and leading whitespaces removed.
     * @return descriptor that stores the description to search a person by.
     * @throws ParseException if there are no valid prefix in the user input.
     */
    private PersonDescriptor createDescriptor(String modifiedString) throws ParseException {
        PersonDescriptor descriptor = new PersonDescriptor(modifiedString);
        if (descriptor.isEmpty()) {
            LOGGER.log(Level.INFO, "Input has no valid prefix");
            throw new ParseException(NO_PREFIX_MESSAGE);
        }
        return descriptor;
    }


}
