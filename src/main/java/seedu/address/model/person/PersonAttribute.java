package seedu.address.model.person;

/**
 * Represents the parent class of all person attributes.
 */
public abstract class PersonAttribute {

    public abstract String toString();

    public abstract int hashCode();

    public abstract boolean equals(Object other);
}
