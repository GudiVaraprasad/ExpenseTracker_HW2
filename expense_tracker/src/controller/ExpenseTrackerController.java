package controller;

import model.*;
import view.ExpenseTrackerView;

import java.util.List;

/**
 * Class for handling the controller of the Expense Tracker App
 */
public class ExpenseTrackerController {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  /**
   * Constructor for ExpenseTrackerController class.
   * @param model
   * @param view
   */
  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  /**
   * Method to refresh the table.
   */
  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  /**
   * Method for input validation and adding a transaction in the Table
   * Returns true if transaction is added.
   * Returns false if fails inputValidation
   * @param amount
   * @param category
   * @return booleanValue
   */
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

  /**
   * Arraylist that contains the filter conditions to be applied
   * @param filterCondition
   * @param filterBy
   * @return filtercondition
   */
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

    return filter.filter(model.getTransactions()); // transactions is the list of transactions
  }
}