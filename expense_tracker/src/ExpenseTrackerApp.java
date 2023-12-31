import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Main Class for handling the Expense Tracker App.
 * Contains Main Method.
 */

public class ExpenseTrackerApp {

  /**
   * Main method
   * 
   * @param args
   */
  public static void main(String[] args) {

    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();

      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);

      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    // Action Listner for apply filter button
    view.getApplyFilterBtn().addActionListener(e -> {
      double minAmount = view.getMinAmountFilterField();
      double maxAmount = view.getMaxAmountFilterField();

      String selectedCategory = view.getCategoryFilterField();
      List<Transaction> filteredTransactions = new ArrayList<>();

      if (minAmount == 0 && maxAmount == 0 && selectedCategory.equals("null")) {
        JOptionPane.showMessageDialog(view, "Select a filter");
      } else if (minAmount != 0 && maxAmount != 0 && !selectedCategory.equals("null")) {
        JOptionPane.showMessageDialog(view, "Select only one filter");
      } else if (maxAmount != 0 && !selectedCategory.equals("null")) {
        JOptionPane.showMessageDialog(view, "Select only one filter");
      } else if (minAmount != 0 && !selectedCategory.equals("null")) {
        JOptionPane.showMessageDialog(view, "Select only one filter");
      } else if (minAmount == 0 && maxAmount == 0) {
        filteredTransactions = controller.applyFilter(selectedCategory, "category");
      } else {
        double[] range = { minAmount, maxAmount };
        filteredTransactions = controller.applyFilter(range, "amount");
      }
      view.refreshTableForFilteredTransactions(model.getTransactions(), filteredTransactions);
    });

    // Action Listner for clear filter button
    view.getClearFilterBtn().addActionListener(e -> {
      view.setMaxAmountFilterField(0);
      view.setMinAmountFilterField(0);
      view.setCategoryFilterField("");
      view.refreshTable(model.getTransactions());
    });

  }

}