package com.arpitsardana;

import static org.junit.Assert.assertEquals;

import com.arpitsardana.exception.InvalidModeException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeektrustTest {
  private InputStream sysInBackup;
  private PrintStream sysOutBackup;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  @Before
  public void setUp() throws Exception {
    sysInBackup = System.in; // backup System.in to restore it later
    sysOutBackup = System.out; // backup System.out to restore it later
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void tearDown() throws Exception {
    System.setIn(sysInBackup);
    System.setOut(sysOutBackup);
  }

  @Test
  public void testFileMode() throws IOException {
    final String expectedOutput =
        "IDIDI Dale 1326 9\n"
            + "IDIDI Dale 3652 4\n"
            + "UON Shelly 15856 3\n"
            + "MBI Harry 9044 10\n";
    Geektrust.main(new String[] {"file_input.txt"});
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test
  public void testFileModeWithInvalidFile() throws IOException {
    final String expectedOutput = "Invalid file given.\n";
    Geektrust.main(new String[] {"some_random_file.txt"});
    assertEquals(expectedOutput, outContent.toString());
  }

  @Test(expected = InvalidModeException.class)
  public void testInvalidMode() throws IOException {
    Geektrust.main(new String[] {"file_input.txt", "some-other-input"});
  }
}
