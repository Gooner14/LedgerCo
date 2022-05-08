package com.arpitsardana.model.emi.strategy;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleInterestEMIStrategyTest {
    private SimpleInterestEMIStrategy simpleInterestEMIStrategy =
            new SimpleInterestEMIStrategy();

    @Test
    public void testValidStrategyExecution() {
        assertTrue(simpleInterestEMIStrategy.getTotalAmount(1000D,5D, 4) == 1200D);
        assertTrue(simpleInterestEMIStrategy.getEmiAmount(1000D,4) == 21D);
        assertFalse(simpleInterestEMIStrategy.getTotalAmount(1000D,5D, 4) == 20D);
    }
}
