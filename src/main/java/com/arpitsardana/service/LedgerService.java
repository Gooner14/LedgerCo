package com.arpitsardana.service;

import com.arpitsardana.exception.InvalidNumberOfUserAccountsException;
import com.arpitsardana.model.EmiDetails;
import com.arpitsardana.model.User;
import com.arpitsardana.model.Loan;
import com.arpitsardana.model.emi.strategy.SimpleInterestEMIStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main Service for Ledger Co . This will have all the business logic.
 */
public class LedgerService {
  private List<Loan> loans = new ArrayList<>();

  public List<Loan> getLoans() {
    return loans;
  }

  public void createLoan(final User user, final EmiDetails emiDetails) {

    Loan loan = new Loan(user, emiDetails, new SimpleInterestEMIStrategy());
    getLoans().add(loan);

  }

  public Loan getLoanforUser(final String userName, final String bankName) {

    List<Loan> allLoansWithUser = getLoans()
            .stream()
            .filter(c -> userName.equalsIgnoreCase(c.getLoanUser().getUserName()))
            .filter(c -> bankName.equalsIgnoreCase(c.getLoanUser().getBankName()))
            .collect(Collectors.toList());

    if(allLoansWithUser.size()!=1)
      throw new InvalidNumberOfUserAccountsException();

    return allLoansWithUser.get(0);
  }

}
