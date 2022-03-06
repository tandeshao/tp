package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's memo in the address book.
 * Guarantees: immutable; is always valid
 */
public class Memo {
    public final String memo;

    /**
     * Constructs a {@code Memo}.
     *
     * @param memo A memo.
     */
    public Memo(String memo) {
        requireNonNull(memo);
        this.memo = memo;
    }

    @Override
    public String toString() {
        return memo;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Memo // instanceof handles nulls
                && memo.equals(((Memo) other).memo)); // state check
    }

    @Override
    public int hashCode() {
        return memo.hashCode();
    }
}
