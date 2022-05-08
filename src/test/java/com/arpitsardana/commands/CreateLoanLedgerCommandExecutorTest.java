package com.arpitsardana.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import com.arpitsardana.OutputPrinter;
import com.arpitsardana.commands.CreateLoanCommandExecutor;
import com.arpitsardana.model.Command;
import com.arpitsardana.model.emi.strategy.SimpleInterestEMIStrategy;
import com.arpitsardana.service.LedgerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;

public class CreateLoanLedgerCommandExecutorTest {
  private LedgerService ledgerService;
  private OutputPrinter outputPrinter;
  private CreateLoanCommandExecutor createLoanCommandExecutor;

  @Before
  public void setUp() throws Exception {
    ledgerService = new LedgerService();
    outputPrinter = mock(OutputPrinter.class);
    createLoanCommandExecutor =
        new CreateLoanCommandExecutor(ledgerService, outputPrinter);
  }

  @Test
  public void testValidCommand() {
    assertTrue(createLoanCommandExecutor.validate(new Command("LOAN IDIDI Dale 10000 5 4")));
  }

  @Test
  public void testInvalidCommand() {
    assertFalse(createLoanCommandExecutor.validate(new Command("LOAN IDIDI Dale String 5 4")));
    assertFalse(createLoanCommandExecutor.validate(new Command("LOAN IDIDI Dale 10000 5String 4")));
  }

  @Test
  public void testCommandExecution() {

    createLoanCommandExecutor.execute(new Command("LOAN IDIDI Dale 10000 5 4"));
    assertTrue(ledgerService.getLoans().size() == 1);
    assertTrue(ledgerService.getLoans().get(0).getEmiDetails().getTime() == 5);
    assertTrue(ledgerService.getLoans().get(0).getEmiDetails().getAmount() == 10000D);
    assertTrue(ledgerService.getLoans().get(0).getEmiDetails().getInterest() == 4D);
    assertTrue("Dale".equalsIgnoreCase(ledgerService.getLoans().get(0).getLoanUser().getUserName()));
    assertTrue("IDIDI".equalsIgnoreCase(ledgerService.getLoans().get(0).getLoanUser().getBankName()));
  }
}
