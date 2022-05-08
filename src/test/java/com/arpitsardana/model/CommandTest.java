package com.arpitsardana.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.arpitsardana.exception.InvalidCommandException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class CommandTest {

  @Test
  public void testCommandParsingFromInput() {
    validateCommandParsing("LOAN IDIDI Dale 10000 5 4", "LOAN", Arrays.asList("IDIDI", "Dale", "10000", "5", "4"));
    validateCommandParsing("BALANCE IDIDI Dale 5 ", "BALANCE", Arrays.asList("IDIDI", "Dale", "5"));
    validateCommandParsing("PAYMENT IDIDI Dale 1000 5", "PAYMENT", Arrays.asList("IDIDI", "Dale", "1000", "5"));
  }

  @Test(expected = InvalidCommandException.class)
  public void testCommandParsingFromInputHavingOnlySpaces() {
    Command command = new Command("   ");
  }

  @Test(expected = InvalidCommandException.class)
  public void testCommandParsingFromEmptyInput() {
    Command command = new Command("");
  }

  /**
   * Helper method to validate command parsing.
   *
   * @param input Input line.
   * @param expectedCommandName Expected command name from input.
   * @param expectedParams Expected command params from inout.
   */
  private void validateCommandParsing(
      final String input, final String expectedCommandName, final List expectedParams) {
    Command command = new Command(input);
    assertNotNull(command);
    assertEquals(expectedCommandName, command.getCommandName());
    assertEquals(expectedParams, command.getParams());
  }
}
