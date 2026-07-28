package kals.com.core.utility;

/**
 * Utility class for common String operations.
 */
public class StringUtil {

    /**
     * Checks if a string is null, empty, or contains only whitespace.
     * @param value the string to check
     * @return true if the string is blank, false otherwise
     */
    public static boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

}
