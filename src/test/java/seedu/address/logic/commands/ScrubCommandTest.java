package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.parser.PersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.ScrubPersonPredicate;

class ScrubCommandTest {

    @Test
    void execute_partialEmail() {
        String expectedMessage = String.format(ScrubCommand.MESSAGE_SCRUB_SUCCESS, 7);
        String partialEmailDescription = "e/@example";
        PersonDescriptor partialEmailDescriptor = new PersonDescriptor(partialEmailDescription);
        testExecuteMethod(partialEmailDescriptor, expectedMessage);
    }

    @Test
    void execute_fullEmail() {
        String expectedMessage = String.format(ScrubCommand.MESSAGE_SCRUB_SUCCESS, 7);
        String fullEmailDescription = "e/@example.com";
        PersonDescriptor fullEmailDescriptor = new PersonDescriptor(fullEmailDescription);
        testExecuteMethod(fullEmailDescriptor, expectedMessage);
    }

    @Test
    void execute_singlePhone() {
        String expectedMessage = String.format(ScrubCommand.MESSAGE_SCRUB_SUCCESS, 1);
        String phoneDescription = "p/94351253";
        PersonDescriptor phoneDescriptor = new PersonDescriptor(phoneDescription);
        testExecuteMethod(phoneDescriptor, expectedMessage);
    }

    private void testExecuteMethod(PersonDescriptor phoneDescriptor, String expectedMessage) {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ScrubCommand command = new ScrubCommand(phoneDescriptor);
        ScrubPersonPredicate predicate = new ScrubPersonPredicate(phoneDescriptor);
        deletePersonProtocol(expectedModel, predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    private void deletePersonProtocol(Model expectedModel, ScrubPersonPredicate predicate) {
        expectedModel.updateFilteredPersonList(predicate);
        ObservableList<Person> personQueueForDeletion = expectedModel.getFilteredPersonList();
        expectedModel.deleteAllPerson(personQueueForDeletion);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        expectedModel.saveAddressBookState();
    }

    @Test
    void execute_multiplePhone() {
        String expectedMessage = String.format(ScrubCommand.MESSAGE_SCRUB_SUCCESS, 2);
        String multiplePhoneDescription = "p/94351253 98765432";
        PersonDescriptor multiplePhoneDescriptor = new PersonDescriptor(multiplePhoneDescription);
        testExecuteMethod(multiplePhoneDescriptor, expectedMessage);
    }

    @Test
    void execute_partialPhone_noDeletion() {
        String expectedMessage = String.format(ScrubCommand.MESSAGE_SCRUB_SUCCESS, 0);
        String partialPhone = "p/9435";
        PersonDescriptor partialPhoneDescriptor = new PersonDescriptor(partialPhone);
        testExecuteMethod(partialPhoneDescriptor, expectedMessage);
    }

    @Test
    void testEquals() {
        String description = "e/@example";
        PersonDescriptor descriptor = new PersonDescriptor(description);
        ScrubCommand scrubCommand = new ScrubCommand(descriptor);
        assertEquals(scrubCommand, new ScrubCommand(descriptor));
    }
}
