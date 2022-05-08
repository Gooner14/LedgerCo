package com.arpitsardana.model.emi.strategy;

/**
 * Strategy which fetches EMI data using Simple Interest
 */
public class SimpleInterestEMIStrategy implements EMIStrategy {
  @Override
  public Double getTotalAmount(Double principal, Double rateOfInterest, Integer duration) {
    return Math.ceil(principal + (principal * rateOfInterest * duration) / 100 );
  }

  @Override
  public Double getEmiAmount(Double totalSum, Integer duration) {
    return Math.ceil(totalSum / duration / 12 );
  }
}
