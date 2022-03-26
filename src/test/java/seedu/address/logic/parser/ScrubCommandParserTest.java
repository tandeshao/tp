package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ScrubCommand;

class ScrubCommandParserTest {
    private final ScrubCommandParser parser = new ScrubCommandParser();

    @Test
    void parse_invalidArg_throwsParseException() {
        String invalidArgMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScrubCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "     ", invalidArgMessage);
        assertParseFailure(parser, "a/tester st 123", invalidArgMessage);
        assertParseFailure(parser, "e/@gmail.com n/hello", invalidArgMessage);
        assertParseFailure(parser, "a/tester st 123 n/hello", invalidArgMessage);
        assertParseFailure(parser, "e/tester@gmail.com", ScrubCommand.MESSAGE_WRONG_DOMAIN_FORMAT);
        assertParseFailure(parser, "e/@gmail.com tester@gmail.com", ScrubCommand.MESSAGE_WRONG_DOMAIN_FORMAT);
    }

    @Test
    void parse_validArgs_returnsScrubCommand() {
        String validPhone = "p/90400204";
        ScrubCommand validScrubPhoneCommand = new ScrubCommand(new PersonDescriptor(validPhone));
        assertParseSuccess(parser, validPhone, validScrubPhoneCommand);

        String multiplePhone = "p/90400204 90400203";
        ScrubCommand validMultiplePhoneCommand = new ScrubCommand(new PersonDescriptor(multiplePhone));
        assertParseSuccess(parser, multiplePhone, validMultiplePhoneCommand);

        String multipleEmail = "e/@mail.com @example";
        ScrubCommand validMultipleEmailCommand = new ScrubCommand(new PersonDescriptor(multipleEmail));
        assertParseSuccess(parser, multipleEmail, validMultipleEmailCommand);
    }
}
