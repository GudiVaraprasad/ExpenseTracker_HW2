package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseTrackerModel {

  private List<Transaction> transactions;

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  public List<Transaction> getTransactions() {
    // returning immutable list which is basically a copy of the original list.
    return Collections.unmodifiableList(new ArrayList<>(transactions));
  }

}