package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddTagCommand object.
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {
    private static final Logger LOGGER = Logger.getLogger(AddTagCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagCommand and
     * returns an AddTagCommand object for execution.
     *
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public AddTagCommand parse(String args) throws ParseException {
        LOGGER.log(Level.INFO, "Executing AddTagCommandParser#parse(String)");
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            LOGGER.log(Level.INFO, "Invalid command format");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE), pe);
        }

        if (argMultimap.getValue(PREFIX_TAG).isEmpty()) {
            LOGGER.log(Level.INFO, "PREFIX_TAG not specified");
            throw new ParseException(AddTagCommand.MESSAGE_MISSING_PREFIX);
        }

        List<String> tagStringsToAppend = argMultimap.getAllValues(PREFIX_TAG);
        if (tagStringsToAppend.isEmpty()) {
            LOGGER.log(Level.INFO, "Tags to append is empty");
            throw new ParseException(AddTagCommand.MESSAGE_NO_TAG_SPECIFIED);
        }

        Set<Tag> tagsToAppend = ParserUtil.parseTags(tagStringsToAppend);
        LOGGER.log(Level.INFO, "AddTagCommandParser#parse(String) success");
        return new AddTagCommand(index, tagsToAppend);
    }

}
