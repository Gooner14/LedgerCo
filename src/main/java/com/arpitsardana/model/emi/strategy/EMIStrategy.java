package com.arpitsardana.model.emi.strategy;

/**
 * Strategy which will be used to decide the total amount to be paid back and the EMI.
 */
public interface EMIStrategy {

  public Double getTotalAmount(Double principal, Double rateOfInterest, Integer duration);

  public Double getEmiAmount(Double totalSum, Integer duration);

}
