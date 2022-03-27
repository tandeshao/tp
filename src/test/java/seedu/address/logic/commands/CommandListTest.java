package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.CommandList;
import seedu.address.logic.CommandRemark;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

public class CommandListTest {

    private final Model model = new ModelManager();

    @Test
    public void getCurrentCommand_success(){
        CommandList.getList().clearAllCommand();
        CommandList.getList().record("find n/ John1");
        CommandList.getList().record("find n/ John2");
        CommandList.getList().record("find n/ John3");
        CommandList.getList().record("find n/ John4");

        Model expectedModel = new ModelManager();

        assertEquals("find n/ John4", CommandList.getList().getCurrentCommand());
    }

}
