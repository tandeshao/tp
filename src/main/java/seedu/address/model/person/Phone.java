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
            "Phone numbers contains country code part and number part, country code should follow by a space. "
                    + "country code part starts with a \"+\" and is optional. "
                    + "Number part can only have numbers and spaces, it should contain at least 3 numbers. "
                    + "Country code should not exceed 3 numbers. "
                    + "The total length should not exceed "
                    + PHONE_NUMBER_MAXIMUM + " characters"
                    + "e.g. +65 12345678\n+123 888 777 666";
    public static final String VALIDATION_REGEX = "(?=^.{3,"
            + PHONE_NUMBER_MAXIMUM + "}$)(\\+\\d{1,3}\\s)?(\\s*\\d){3,}";
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
                && phone.equals(((Phone) other).phone)); // state check
    }

    @Override
    public int hashCode() {
        return phone.hashCode();
    }

}
