package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class CommandListTest {

    private final Model model = new ModelManager();

    @Test
    public void getCurrentCommand_success() throws CommandException {
        CommandList.getList().resetPointer();
        CommandList.getList().clearAllCommand();
        CommandList.getList().record("find n/ John1");
        CommandList.getList().record("find n/ John2");
        CommandList.getList().record("find n/ John3");
        CommandList.getList().record("find n/ John4");
        CommandList.getList().decreasePointer();

        Model expectedModel = new ModelManager();

        assertEquals("find n/ John4", CommandList.getList().getCurrentCommand());
    }

}
