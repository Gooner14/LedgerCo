package com.arpitsardana;

/**
 * Printer to help in printing the output back to the user.
 */
public class OutputPrinter {

  public void invalidFile() {
    printWithNewLine("Invalid file given.");
  }

  public void printWithNewLine(final String msg) {
    System.out.println(msg);
  }
}
