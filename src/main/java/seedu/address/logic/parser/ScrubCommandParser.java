package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;.

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.ScrubCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ScrubCommandParser implements Parser<ScrubCommand> {
    private static final Logger LOGGER = Logger.getLogger(ScrubCommandParser.class.getName());

    /**
     * Parses the given {@code String} of arguments into the ScrubCommand. Trims off all
     * leading and trailing white space and returns a ScrubCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ScrubCommand parse(String args) throws ParseException {
        LOGGER.log(Level.INFO, "Parsing user input");
        String modifiedString = getProcessedString(args);
        PersonDescriptor descriptor = new PersonDescriptor(modifiedString);
        return new ScrubCommand(descriptor);
    }

    private String getProcessedString(String args) throws ParseException {
        String trimmedArgs = args.trim();
        // Regex to replace 2 or more consecutive whitespaces with a single whitespace between words
        String modifiedString = trimmedArgs.replaceAll("\\s{2,}", " ");
        if (modifiedString.isEmpty() || prefixExistsWithinString(modifiedString, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_MEMO)) {
            LOGGER.log(Level.INFO, "Input is empty");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScrubCommand.MESSAGE_USAGE));
        }
        return modifiedString;
    }


    private boolean prefixExistsWithinString(String modifiedString, Prefix... prefixes) {
        for (Prefix p : prefixes) {
            if (modifiedString.contains(p.toString()))
                return true;
        }
        return false;
    }
}
