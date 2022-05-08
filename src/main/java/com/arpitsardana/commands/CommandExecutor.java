package com.arpitsardana.commands;

import com.arpitsardana.OutputPrinter;
import com.arpitsardana.model.Command;
import com.arpitsardana.service.LedgerService;

/**
 * Command executor interface.
 */
public abstract class CommandExecutor {
  protected LedgerService ledgerService;
  protected OutputPrinter outputPrinter;

  public CommandExecutor(final LedgerService ledgerService,
      final OutputPrinter outputPrinter) {
    this.ledgerService = ledgerService;
    this.outputPrinter = outputPrinter;
  }

  /**
   * Validates the command to be run.
   *
   * @param command Command to be validated.
   * @return Boolean indicating whether command is valid or not.
   */
  public abstract boolean validate(Command command);

  /**
   * Executes the command.
   *
   * @param command Command to be executed.
   */
  public abstract void execute(Command command);
}
