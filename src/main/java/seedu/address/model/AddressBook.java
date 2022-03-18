package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.exceptions.EmptyRedoStateDequeException;
import seedu.address.model.exceptions.EmptyUndoStateDequeException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.state.AddressBookState;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    public static final int UNDO_REDO_CAPACITY = 5;

    private final UniquePersonList persons;
    private final Deque<AddressBookState> undoStateDeque = new ArrayDeque<>();
    private final Deque<AddressBookState> redoStateDeque = new ArrayDeque<>();

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {}

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
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// undo and redo methods

    /**
     * Restores address book to its previous state.
     *
     * @throws EmptyUndoStateDequeException If address book {@code undoStateDeque} is empty.
     */
    public void undo() throws EmptyUndoStateDequeException {
        if (!isUndoable()) {
            throw new EmptyUndoStateDequeException();
        }
        AddressBookState addressBookState = undoStateDeque.removeFirst();
        redoStateDeque.addFirst(addressBookState);
        resetData(addressBookState.getState());
    }

    /**
     * Restores address book to its previous undid state.
     *
     * @throws EmptyRedoStateDequeException If address book {@code redoStateDeque} is empty.
     */
    public void redo() throws EmptyRedoStateDequeException {
        if (!isRedoable()) {
            throw new EmptyRedoStateDequeException();
        }
        AddressBookState addressBookState = redoStateDeque.removeFirst();
        undoStateDeque.addFirst(addressBookState);
        resetData(addressBookState.getState());
    }

    /**
     * Returns true if address book is undoable, i.e., {@code undoStateDeque} is not empty;
     * otherwise returns false.
     *
     * @return true if undoable; false otherwise.
     */
    public boolean isUndoable() {
        return undoStateDeque.size() > 0;
    }

    /**
     * Returns true if address book is redoable, i.e., {@code redoStateDeque} is not empty;
     * otherwise returns false.
     *
     * @return true if redoable; otherwise false.
     */
    public boolean isRedoable() {
        return redoStateDeque.size() > 0;
    }

    /**
     * Saves the current address book state and the input that result in this state.
     *
     * @param previousInput previous input that result in this address book state.
     */
    public void saveState(String previousInput) {
        if (isFull()) {
            undoStateDeque.removeLast();
        }
        redoStateDeque.clear();
        undoStateDeque.addFirst(new AddressBookState(new AddressBook(this), previousInput));
    }

    /**
     * Returns previous input that resulted in the address book state.
     *
     * @return previous input.
     * @throws EmptyUndoStateDequeException if address book {@code undoStateDeque} is empty.
     */
    public String getUndoStatePreviousInput() throws EmptyUndoStateDequeException {
        if (!isUndoable()) {
            throw new EmptyUndoStateDequeException();
        }
        return undoStateDeque.getFirst().getPreviousInput();
    }

    /**
     * Returns the undid input that will restore the undid address book state.
     *
     * @return previous undid input.
     * @throws EmptyRedoStateDequeException if address book {@code redoStateDeque} is empty.
     */
    public String getRedoStatePreviousInput() throws EmptyRedoStateDequeException {
        if (!isRedoable()) {
            throw new EmptyRedoStateDequeException();
        }
        return redoStateDeque.getFirst().getPreviousInput();
    }

    /**
     * Returns true if undoStateDeque is full, i.e. equal to UNDO_CAPACITY; otherwise returns false.
     *
     * @return true if full; otherwise false.
     */
    private boolean isFull() {
        return undoStateDeque.size() == UNDO_REDO_CAPACITY;
    }


    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
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
