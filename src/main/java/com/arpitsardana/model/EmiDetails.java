package com.arpitsardana.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Model object to represent EMI Details.
 */
public class EmiDetails {
  private final Double amount;
  private final Integer time;
  private final Double interest;
  private Map<Integer, Double> payments;

  public Double getAmount() {
    return amount;
  }

  public Integer getTime() {
    return time;
  }

  public Double getInterest() {
    return interest;
  }

  public Map<Integer, Double> getPayments() {
    return payments;
  }

  public void setPayments(Map<Integer, Double> payments) {
    this.payments = payments;
  }

  public EmiDetails(final Double amount, final Integer time, Double interest) {
    this.amount = amount;
    this.time = time;
    this.interest = interest;
    this.payments = new HashMap<>();
  }
}
