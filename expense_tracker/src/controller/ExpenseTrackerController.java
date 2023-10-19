package controller;

import model.*;
import view.ExpenseTrackerView;

import java.util.List;

public class ExpenseTrackerController {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }

    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[] { t.getAmount(), t.getCategory(), t.getTimestamp() });
    refresh();
    return true;
  }

  // Other controller methods

  public List<Transaction> applyFilter(Object filterCondition, String filterBy) {
    TransactionFilter filter;

    if (filterBy.equals("category")) {
      filter = new TransactionsFilterByCategory((String) filterCondition);
    } else if (filterBy.equals("amount")) {
      // Assuming criteria is an array of {minAmount, maxAmount}
      double[] range = (double[]) filterCondition;
      filter = new TransactionsFilterByAmount(range[0], range[1]);
    } else {
      throw new IllegalArgumentException("Invalid filter type");
    }

    return filter.filter(model.getTransactions()); // transactions is your list of transactions
  }
}