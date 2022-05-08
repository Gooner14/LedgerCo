package com.arpitsardana;

import com.arpitsardana.commands.CommandExecutorFactory;
import com.arpitsardana.exception.InvalidModeException;
import com.arpitsardana.mode.FileMode;
import com.arpitsardana.service.LedgerService;
import java.io.IOException;

public class Geektrust {
  public static void main(final String[] args) throws IOException {
    final OutputPrinter outputPrinter = new OutputPrinter();
    final LedgerService ledgerService = new LedgerService();
    final CommandExecutorFactory commandExecutorFactory =
        new CommandExecutorFactory(ledgerService);

    if (isFileInputMode(args)) {
      new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
    } else {
      throw new InvalidModeException();
    }
  }

  /**
   * Checks whether the program is running using file input mode.
   *
   * @param args Command line arguments.
   * @return Boolean indicating whether in file input mode.
   */
  private static boolean isFileInputMode(final String[] args) {
    return args.length == 1;
  }

}
