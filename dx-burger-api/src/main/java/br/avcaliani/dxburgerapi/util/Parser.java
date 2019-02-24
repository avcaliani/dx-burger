package br.avcaliani.dxburgerapi.util;

/**
 * Parser Util.
 *
 * @author Anthony Vilarim Caliani
 * @since 19.2.0
 */
public class Parser {

    /**
     * Private Constructor.
     */
    private Parser() { }

    /**
     * Remove all non numeric characters.
     *
     * @param value String Value.
     * @return String.
     */
    public static String replaceAllNonNumeric(String value) {
        if (value == null)
            return null;
        return value.replaceAll("[^0-9]+", "");
    }
}
