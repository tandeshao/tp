package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.PersonContainsKeywordsPredicateTest.NAME_PREDICATE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;

public class FindCommandParserTest {

    private static final String POPULATED_TEST_USER_INPUT_WITH_NAME = " n/Alex bob";
    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindCommand expectedFindCommand = new FindCommand(NAME_PREDICATE);
        assertParseSuccess(parser, " n/Alex bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alex \n \t bob \t", expectedFindCommand);
    }

}
