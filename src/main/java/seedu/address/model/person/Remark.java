package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark {
    public final String value;

    /**
     * Constructor for remark object.
     * @param remark Remarks for the person.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    /**
     * Returns String representation of Remark.
     * @return String representation of remark.
     */
    @Override
    public String toString() {
        return value;
    }


    /**
     * Overrides object.equals method to allow comparison between two remark objects.
     *
     * @param other The other remark object.
     * @return Boolean value representing the result of comparison between two remark objects.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
