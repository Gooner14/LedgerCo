package com.arpitsardana.model;

import com.arpitsardana.model.emi.strategy.EMIStrategy;

/**
 * Model object to represent a Loan Account.
 */
public class Loan {
  private User loanUser;
  private EMIStrategy emiStrategy;
  private EmiDetails emiDetails;


  public Loan(User loanUser, EmiDetails emiDetails, EMIStrategy emiStrategy) {
    this.loanUser = loanUser;
    this.emiStrategy = emiStrategy;
    this.emiDetails = emiDetails;
  }

  public User getLoanUser() {
    return loanUser;
  }

  public EMIStrategy getEmiStrategy() {
    return emiStrategy;
  }

  public EmiDetails getEmiDetails() {
    return emiDetails;
  }

}
