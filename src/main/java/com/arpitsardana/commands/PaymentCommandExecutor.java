package com.arpitsardana.commands;

import com.arpitsardana.OutputPrinter;
import com.arpitsardana.exception.InvalidPaymentException;
import com.arpitsardana.model.Loan;
import com.arpitsardana.model.Command;
import com.arpitsardana.service.LedgerService;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Executor to add lumpsum payments.
 */
public class PaymentCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "PAYMENT";

  public PaymentCommandExecutor(
          final LedgerService ledgerService, final OutputPrinter outputPrinter) {
    super(ledgerService, outputPrinter);
  }

  @Override
  public boolean validate(final Command command) {
    return command.getParams().size() == 4;
  }

  @Override
  public void execute(final Command command) {

    Loan loan  = ledgerService.getLoanforUser(command.getParams().get(1), command.getParams().get(0));
    Map<Integer, Double> allPayments = loan.getEmiDetails().getPayments();

    Double totalAmount = loan.getEmiStrategy().getTotalAmount(loan.getEmiDetails().getAmount(), loan.getEmiDetails().getInterest(), loan.getEmiDetails().getTime());
    Double emiAmount = loan.getEmiStrategy().getEmiAmount(totalAmount, loan.getEmiDetails().getTime());

    Double totalAMountPaidTillinEMI = emiAmount * Integer.parseInt(command.getParams().get(3));

    AtomicReference<Double> totalAmountPaidinLumpsum = new AtomicReference<>(0d);

    Integer duration = Integer.parseInt(command.getParams().get(3));

    loan.getEmiDetails().getPayments().forEach((key, value) -> {
      if (duration >= key) {
        totalAmountPaidinLumpsum.updateAndGet(v -> v + value);
      }
    });

    if(totalAMountPaidTillinEMI + totalAmountPaidinLumpsum.get() + Double.parseDouble(command.getParams().get(2)) > totalAmount)
      throw new InvalidPaymentException();

    allPayments.put(Integer.parseInt(command.getParams().get(3)), Double.parseDouble(command.getParams().get(2)));
    loan.getEmiDetails().setPayments(allPayments);
  }
}
