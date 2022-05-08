package com.arpitsardana.commands;

import com.arpitsardana.OutputPrinter;
import com.arpitsardana.exception.InvalidPaymentException;
import com.arpitsardana.model.Command;
import com.arpitsardana.model.Loan;
import com.arpitsardana.service.LedgerService;
import com.arpitsardana.validator.IntegerValidator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Executor to find balance of a user/loan account.
 */
public class BalanceCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "BALANCE";

  public BalanceCommandExecutor(final LedgerService ledgerService,
                                final OutputPrinter outputPrinter) {
    super(ledgerService, outputPrinter);
  }

  @Override
  public boolean validate(final Command command) {
    final List<String> params = command.getParams();
    if (params.size() != 3) {
      return false;
    }
    return IntegerValidator.isInteger(params.get(2));
  }

  @Override
  public void execute(final Command command) {

    final String bankName = (command.getParams().get(0));
    final String userName = (command.getParams().get(1));

    Loan loan  = ledgerService.getLoanforUser(userName, bankName);

    Double totalAmount = loan.getEmiStrategy().getTotalAmount(loan.getEmiDetails().getAmount(), loan.getEmiDetails().getInterest(), loan.getEmiDetails().getTime());
    Double emiAmount = loan.getEmiStrategy().getEmiAmount(totalAmount, loan.getEmiDetails().getTime());

    Double totalAMountPaidTillinEMI = emiAmount * Integer.parseInt(command.getParams().get(2));
    AtomicReference<Double> totalAmountPaidinLumpsum = new AtomicReference<>(0d);

    Integer duration = Integer.parseInt(command.getParams().get(2));

    loan.getEmiDetails().getPayments().forEach((key, value) -> {
      if (duration >= key) {
          totalAmountPaidinLumpsum.updateAndGet(v -> v + value);
      }
    });

    Double totalAmountPaid = totalAMountPaidTillinEMI + totalAmountPaidinLumpsum.get();

    if(totalAmountPaid > totalAmount)
      throw new InvalidPaymentException();

    Double emisLeft = Math.ceil((totalAmount - totalAmountPaid) / emiAmount);

    String output = String.format("%s %s %d %d", bankName, userName, totalAmountPaid.intValue(), emisLeft.intValue());

    outputPrinter.printWithNewLine(output);

  }
}
