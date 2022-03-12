package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MemoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Memo;

/**
 * Parses input arguments and creates a new {@code MemoCommand} object
 */
public class MemoCommandParser implements Parser<MemoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code MemoCommand}
     * and returns a {@code MemoCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MemoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMO);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MemoCommand.MESSAGE_USAGE), ive);
        }

        String memo = argMultimap.getValue(PREFIX_MEMO).orElse("");

        return new MemoCommand(index, new Memo(memo));
    }
}
