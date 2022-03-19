package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Logger LOGGER = Logger.getLogger(FindCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        LOGGER.log(Level.INFO, "Parsing user input.");
        String trimmedArgs = args.trim();
        // Regex to replace 2 or more consecutive whitespaces with a single whitespace between words
        String modifiedString = " " + trimmedArgs.replaceAll("\\s{2,}", " ");
        if (trimmedArgs.isEmpty()) {
            LOGGER.log(Level.INFO, "Input is empty.");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = createArgumentMultimap(modifiedString);
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate((argMultimap));
        return new FindCommand(predicate);
    }

    private ArgumentMultimap createArgumentMultimap(String modifiedString) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(modifiedString, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_MEMO);
        // There is no need for preamble for FindCommand
        argMultimap.removePreamble();
        // If no prefix is passed into the command.
        if (argMultimap.isEmpty()) {
            LOGGER.log(Level.INFO, "Input have no valid prefix.");
            throw new ParseException(FindCommand.NO_PREFIX_MESSAGE);
        }
        return argMultimap;
    }
}
