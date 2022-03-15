package seedu.address.logic.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.address.logic.CommandList;

public class CommandListTest {

    @Test
    public void getRecentCommands_test() {
        CommandList.clearAllCommand();
        CommandList.record("find n/ John1");
        CommandList.record("find n/ John2");
        CommandList.record("find n/ John3");
        CommandList.record("find n/ John4");

        Assertions.assertEquals(CommandList.getRecentCommands(), "find n/ John4\nfind n/ John3\nfind n/ John2");
    }

    @Test
    public void getLastCommand_test() {
        CommandList.clearAllCommand();
        CommandList.record("find n/ John1");
        CommandList.record("find n/ John2");
        CommandList.record("find n/ John3");
        CommandList.record("find n/ John4");

        Assertions.assertEquals(CommandList.getLastCommand(), "find n/ John4");
    }
}
