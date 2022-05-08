package com.arpitsardana.commands;

import com.arpitsardana.OutputPrinter;
import com.arpitsardana.exception.InvalidCommandException;
import com.arpitsardana.model.Command;
import com.arpitsardana.service.LedgerService;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory to get correct {@link CommandExecutor} command.
 */
public class CommandExecutorFactory {
  private Map<String, CommandExecutor> commands = new HashMap<>();

  public CommandExecutorFactory(final LedgerService ledgerService) {
    final OutputPrinter outputPrinter = new OutputPrinter();
    commands.put(
        CreateLoanCommandExecutor.COMMAND_NAME,
        new CreateLoanCommandExecutor(ledgerService, outputPrinter));
    commands.put(
        BalanceCommandExecutor.COMMAND_NAME,
        new BalanceCommandExecutor(ledgerService, outputPrinter));
    commands.put(
            PaymentCommandExecutor.COMMAND_NAME,
            new PaymentCommandExecutor(ledgerService, outputPrinter));
    commands.put(
            StatusCommandExecutor.COMMAND_NAME,
            new StatusCommandExecutor(ledgerService, outputPrinter));
  }

  /**
   * Gets {@link CommandExecutor}. Uses name to find the corresponding command.
   *
   * @param command Command for which executor has to be fetched.
   * @return Command executor.
   */
  public CommandExecutor getCommandExecutor(final Command command) {
    final CommandExecutor commandExecutor = commands.get(command.getCommandName());
    if (commandExecutor == null) {
      throw new InvalidCommandException();
    }
    return commandExecutor;
  }
}
