package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MemoTest {

    @Test
    public void equals() {
        Memo memo = new Memo("Hello");

        // same object -> returns true
        assertTrue(memo.equals(memo));

        // same values -> returns true
        Memo memoCopy = new Memo(memo.memo);
        assertTrue(memo.equals(memoCopy));

        // different types -> returns false
        assertFalse(memo.equals(1));

        // null -> returns false
        assertFalse(memo.equals(null));

        // different memo -> returns false
        Memo differentMemo = new Memo("Bye");
        assertFalse(memo.equals(differentMemo));
    }
}
