package seedu.address.logic.commands;


import org.junit.jupiter.api.Test;
import seedu.address.logic.CommandList;
import seedu.address.logic.CommandRemark;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

public class HistoryCommandTest {
    @Test
    public void history_command_test1() {
        CommandList.clearAllCommand();
        CommandList.record("find n/ John1");
        CommandList.record("find n/ John2");
        CommandList.record("find n/ John3");
        CommandList.record("find n/ John4");

        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult = new CommandResult(HistoryCommand.MESSAGE_ON_HISTORY_SUCCESS
                + "find n/ John4\nfind n/ John3\nfind n/ John2", CommandRemark.HISTORY);
        assertCommandSuccess(new HistoryCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void history_command_test2() {
        CommandList.clearAllCommand();
        CommandList.record("find n/ John1");

        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult = new CommandResult(HistoryCommand.MESSAGE_ON_HISTORY_SUCCESS
                + "find n/ John1", CommandRemark.HISTORY);
        assertCommandSuccess(new HistoryCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void empty_history_command_test() {
        CommandList.clearAllCommand();
        Model model = new ModelManager();
        assertThrows(CommandException.class, HistoryCommand.MESSAGE_ON_EMPTY_HISTORY, () -> new HistoryCommand().execute(model));
    }

}
