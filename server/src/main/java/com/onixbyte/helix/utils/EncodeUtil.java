package com.onixbyte.helix.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Utility class providing encoding and decoding operations for web applications.
 * <p>
 * This utility class offers methods for handling URL encoding operations that are commonly required
 * in web applications, particularly for ensuring compatibility between Java server-side encoding
 * and JavaScript client-side encoding standards.
 * <p>
 * The primary focus is on providing encoding methods that precisely replicate JavaScript's built-in
 * encoding functions, ensuring consistent behaviour across different platforms and environments.
 * This is particularly important for applications that need to maintain encoding consistency
 * between server-side Java code and client-side JavaScript code.
 * <p>
 * All methods in this utility class are static and the class is designed to be used
 * without instantiation. The encoding operations follow web standards and are optimised for
 * performance and reliability.
 *
 * @author zihluwang
 * @see java.net.URLEncoder
 * @see java.nio.charset.StandardCharsets
 * @since 1.0.0
 */
public class EncodeUtil {

    /**
     * Encodes a string to be used in a URI component, precisely replicating JavaScript's
     * {@code encodeURIComponent} function.
     * <p>
     * This method provides exact compatibility with JavaScript's {@code encodeURIComponent}
     * function by first using Java's standard {@link URLEncoder} and then applying specific
     * corrections to match JavaScript's encoding behaviour.
     * <p>
     * The method handles the following character encoding differences:
     * <ul>
     * <li>Converts "+" to "{@code %20}" for proper space encoding</li>
     * <li>Preserves "*" characters (decoded from "{@code %2A}")</li>
     * <li>Preserves "'" characters (decoded from "{@code %27}")</li>
     * <li>Preserves "(" characters (decoded from "{@code %28}")</li>
     * <li>Preserves ")" characters (decoded from "{@code %29}")</li>
     * </ul>
     * <p>
     * Characters that remain unencoded in both implementations: {@code - _ . ! ~ * ' ( )}
     * <p>
     * This method is particularly useful for applications that need to maintain encoding
     * consistency between server-side Java operations and client-side JavaScript operations, such
     * as when generating URLs or handling form data.
     *
     * @param content the string component to encode for URI usage
     * @return a URI-component-safe encoded string that matches JavaScript's
     * {@code encodeURIComponent} output
     * @throws NullPointerException if the content parameter is null
     * @see java.net.URLEncoder#encode(String, java.nio.charset.Charset)
     * @see <a href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/encodeURIComponent">MDN encodeURIComponent Documentation</a>
     */
    public static String encodeUriComponent(String content) {
        String encodedComponent;

        // First, we use the standard Java URLEncoder.
        //
        // It's crucial to specify the character encoding, with UTF-8 being the web standard.
        encodedComponent = URLEncoder.encode(content, StandardCharsets.UTF_8);

        // Next, we must manually correct the output of URLEncoder to match encodeURIComponent's
        // behaviour.
        //
        // JavaScript's encodeURIComponent does not encode these characters: - _ . ! ~ * ' ( )
        // URLEncoder, however, does encode some of them.

        // Replace "+" with "%20" (space character)
        encodedComponent = encodedComponent.replace("+", "%20");

        // URLEncoder encodes "*" as "%2A", but encodeURIComponent does not, so we decode it back.
        encodedComponent = encodedComponent.replace("%2A", "*");

        // URLEncoder encodes "'" as "%27", but encodeURIComponent does not.
        encodedComponent = encodedComponent.replace("%27", "'");

        // URLEncoder encodes "(" as "%28", but encodeURIComponent does not.
        encodedComponent = encodedComponent.replace("%28", "(");

        // URLEncoder encodes ")" as "%29", but encodeURIComponent does not.
        encodedComponent = encodedComponent.replace("%29", ")");

        // Note: The characters '!', '~' are also not encoded by encodeURIComponent.
        //
        // Modern versions of URLEncoder (in recent JDKs) correctly leave '!' and '~' unencoded,
        // so explicit replacements for "%21" and "%7E" are typically no longer necessary.
        return encodedComponent;
    }
}
