package com.kranvas.console.commands.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Parses a delimited string of numbers into a list
 */
public class IntegerListParser {
    public static List<Integer> parse(String intListAsString) {
        if (intListAsString == null || intListAsString.length() == 0)
            return null;

        return Arrays
                .stream(intListAsString.split("\\s+"))
                .map(IntegerListParser::parseInteger)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static Integer parseInteger(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
