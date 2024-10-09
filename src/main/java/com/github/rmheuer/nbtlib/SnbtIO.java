package com.github.rmheuer.nbtlib;

import java.util.regex.Pattern;

public final class SnbtIO {
    private static final Pattern ALLOWED_UNQUOTED = Pattern.compile("[A-Za-z_][0-9A-Za-z_.+-]*");

    public static String quoteStringIfNeeded(String str) {
        if (ALLOWED_UNQUOTED.matcher(str).matches()) {
            return str;
        }

        String escapedBackslash = str.replace("\\", "\\\\");
        String escapedQuote = escapedBackslash.replace("\"", "\\\"");

        return '"' + escapedQuote + '"';
    }
}
