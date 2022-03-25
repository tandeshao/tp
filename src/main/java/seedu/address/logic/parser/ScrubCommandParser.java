package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ScrubCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for the {@link ScrubCommand} class. Ensures that arguments that
 * are passed in the ScrubCommand is in correct format.
 */
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
        String modifiedArguments = processArguments(args);
        PersonDescriptor descriptor = new PersonDescriptor(modifiedArguments);
        return new ScrubCommand(descriptor);
    }

    /**
     * Process arguments to ensure they are in the right format for ScrubCommand. Some checks that
     * include finding if valid prefix (phone, email, tags) are present in the argument
     * and invalid prefix (address, memo, name) are not present in the argument. Email processing
     * is also done here where the email argument is checked if it follows the correct format (only domain name
     * allowed as argument).
     * @param args User input that is processed.
     * @return Processed string that is ready to be parsed into ScrubCommand.
     * @throws ParseException Thrown when the string does not follow a valid format.
     */
    private String processArguments(String args) throws ParseException {
        String trimmedArgs = args.trim();
        // Regex to replace 2 or more consecutive whitespaces with a single whitespace between words
        String processedArgs = trimmedArgs.replaceAll("\\s{2,}", " ");
        boolean containsValidPrefix = containsPrefix(processedArgs, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);
        boolean containsInvalidPrefix = containsPrefix(processedArgs, PREFIX_ADDRESS, PREFIX_MEMO, PREFIX_NAME);
        if (containsInvalidPrefix || !containsValidPrefix || processedArgs.isEmpty()) {
            LOGGER.log(Level.INFO, "Input is invalid");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScrubCommand.MESSAGE_USAGE));
        }
        checkCorrectEmailFormat(processedArgs);
        return processedArgs;
    }

    /**
     * Helper method for {@link #processArguments(String)} to check if the given string argument contains any of the
     * prefixes.
     * @param args String argument to be checked.
     * @param prefixes Array of prefixes that are checked against.
     * @return true if any prefix in prefixes is within the args, false otherwise.
     */
    private boolean containsPrefix(String args, Prefix... prefixes) {
        return Arrays.stream(prefixes).anyMatch(prefix -> args.contains(prefix.toString()));
    }

    private void parseDomain(String arg) throws ParseException {
        // regex that takes everything after @
        String regexForDomainName = "(@)(.*)";
        Pattern pattern = Pattern.compile(regexForDomainName);
        Matcher matcher = pattern.matcher(arg);
        boolean argMatches = matcher.matches();
        if (!argMatches) {
            throw new ParseException(ScrubCommand.MESSAGE_WRONG_DOMAIN_FORMAT);
        }
    }

    /**
     * Checks if the email argument is in a valid format. Email argument in the scrub command has to only contain the
     * domain name and it has to start with "@".
     * @param args Email argument that is tested.
     * @throws ParseException Thrown when the email argument is not in a valid format.
     */
    private void checkCorrectEmailFormat(String args) throws ParseException {
        String emailPrefix = PREFIX_EMAIL.toString();
        if (args.contains(emailPrefix)) {
            parseDomain(retrieveEmailArg(args, args.indexOf(emailPrefix)));
        }
    }

    /**
     * Helper method for {@link #checkCorrectEmailFormat(String)} that retrieves the email argument from a given user
     * argument during the invocation of the ScrubCommand on the Ui.
     * @param arg User argument that is supplied into the parser.
     * @param prefixIndex Index position of the email prefix in the user argument.
     * @return The email argument that is within the user supplied argument.
     */
    private String retrieveEmailArg(String arg, int prefixIndex) {
        int startIndex = findStartIndex(arg, prefixIndex);
        int endIndex = findEndIndex(arg, startIndex);
        return arg.substring(startIndex, endIndex);
    }

    /**
     * Finds the starting index of the email argument.
     * @param arg  User argument during the invocation of the ScrubCommand in Ui.
     * @param prefixIndex Index position of the email prefix in the user argument.
     * @return 0-based starting index of the email argument.
     */
    private int findStartIndex(String arg, int prefixIndex) {
        // + 2 to offset email prefix.
        int startIndex = prefixIndex + 2;
        while (arg.charAt(startIndex) == ' ') {
            startIndex++;
        }
        return startIndex;
    }

    /**
     * Returns the ending index of the email argument.
     * @param arg User argument during the invocation of the ScrubCommand in Ui.
     * @param startIndex Starting index of the email argument.
     * @return 0-based ending index of the email argument.
     */
    private int findEndIndex(String arg, int startIndex) {
        int endIndex = startIndex;
        int arraySize = arg.length();
        while (endIndex < arraySize) {
            if (arg.charAt(endIndex) == ' ' || endIndex == arraySize - 1) {
                break;
            } else {
                endIndex++;
            }
        }
        return endIndex;
    }
}
