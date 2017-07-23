package com.kranvas.console.commands.utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListParserTest {

    @Test
    void null_returns_null() {
        assertNull(IntegerListParser.parse(null));
    }

    @Test
    void only_white_spaces_returns_null() {
        assertNull(IntegerListParser.parse("   "));
    }

    @Test
    void integers_with_multiple_spaces_works() {
        List<Integer> result = IntegerListParser.parse(" 1  2  ");
        assertEquals(2, result.size());
        assertEquals(1, (int)result.get(0));
        assertEquals(2, (int)result.get(1));
    }

    @Test
    void able_to_handle_non_integer_input() {
        List<Integer> result = IntegerListParser.parse(" 1  a 2  ");
        assertEquals(2, result.size());
        assertEquals(1, (int)result.get(0));
        assertEquals(2, (int)result.get(1));
    }
}