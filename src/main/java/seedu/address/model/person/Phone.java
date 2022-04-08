package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final int PHONE_NUMBER_MAXIMUM = 100;
    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers or white spaces. "
                    + "It may also contain '+' at the start, but must be followed by a number. "
                    + "It should contain at least 3 numbers and cannot exceed " + PHONE_NUMBER_MAXIMUM
                    + " characters";
    public static final String VALIDATION_REGEX = "(?=^.{3,"
            + PHONE_NUMBER_MAXIMUM + "}$)\\+?(\\d *){3,}";
    public final String phone;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        this.phone = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return phone;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && removeAllWhiteSpace(phone)
                .equals(removeAllWhiteSpace(((Phone) other).phone))); // case-insensitive and ignores white space
    }

    /**
     * Returns true if both phone numbers are identical (case-sensitive).
     *
     * @param otherPhone The other phone number.
     * @return true if both phone numbers are identical.
     */
    public boolean exactEquals(Phone otherPhone) {
        return otherPhone != null
                && phone.equals(otherPhone.phone);
    }

    @Override
    public int hashCode() {
        return removeAllWhiteSpace(phone).hashCode();
    }

    /**
     * Removes all white space from the given string.
     *
     * @param str String to remove all white space from.
     * @return String with all white space removed.
     */
    private String removeAllWhiteSpace(String str) {
        return str.replaceAll(" ", "");
    }

}
