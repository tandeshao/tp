package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMO_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.MemoUtil.MAXIMUM_MEMO_STRING;
import static seedu.address.testutil.MemoUtil.ONE_LESS_THAN_MAXIMUM_MEMO_STRING;
import static seedu.address.testutil.MemoUtil.ONE_MORE_THAN_MAXIMUM_MEMO_STRING;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for {@code Memo}.
 */
public class MemoTest {

    private final Memo validMemoAmy = new Memo(VALID_MEMO_AMY);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Memo(null));
    }

    @Test
    public void constructor_invalidMemo_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Memo(ONE_MORE_THAN_MAXIMUM_MEMO_STRING));
    }

    @Test
    public void isValidMemo_validShortLengthMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo(VALID_MEMO_AMY));
    }

    @Test
    public void isValidMemo_validOneLessThanMaximumLengthMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo(ONE_LESS_THAN_MAXIMUM_MEMO_STRING));
    }

    @Test
    public void isValidMemo_validMaximumLengthMemo_returnsTrue() {
        assertTrue(Memo.isValidMemo(MAXIMUM_MEMO_STRING));
    }

    @Test
    public void isValidMemo_invalidOneMoreThanMaximumLengthMemo_returnsFalse() {
        assertFalse(Memo.isValidMemo(ONE_MORE_THAN_MAXIMUM_MEMO_STRING));
    }

    @Test
    public void isEmpty_notEmptyValidMemo_returnsFalse() {
        assertFalse(validMemoAmy.isEmpty());
    }

    @Test
    public void isEmpty_emptyValidMemo_returnsTrue() {
        assertTrue(Memo.EMPTY_MEMO.isEmpty());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(validMemoAmy.equals(validMemoAmy));

        // same values -> returns true
        Memo validMemoCopy = new Memo(validMemoAmy.memo);
        assertTrue(validMemoAmy.equals(validMemoCopy));

        // different capitalization -> returns true
        Memo validMemoAmyAllCaps = new Memo(VALID_MEMO_AMY);
        assertTrue(validMemoAmy.equals(validMemoAmyAllCaps));

        // different types -> returns false
        assertFalse(validMemoAmy.equals(1));

        // null -> returns false
        assertFalse(validMemoAmy.equals(null));

        // different memo -> returns false
        Memo differentMemo = new Memo(VALID_MEMO_BOB);
        assertFalse(validMemoAmy.equals(differentMemo));
    }
}
