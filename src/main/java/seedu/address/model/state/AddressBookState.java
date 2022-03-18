package seedu.address.model.state;

import seedu.address.model.ReadOnlyAddressBook;

public class AddressBookState {
    private final ReadOnlyAddressBook state;
    private final String previousInput;

    /**
     * Creates an AddressBookState that records the {@code state} and {@code previousInput} that resulted in this state.
     *
     * @param state address book state.
     * @param previousInput input that resulted in this address book state.
     */
    public AddressBookState(ReadOnlyAddressBook state, String previousInput) {
        this.state = state;
        this.previousInput = previousInput;
    }

    /**
     * Returns the address book {@code state}.
     *
     * @return address book state.
     */
    public ReadOnlyAddressBook getState() {
        return this.state;
    }

    /**
     * Returns the {@code previousInput} that resulted in this {@code state}.
     *
     * @return previous input.
     */
    public String getPreviousInput() {
        return this.previousInput;
    }
}
