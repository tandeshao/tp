package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final SimpleObjectProperty<Person> personOnDisplay;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        personOnDisplay = new SimpleObjectProperty<>();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        updatePersonOnDisplay(newData.getPersonOnDisplay());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book,
     * excluding {@code except}.
     */
    public boolean hasPersonExcept(Person person, Person except) {
        requireNonNull(person);
        requireNonNull(except);
        return persons.containsExcept(person, except);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        updatePersonOnDisplay(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
        changePersonOnDisplay(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        updatePersonOnDisplayUponRemovals(key);
    }

    /**
     * Removes all person from the {@code AddressBook}.
     *
     * @param personsToRemove List of person to remove from the {@code AddressBook}
     */
    public void removePersons(Person... personsToRemove) {
        persons.removeAll(personsToRemove);
        updatePersonOnDisplayUponRemovals(personsToRemove);
    }

    private void updatePersonOnDisplayUponRemovals(Person... personsToRemove) {
        Person currentPerson = personOnDisplay.get();
        for (Person p : personsToRemove) {
            if (p.equals(currentPerson)) {
                removePersonOnDisplay();
                return;
            }
        }
    }

    private void changePersonOnDisplay(Person target, Person updatedPerson) {
        updatePersonOnDisplay(updatedPerson);
    }

    private void removePersonOnDisplay() {
        updatePersonOnDisplay(null);
    }

    private void updatePersonOnDisplay(Person p) {
        personOnDisplay.set(p);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public Person getPersonOnDisplay() {
        return personOnDisplay.get();
    }

    @Override
    public void addPersonOnDisplayListener(ChangeListener<? super Person> listener) {
        personOnDisplay.addListener(listener);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
