package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Model of the Expense Tracker App
 */
public class ExpenseTrackerModel {

  private List<Transaction> transactions;

  /**
   * Constructor for ExpenseTrackerModel class
   */
  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  /**
   * Method for adding transaction
   * @param t
   */
  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  /**
   * Method to remove transaction
   * @param t
   */
  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  /**
   * Method to return an Immutable arrayList.
   * @return
   */
  public List<Transaction> getTransactions() {

    // returning immutable list which is basically a copy of the original list.
    return Collections.unmodifiableList(new ArrayList<>(transactions));
  }

}