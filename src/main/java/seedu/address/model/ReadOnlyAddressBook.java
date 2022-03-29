package seedu.address.model;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns the current Person object on display.
     */
    Person getPersonOnDisplay();

    /**
     * Adds a listener for when the personOnDisplay changes.
     *
     * @param listener ChangeListener that reacts to the Person object changing.
     */
    void addPersonOnDisplayListener(ChangeListener<? super Person> listener);
}
