package com.arpitsardana.commands;

import com.arpitsardana.OutputPrinter;
import com.arpitsardana.model.Command;
import com.arpitsardana.model.EmiDetails;
import com.arpitsardana.model.Loan;
import com.arpitsardana.model.User;
import com.arpitsardana.service.LedgerService;
import com.arpitsardana.validator.IntegerValidator;
import java.util.List;

/**
 * Creates a loan account.
 */
public class CreateLoanCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "LOAN";

  public CreateLoanCommandExecutor(
          final LedgerService ledgerService, final OutputPrinter outputPrinter) {
    super(ledgerService, outputPrinter);
  }

  @Override
  public boolean validate(final Command command) {
    final List<String> params = command.getParams();
    if (params.size() != 5) {
      return false;
    }
    return IntegerValidator.isInteger(params.get(2)) &&
            IntegerValidator.isInteger(params.get(3)) &&
            IntegerValidator.isInteger(params.get(4));
  }

  @Override
  public void execute(final Command command) {

    List<Loan> loans = ledgerService.getLoans();
    final User user = new User(command.getParams().get(0), command.getParams().get(1));

    final EmiDetails emiDetails = new EmiDetails(Double.parseDouble(command.getParams().get(2)),
            Integer.parseInt(command.getParams().get(3)),
            Double.parseDouble(command.getParams().get(4)));

    ledgerService.createLoan(user, emiDetails);
  }
}
