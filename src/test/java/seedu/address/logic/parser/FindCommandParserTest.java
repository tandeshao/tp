package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.NO_PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicate.FindPersonPredicate;

public class FindCommandParserTest {
    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_invalidArg_throwsParseException() {
        // Empty input -> should result in parse failure
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // Invalid prefix -> should result in parse failure
        assertParseFailure(parser, "find z/ test1 s/test2", NO_PREFIX_MESSAGE);

        // Stand-alone command -> should result in pare failure
        assertParseFailure(parser, "find ", NO_PREFIX_MESSAGE);

        // Special characters for ContactedDate -> should result in pare failure
        assertParseFailure(parser, "find c/@", FindCommandParser.MESSAGE_INCORRECT_FORMAT);

        // Negative integer values for ContactedDate -> should result in pare failure
        assertParseFailure(parser, "find c/-1", FindCommandParser.MESSAGE_INCORRECT_FORMAT);

        // Positive value above 2147483647 is invalid.
        assertParseFailure(parser, "find c/2147483648", FindCommandParser.MESSAGE_INCORRECT_FORMAT);

    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        assertParseSuccess(parser, " n/Alex bob", prepareFindCommand(" n/Alex bob"));
        assertParseSuccess(parser, " c/1", prepareFindCommand(" c/1"));

        // multiple whitespaces between keywords
        String argToBeTested = " n/ Alex bob";
        assertParseSuccess(parser, " n/\n Alex \n \t bob \t", prepareFindCommand(argToBeTested));

        // Max positive integer values -> parse successful.
        String argWithMaxPositiveInteger = " c/2147483647";
        assertParseSuccess(parser, argWithMaxPositiveInteger, prepareFindCommand(argWithMaxPositiveInteger));
    }

    /**
     * Prepares the FindCommand for testing.
     *
     * @param userFindCommandArgs  User argument that is passed into the FindCommandParser.
     * @return A valid FindCommand that is derived from the user argument.
     */
    private FindCommand prepareFindCommand(String userFindCommandArgs) {
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(userFindCommandArgs, ARRAY_OF_PREFIX);
        FindPersonPredicate predicate = new FindPersonPredicate(descriptor);
        return new FindCommand(predicate);
    }
}

