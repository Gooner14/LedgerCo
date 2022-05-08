package com.arpitsardana.commands;

import com.arpitsardana.OutputPrinter;
import com.arpitsardana.model.Command;
import com.arpitsardana.service.LedgerService;

/**
 * Executor to fetch status of all users and their emi data.
 */
public class StatusCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "STATUS";

  public StatusCommandExecutor(final LedgerService ledgerService,
      final OutputPrinter outputPrinter) {
    super(ledgerService, outputPrinter);
  }

  @Override
  public boolean validate(Command command) {
    return false;
  }

  @Override
  public void execute(Command command) {

  }

}
