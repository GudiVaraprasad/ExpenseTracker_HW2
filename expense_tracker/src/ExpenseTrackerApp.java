import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerApp {

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

    view.getApplyFilterBtn().addActionListener(e -> {
      double minAmount = view.getMinAmountFilterField();
      double maxAmount = view.getMaxAmountFilterField();

      String selectedCategory = view.getCategoryFilterField();
      List<Transaction> filteredTransactions = new ArrayList<>();

      if(minAmount == 0 && maxAmount == 0 && selectedCategory.equals("null")){
          JOptionPane.showMessageDialog(view,"Select a filter");
      } else if(minAmount != 0 && maxAmount != 0 && !selectedCategory.equals("null")){
          JOptionPane.showMessageDialog(view,"Select only one filter");
      } else if(minAmount == 0 && maxAmount == 0){
        filteredTransactions = controller.applyFilter(selectedCategory, "category");
      } else {
          double[] range = {minAmount, maxAmount};
          filteredTransactions = controller.applyFilter(range, "amount");
      }
      System.out.println(filteredTransactions);
    });

    view.getClearFilterBtn().addActionListener(e -> {
      view.setMaxAmountFilterField(0);
      view.setMinAmountFilterField(0);
      view.setCategoryFilterField("");
    });

  }

}