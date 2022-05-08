package com.arpitsardana.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.arpitsardana.OutputPrinter;
import com.arpitsardana.model.Command;
import com.arpitsardana.service.LedgerService;
import org.junit.Before;
import org.junit.Test;

public class PaymentExecutorFactoryTest {
    private LedgerService ledgerService;
    private OutputPrinter outputPrinter;
    private PaymentCommandExecutor paymentCommandExecutor;

    @Before
    public void setUp() throws Exception {
        ledgerService = new LedgerService();
        outputPrinter = mock(OutputPrinter.class);
        paymentCommandExecutor =
                new PaymentCommandExecutor(ledgerService, outputPrinter);
    }

    @Test
    public void testValidCommand() {
        assertTrue(paymentCommandExecutor.validate(new Command("PAYMENT UON Shelly 7000 12")));
    }

    @Test
    public void testInvalidCommand() {
        assertFalse(paymentCommandExecutor.validate(new Command("PAYMENT UON Shelly 12")));
        assertFalse(paymentCommandExecutor.validate(new Command("PAYMENT UON Shelly String")));
    }
}
