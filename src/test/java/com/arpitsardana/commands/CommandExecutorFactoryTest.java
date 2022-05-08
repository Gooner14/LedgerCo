package com.arpitsardana.commands;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.arpitsardana.exception.InvalidCommandException;
import com.arpitsardana.model.Command;
import com.arpitsardana.service.LedgerService;
import org.junit.Before;
import org.junit.Test;

public class CommandExecutorFactoryTest {

  private CommandExecutorFactory factory;

  @Before
  public void setUp() throws Exception {
    final LedgerService ledgerService = mock(LedgerService.class);
    factory = new CommandExecutorFactory(ledgerService);
  }

  @Test
  public void testFetchingExecutorForValidBalanceCommand() {
    final CommandExecutor commandExecutor = factory.getCommandExecutor(new Command("BALANCE IDIDI Dale 40"));
    assertNotNull(commandExecutor);
    assertTrue(commandExecutor instanceof BalanceCommandExecutor);
  }

    @Test
    public void testFetchingExecutorForValidLoanCommand() {
        final CommandExecutor commandExecutor = factory.getCommandExecutor(new Command("LOAN UON Shelly 15000 2 90"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof CreateLoanCommandExecutor);
    }

    @Test
    public void testFetchingExecutorForValidPaymentCommand() {
        final CommandExecutor commandExecutor = factory.getCommandExecutor(new Command("PAYMENT IDIDI Dale 1000 5"));
        assertNotNull(commandExecutor);
        assertTrue(commandExecutor instanceof PaymentCommandExecutor);
    }

  @Test(expected = InvalidCommandException.class)
  public void testFetchingExecutorForInvalidCommand() {
    factory.getCommandExecutor(new Command("RANDOM String 123"));
  }
}
