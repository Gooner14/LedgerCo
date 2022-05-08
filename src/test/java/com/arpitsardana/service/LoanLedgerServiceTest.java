//package com.uditagarwal.service;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.uditagarwal.exception.ParkingLotException;
//import com.uditagarwal.model.User;
//import com.uditagarwal.model.Loan;
//import com.uditagarwal.model.emi.strategy.SimpleInterestEMIStrategy;
//import com.uditagarwal.model.emi.strategy.EMIStrategy;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.junit.Before;
//import org.junit.Test;
//
//public class LoanLedgerServiceTest {
//  private LedgerService ledgerService = new LedgerService();
//  private EMIStrategy parkingStrategy;
//  private LoanLedger loanLedger;
//
//  @Before
//  public void setUp() throws Exception {
//    parkingStrategy = mock(EMIStrategy.class);
//    ;
//    loanLedger = mock(LoanLedger.class);
//    ledgerService.createParkingLot(loanLedger, parkingStrategy);
//  }
//
//  @Test(expected = ParkingLotException.class)
//  public void testCreatingParkingLotWhenAlreadyExists() {
//    final LedgerService ledgerService = new LedgerService();
//    ledgerService.createParkingLot(new LoanLedger(10), new SimpleInterestEMIStrategy());
//    ledgerService.createParkingLot(new LoanLedger(20), new SimpleInterestEMIStrategy());
//  }
//
//  @Test
//  public void testSlotNumberIsRemovedFromStrategyAfterParking() {
//    final User testUser = new User("test-car-no", "white");
//    when(parkingStrategy.getNextSlot()).thenReturn(1);
//    ledgerService.park(testUser);
//    verify(parkingStrategy).removeSlot(1);
//  }
//
//  @Test
//  public void testParkingIsDoneInTheParkingLot() {
//    final User testUser = new User("test-car-no", "white");
//    when(parkingStrategy.getNextSlot()).thenReturn(1);
//    ledgerService.park(testUser);
//    verify(loanLedger).park(testUser, 1);
//  }
//
//  @Test(expected = ParkingLotException.class)
//  public void testParkingCarWithoutCreatingParkingLot() {
//    final LedgerService ledgerService = new LedgerService();
//    final User testUser = new User("test-car-no", "white");
//    ledgerService.park(testUser);
//  }
//
//  @Test
//  public void testFreeingSlot() {
//    ledgerService.makeSlotFree(1);
//    verify(parkingStrategy).addSlot(1);
//    verify(loanLedger).makeSlotFree(1);
//  }
//
//  @Test(expected = ParkingLotException.class)
//  public void testFreeingSlotWithoutCreatingParkingLot() {
//    final LedgerService ledgerService = new LedgerService();
//    ledgerService.makeSlotFree(1);
//  }
//
//  @Test
//  public void testOccupiedSlots() {
//    final Map<Integer, Loan> allSlots = new HashMap<>();
//    final Loan loan1 = new Loan(1);
//    final Loan loan2 = new Loan(2);
//    loan2.assignCar(new User("test-car-no1", "white"));
//    final Loan loan3 = new Loan(3);
//    final Loan loan4 = new Loan(4);
//    loan4.assignCar(new User("test-car-no2", "white"));
//
//    allSlots.put(1, loan1);
//    allSlots.put(2, loan2);
//    allSlots.put(3, loan3);
//    allSlots.put(4, loan4);
//
//    when(loanLedger.getSlots()).thenReturn(allSlots);
//    when(loanLedger.getCapacity()).thenReturn(10);
//
//    final List<Loan> occupiedLoans = ledgerService.getOccupiedSlots();
//    assertEquals(2, occupiedLoans.size());
//    assertEquals(loan2, occupiedLoans.get(0));
//    assertEquals(loan4, occupiedLoans.get(1));
//  }
//
//  @Test
//  public void testGetSlotsForAParticularColor() {
//    final Map<Integer, Loan> allSlots = new HashMap<>();
//    final Loan loan1 = new Loan(1);
//    loan1.assignCar(new User("test-car-no1", "blue"));
//    final Loan loan2 = new Loan(2);
//    loan2.assignCar(new User("test-car-no2", "white"));
//    final Loan loan3 = new Loan(3);
//    final Loan loan4 = new Loan(4);
//    loan4.assignCar(new User("test-car-no3", "white"));
//
//    allSlots.put(1, loan1);
//    allSlots.put(2, loan2);
//    allSlots.put(3, loan3);
//    allSlots.put(4, loan4);
//
//    when(loanLedger.getSlots()).thenReturn(allSlots);
//    when(loanLedger.getCapacity()).thenReturn(10);
//
//    final List<Loan> loans = ledgerService.getSlotsForColor("white");
//    assertEquals(2, loans.size());
//    assertEquals(loan2, loans.get(0));
//    assertEquals(loan4, loans.get(1));
//  }
//
//  @Test
//  public void testGetSlotsForAParticularCarColorWhenNoCarMatches() {
//    final Map<Integer, Loan> allSlots = new HashMap<>();
//    final Loan loan1 = new Loan(1);
//    loan1.assignCar(new User("test-car-no1", "blue"));
//    final Loan loan2 = new Loan(2);
//    final Loan loan3 = new Loan(3);
//    loan3.assignCar(new User("test-car-no2", "red"));
//
//    allSlots.put(1, loan1);
//    allSlots.put(2, loan2);
//    allSlots.put(3, loan3);
//
//    when(loanLedger.getSlots()).thenReturn(allSlots);
//    when(loanLedger.getCapacity()).thenReturn(10);
//
//    final List<Loan> loans = ledgerService.getSlotsForColor("white");
//    assertEquals(0, loans.size());
//  }
//}
