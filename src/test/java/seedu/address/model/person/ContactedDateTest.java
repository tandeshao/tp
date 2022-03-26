package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Contains tests for {@code ContactedDate}.
 */
public class ContactedDateTest {

    private final String validFormatDateString = "01-01-2020";
    private final ContactedDate validContactedDate = new ContactedDate(validFormatDateString);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactedDate(null));
    }

    @Test
    public void constructor_invalidContactedDate_throwsIllegalArgumentException() {
        // Not a date
        assertThrows(IllegalArgumentException.class, () -> new ContactedDate("1"));
    }

    @Test
    public void isValidContactedDate() {
        // invalid null contacted date
        assertFalse(ContactedDate.isValidContactedDate(null));

        // valid empty date
        assertTrue(ContactedDate.isValidContactedDate("")); // empty string

        // invalid spaces only, such input will not occur as user input is trimmed
        assertFalse(ContactedDate.isValidContactedDate(" ")); // spaces only

        // valid dd-MM-yyyy date that is not in the future
        assertTrue(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(0))); // today
        assertTrue(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(-1))); // 1 day ago
        assertTrue(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(-10))); // 10 days ago
        assertTrue(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(-100))); // 100 days ago
        assertTrue(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(-1000))); // 1000 days ago
        assertTrue(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(-10000))); // 10000 days ago
        assertTrue(ContactedDate.isValidContactedDate(validFormatDateString)); // valid date string
        assertTrue(ContactedDate.isValidContactedDate("29-02-2020")); // 29th February 2020, leap year

        // invalid dd-MM-yyyy future date
        assertFalse(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(1))); // 1 day after
        assertFalse(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(10))); // 10 days after
        assertFalse(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(100))); // 100 days after
        assertFalse(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(1000))); // 1000 days after
        assertFalse(ContactedDate.isValidContactedDate(getValidCurrentDateWithDaysOffset(10000))); // 10000 days after

        // invalid inputs that are not dates
        assertFalse(ContactedDate.isValidContactedDate("1")); // just a number
        assertFalse(ContactedDate.isValidContactedDate("111111")); // multiple numbers
        assertFalse(ContactedDate.isValidContactedDate("h")); // an alphabet
        assertFalse(ContactedDate.isValidContactedDate("hello")); // alphabets
        assertFalse(ContactedDate.isValidContactedDate("-")); // just a hyphen
        assertFalse(ContactedDate.isValidContactedDate("/")); // special character '/'
        assertFalse(ContactedDate.isValidContactedDate("~")); // special character '~'

        // invalid dates that do not adhere to the dd-mm-yyyy format
        assertFalse(ContactedDate.isValidContactedDate("01012020")); // no hyphen
        assertFalse(ContactedDate.isValidContactedDate("01 01 2020")); // spaces instead of hyphen
        assertFalse(ContactedDate.isValidContactedDate("01/01/2020")); // '/' instead of hyphen
        assertFalse(ContactedDate.isValidContactedDate("01-Jan-2020")); // month represented as alphabet
        assertFalse(ContactedDate.isValidContactedDate("1-01-2020")); // day single digit
        assertFalse(ContactedDate.isValidContactedDate("01-1-2020")); // month single digit
        assertFalse(ContactedDate.isValidContactedDate("1-1-2020")); // day and month single digit
        assertFalse(ContactedDate.isValidContactedDate("01-01-20")); // year two digit
        assertFalse(ContactedDate.isValidContactedDate("1-1-20")); // day and month single digit, year two digit

        // invalid dd-mm-yyyy dates that do not exist
        assertFalse(ContactedDate.isValidContactedDate("01-13-2020")); // 13th month
        assertFalse(ContactedDate.isValidContactedDate("01-14-2020")); // 14th month
        assertFalse(ContactedDate.isValidContactedDate("01-30-2020")); // 30th month
        assertFalse(ContactedDate.isValidContactedDate("32-01-2020")); // 32nd January 2020
        assertFalse(ContactedDate.isValidContactedDate("32-01-2020")); // 50th January 2020
        assertFalse(ContactedDate.isValidContactedDate("30-02-2019")); // 30th February 2020
        assertFalse(ContactedDate.isValidContactedDate("29-02-2019")); // 29th February 2019, not a leap year
    }

    @Test
    public void isEmpty_notEmptyValidContactedDate_returnsFalse() {
        assertFalse(validContactedDate.isEmpty());
    }

    @Test
    public void isEmpty_emptyValidContactedDate_returnsTrue() {
        assertTrue(ContactedDate.EMPTY_CONTACTED_DATE.isEmpty());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(validContactedDate.equals(validContactedDate));

        // same values -> returns true
        ContactedDate validContactedDateCopy = new ContactedDate(validContactedDate.contactedDate);
        assertTrue(validContactedDate.equals(validContactedDateCopy));

        // different types -> returns false
        assertFalse(validContactedDate.equals(1));

        // null -> returns false
        assertFalse(validContactedDate.equals(null));

        // different memo -> returns false
        ContactedDate differentValidContactedDate = new ContactedDate("01-02-2020");
        assertFalse(validContactedDate.equals(differentValidContactedDate));
    }

    /**
     * Returns a valid dd-MM-yyyy date which is the current date offset by a specified number of days.
     *
     * @param daysOffset Days offset from the current date.
     *
     * @return Valid dd-MM-yyyy date that is the current date offset by a specified number of days.
     */
    private String getValidCurrentDateWithDaysOffset(int daysOffset) {
        LocalDate date = LocalDate.now();

        if (daysOffset >= 0) {
            date = date.plusDays(daysOffset);
        } else {
            date = date.minusDays(Math.abs(daysOffset));
        }

        return date.format(DateTimeFormatter.ofPattern(ContactedDate.DATE_FORMAT));
    }
}
