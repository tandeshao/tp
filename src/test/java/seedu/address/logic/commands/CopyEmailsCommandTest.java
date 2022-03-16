package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

public class CopyEmailsCommandTest {

    private Model emptyModel = new ModelManager();

    @Test
    public void execute_emptyList_throwsCommandException() {
        CopyEmailsCommand copyEmailsCommand = new CopyEmailsCommand();
        assertCommandFailure(copyEmailsCommand, emptyModel, CopyEmailsCommand.MESSAGE_EMPTY_EMAIL_LIST);
    }


}
