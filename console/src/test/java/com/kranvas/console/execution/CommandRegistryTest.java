package com.kranvas.console.execution;

import com.kranvas.console.commands.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CommandRegistryTest {
    private CommandRegistry sut = new CommandRegistry();

    @Mock
    private Command command;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void null_command_is_rejected() {
        verifyIllegarlArugmentException(null, "null");
    }

    @Test
    void command_without_short_name_is_rejected() {
        when(command.getShortName()).thenReturn(null);
        verifyIllegarlArugmentException(command, "null");
    }

    @Test
    void command_with_blank_short_name_is_rejected() {
        when(command.getShortName()).thenReturn("  ");
        verifyIllegarlArugmentException(command, "blank");
    }

    @Test
    void duplicate_short_name_is_rejected() {
        when(command.getShortName()).thenReturn("X");
        sut.register(command);
        verifyIllegarlArugmentException(command, "already taken");
    }

    @Test
    void test_find_command() {
        when(command.getShortName()).thenReturn("X");
        sut.register(command);
        assertEquals(command, sut.findCommand("X"));
    }

    @Test
    void returns_null_for_unknown_command() {
        assertNull(sut.findCommand("X"));
    }

    @Test
    void generate_usage_includes_all_commands() {
        registerCommand("A", "A command");
        registerCommand("B", "B command");

        String usage = sut.getUsage();
        assertTrue(usage.contains("A command"));
        assertTrue(usage.contains("B command"));
    }

    private void registerCommand(String a, String t) {
        Command command = mock(Command.class);
        when(command.getShortName()).thenReturn(a);
        when(command.getDescription()).thenReturn(t);
        sut.register(command);
    }

    private void verifyIllegarlArugmentException(Command command, String message) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> sut.register(command));
        assertTrue(exception.getMessage().contains(message));
    }

}