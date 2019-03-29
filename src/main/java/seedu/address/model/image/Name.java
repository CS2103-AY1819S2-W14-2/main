/* @@author Carrein */
package seedu.address.model.image;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Image's name in FomoFoto's Album.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * Ensures the name of the file has to be valid and of an image type.
     */
    public static final String VALIDATION_REGEX = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|tiff|tif))$)";

    public final String name;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && name.equals(((Name) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
