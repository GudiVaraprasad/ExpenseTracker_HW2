package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);

    // Create combo box for category filtering
    String[] categories = {"food", "travel", "bills", "entertainment", "other"};
    JComboBox<String> categoryFilter = new JComboBox<>(categories);
    categoryFilter.setSelectedIndex(-1); // Initially, no category is selected

    // Create fields for minimum and maximum amount filtering
    JFormattedTextField minAmountFilter = new JFormattedTextField(format);
    JFormattedTextField maxAmountFilter = new JFormattedTextField(format);

    // Create a button to clear filters
    JButton clearFiltersBtn = new JButton("Clear Filters");
    JButton applyFiltersBtn = new JButton("Apply Filters");

    JPanel filterPanel = new JPanel();

    // Filter by Amount
//    filterPanel.add(new JLabel("Filter by Amount:"));
    filterPanel.add(new JLabel("Min Amount:"));
    filterPanel.add(minAmountFilter);
    minAmountFilter.setPreferredSize(new Dimension(50, 25));
    filterPanel.add(new JLabel("Max Amount:"));
    filterPanel.add(maxAmountFilter);
    maxAmountFilter.setPreferredSize(new Dimension(50, 25));


    // Filter by Category
    filterPanel.add(new JLabel("Category:"));
    filterPanel.add(categoryFilter);

    //
    filterPanel.add(applyFiltersBtn);
    //A field to clear all the applied filters
    filterPanel.add(clearFiltersBtn);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);

    // Combined inputPanel and filterPanel
    JPanel combinedPanel = new JPanel();
    combinedPanel.setLayout(new BoxLayout(combinedPanel, BoxLayout.Y_AXIS));
    combinedPanel.add(inputPanel);
    combinedPanel.add(filterPanel);

    // Add the combined panel to the NORTH position of your main frame
    add(combinedPanel, BorderLayout.NORTH);

    add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Set frame properties
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  

  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}
