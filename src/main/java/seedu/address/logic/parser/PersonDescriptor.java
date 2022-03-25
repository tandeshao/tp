package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.LIST_OF_PREFIX;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javafx.util.Pair;

/**
 *  A class that stores the description of a person.
 */
public class PersonDescriptor {
    private final HashMap<Prefix, String> descriptor;
    private final String userInput;

    /**
     * Constructor for PersonDescriptor.
     * @param input user supplied argument that contains both the prefix and their corresponding description.
     */
    public PersonDescriptor(String input) {
        userInput = input;
        descriptor = new HashMap<>();
        populateDescriptor();
    }

    /**
     * Returns the list of prefixes in the descriptor.
     * @return prefixes that are in the user input.
     */
    public List<Prefix> getAllAvailablePrefix() {
        return new ArrayList<>(descriptor.keySet());
    }

    /**
     * Finds out if the descriptor is empty. Only way for descriptor to be empty
     * is when there are no valid prefix in the user input.
     * @return true if descriptor is empty, false otherwise.
     */
    public boolean isEmpty() {
        return descriptor.isEmpty();
    }

    /**
     * Gets the corresponding description based on the prefix.
     * @param p prefix to matched with.
     * @return description of the prefix.
     */
    public String getDescription(Prefix p) {
        return descriptor.get(p);
    }

    /**
     * Extracts the argument of each search parameter and
     * put it in an entry in the hashmap that matches to its corresponding
     * prefix.
     */
    private void populateDescriptor() {
        List<Pair<Prefix, Integer>> prefixPositions = getPrefixPositions();
        for (int i = 0; i < prefixPositions.size(); i++) {
            addToDescriptor(prefixPositions, i);
        }
    }

    /**
     * Adds the prefix and its corresponding description into the descriptor.
     * @param prefixPositions list of prefix position(s) in the user input.
     * @param listIndex indicates which prefix position is looked at in this current iteration.
     */
    private void addToDescriptor(List<Pair<Prefix, Integer>> prefixPositions, int listIndex) {
        if (listIndex + 1 < prefixPositions.size()) {
            Prefix prefix = prefixPositions.get(listIndex).getKey();
            int firstPrefixPos = prefixPositions.get(listIndex).getValue();
            int secondPrefixPos = prefixPositions.get(listIndex + 1).getValue();
            String description = extractDescription(firstPrefixPos,
                    secondPrefixPos);
            descriptor.put(prefix, description);
        } else {
            Prefix prefix = prefixPositions.get(listIndex).getKey();
            int prefixPos = prefixPositions.get(listIndex).getValue();
            String trimmedDescription = extractDescription(prefixPos, userInput.length());
            descriptor.put(prefix, trimmedDescription);
        }
    }

    /**
     * Looks at the user supplied input and finds the index
     * of the possible prefixes that may be in the input string.
     * @return list of sorted prefix positions in ascending order.
     */
    private List<Pair<Prefix, Integer>> getPrefixPositions() {
        List<Pair<Prefix, Integer>> result = new ArrayList<>();
        for (Prefix prefix : LIST_OF_PREFIX) {
            String searchString = prefix.getPrefix();
            int indexOfString = userInput.indexOf(searchString);
            if (indexOfString != -1) {
                // +1 as an offset for whitespace
                Pair<Prefix, Integer> pair = new Pair<>(prefix, indexOfString);
                result.add(pair);
            }
        }
        result.sort(Comparator.comparing(Pair::getValue));
        return result;
    }

    /**
     * Extractor that returns the user specified argument
     * based off the position of two prefixes.
     * @return argument of the prefix in the user input.
     */
    private String extractDescription(int prefixPos, int endIndex) {
        // +2 to offset the prefix and trims off leading and trailing whitespaces after
        return userInput.substring(prefixPos + 2, endIndex).trim();
    }

    /**
     * Equal method to check if two {@link PersonDescriptor} are equal.
     *
     * @param other other PersonDescriptor object.
     * @return true if both {@link PersonDescriptor} object are the same, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonDescriptor // instanceof handles nulls
                && descriptor.equals(((PersonDescriptor) other).descriptor)); // state check
    }
}
