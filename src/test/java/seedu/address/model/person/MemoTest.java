package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.MemoUtil.LONGER_THAN_MAXIMUM_MEMO_STRING;
import static seedu.address.testutil.MemoUtil.MAXIMUM_MEMO_STRING;
import static seedu.address.testutil.MemoUtil.SHORT_LENGTH_STRING;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for {@code Memo}.
 */
public class MemoTest {

    private final Memo validShortMemo = new Memo(SHORT_LENGTH_STRING);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Memo(null));
    }

    @Test
    public void constructor_invalidMemo_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Memo(LONGER_THAN_MAXIMUM_MEMO_STRING));
    }

    @Test
    public void isValidMemo_validShortLengthMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo(SHORT_LENGTH_STRING));
    }

    @Test
    public void isValidMemo_validMaximumLengthMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo(MAXIMUM_MEMO_STRING));
    }

    @Test
    public void isValidMemo_invalidLongerThanMaximumLengthMemo_returnsFalse() {
        assertFalse(Memo.isValidMemo(LONGER_THAN_MAXIMUM_MEMO_STRING));
    }

    @Test
    public void isEmpty_notEmptyValidMemo_returnsFalse() {
        assertFalse(validShortMemo.isEmpty());
    }

    @Test
    public void isEmpty_emptyValidMemo_returnsTrue() {
        assertTrue(Memo.EMPTY_MEMO.isEmpty());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(validShortMemo.equals(validShortMemo));

        // same values -> returns true
        Memo memoCopy = new Memo(validShortMemo.memo);
        assertTrue(validShortMemo.equals(memoCopy));

        // different types -> returns false
        assertFalse(validShortMemo.equals(1));

        // null -> returns false
        assertFalse(validShortMemo.equals(null));

        // different memo -> returns false
        Memo differentMemo = new Memo("Bye");
        assertFalse(validShortMemo.equals(differentMemo));
    }
}
