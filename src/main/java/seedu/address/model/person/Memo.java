package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's memo in the address book.
 * Guarantees: immutable; is always valid
 */
public class Memo {

    public static final String MESSAGE_CONSTRAINTS = "Memo should not contain /";

    /** Every character is allowed except /. */
    public static final String VALIDATION_REGEX = "[^/]*";

    public final String memo;

    /**
     * Constructs a {@code Memo}.
     *
     * @param memo A memo.
     */
    public Memo(String memo) {
        requireNonNull(memo);
        checkArgument(isValidMemo(memo), MESSAGE_CONSTRAINTS);
        this.memo = memo;
    }

    /**
     * Returns true if a given string is a valid memo.
     */
    public static boolean isValidMemo(String memo) {
        return memo.matches(VALIDATION_REGEX);
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
