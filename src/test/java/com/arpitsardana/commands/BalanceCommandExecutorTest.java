package com.arpitsardana.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.arpitsardana.OutputPrinter;
import com.arpitsardana.model.Command;
import com.arpitsardana.model.EmiDetails;
import com.arpitsardana.model.Loan;
import com.arpitsardana.model.User;
import com.arpitsardana.model.emi.strategy.SimpleInterestEMIStrategy;
import com.arpitsardana.service.LedgerService;
import org.junit.Before;
import org.junit.Test;

public class BalanceCommandExecutorTest {

  private LedgerService ledgerService;
  private OutputPrinter outputPrinter;
  private BalanceCommandExecutor balanceCommandExecutor;

  @Before
  public void setUp() throws Exception {
    ledgerService = mock(LedgerService.class);
    outputPrinter = mock(OutputPrinter.class);
    balanceCommandExecutor = new BalanceCommandExecutor(ledgerService, outputPrinter);
  }

  @Test
  public void testValidCommand() {
      assertTrue(balanceCommandExecutor.validate(new Command("BALANCE IDIDDI Dale 123")));
  }

  @Test
  public void testInvalidCommand() {
      assertFalse(balanceCommandExecutor.validate(new Command("BALANCE IDIDI Dale String")));
      assertFalse(balanceCommandExecutor.validate(new Command("BALANCE Dale 20")));
      assertFalse(balanceCommandExecutor.validate(new Command("BALANCE 12 123 Dale")));
      assertFalse(balanceCommandExecutor.validate(new Command("BALANCE 123")));
  }

  @Test
  public void testOutput() {

      Loan loan = new Loan(new User("IDIDI", "Dale"), new EmiDetails(10000D,5, 4D), new SimpleInterestEMIStrategy());
      when(ledgerService.getLoanforUser("Dale","IDIDI")).thenReturn(loan);

      balanceCommandExecutor.execute(new Command("BALANCE IDIDI Dale 5"));
      balanceCommandExecutor.execute(new Command("BALANCE IDIDI Dale 40"));

      verify(outputPrinter).printWithNewLine("IDIDI Dale 1000 55");
      verify(outputPrinter).printWithNewLine("IDIDI Dale 8000 20");
  }
}
