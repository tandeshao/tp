package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MemoCommand;
import seedu.address.model.person.Memo;

public class MemoCommandParserTest {
    private final String nonEmptyMemo = "Some memo.";
    private final MemoCommandParser parser = new MemoCommandParser();

    public void parse_indexSpecified_success() {
        // have memo
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_MEMO + nonEmptyMemo;
        MemoCommand expectedCommand = new MemoCommand(INDEX_FIRST_PERSON, new Memo(nonEmptyMemo));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no memo
        userInput = targetIndex.getOneBased() + " " + PREFIX_MEMO;
        expectedCommand = new MemoCommand(INDEX_FIRST_PERSON, new Memo(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MemoCommand.MESSAGE_USAGE);
        // no parameters
        assertParseFailure(parser, MemoCommand.COMMAND_WORD, expectedMessage);
        // no index
        assertParseFailure(parser, MemoCommand.COMMAND_WORD + " " + nonEmptyMemo, expectedMessage);
    }
}
