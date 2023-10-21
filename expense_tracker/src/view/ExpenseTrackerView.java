package view;

import javax.lang.model.util.ElementScanner6;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableCellRenderer;

import controller.InputValidation;
import java.awt.*;
import java.text.Format;
import java.text.NumberFormat;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExpenseTrackerView extends JFrame {
  private JTable transactionsTable;

  NumberFormat format = NumberFormat.getNumberInstance(Locale.US);

  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;

  // ArrayList to store filtered row numbers
  private List<Integer> filteredRowNumbers = new ArrayList<>();
  private JButton applyFiltersBtn;

  private JButton clearFiltersBtn;
  private JFormattedTextField minAmountFilter;
  private JFormattedTextField maxAmountFilter;

  private JComboBox<String> categoryFilter;


  public ExpenseTrackerView() {

    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();
    format.setGroupingUsed(false);


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
    categoryFilter = new JComboBox<>(categories);
    categoryFilter.setSelectedIndex(-1); // Initially, no category is selected

    // Create fields for minimum and maximum amount filtering
    minAmountFilter = new JFormattedTextField(format);
    maxAmountFilter = new JFormattedTextField(format);

    // Create a button to clear filters
    clearFiltersBtn = new JButton("Clear Filters");
    applyFiltersBtn = new JButton("Apply Filters");

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

  // Method to set the filtered row numbers
  public void setFilteredRowNumbers(List<Integer> rowNumbers) {
    filteredRowNumbers = rowNumbers;
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
    Object[] totalRow = {"Total",totalCost, null, null};
    model.addRow(totalRow);

    // Fire table update
    transactionsTable.updateUI();

  }

  public void refreshTableForFilteredTransactions(List<Transaction> transactions, List<Transaction> filteredTransactions) {

    // Clear existing rows
    model.setRowCount(0);
    // Get row count
    int rowNum = model.getRowCount();
    double totalCost=0;

    //List for storing filtered Row Numbers
    List<Integer> filteredRowNumbers = new ArrayList<>(); // Create a new list


    // Calculate total cost
    for(Transaction t : transactions) {
      totalCost+=t.getAmount();
    }

    // Add rows from transactions list
    for(Transaction t : transactions) {

      model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()});

      for(Transaction ft : filteredTransactions){

          if(t.getCategory().equals(ft.getCategory()) && ft.getAmount() == t.getAmount()){
            filteredRowNumbers.add(rowNum-1); // Add the row number to the list
            break;
//
        }
      }
//      rowNum++;
    }
    // Add total row
    Object[] totalRow = {"Total", totalCost, null, null};
    model.addRow(totalRow);

    setFilteredRowNumbers(filteredRowNumbers);
    transactionsTable.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer(filteredRowNumbers));
    transactionsTable.getColumnModel().getColumn(1).setCellRenderer(new CustomTableCellRenderer(filteredRowNumbers));
    transactionsTable.getColumnModel().getColumn(2).setCellRenderer(new CustomTableCellRenderer(filteredRowNumbers));
    transactionsTable.getColumnModel().getColumn(3).setCellRenderer(new CustomTableCellRenderer(filteredRowNumbers));
    transactionsTable.updateUI();
  }

  public double getMinAmountFilterField(){
    if(minAmountFilter.getText().isEmpty()) {
      return 0;
    }else {
      double amount = Double.parseDouble(minAmountFilter.getText());
      return amount;
    }
  }

  public double getMaxAmountFilterField(){
    if(maxAmountFilter.getText().isEmpty()) {
      return 0;
    }else {
      double amount = Double.parseDouble(maxAmountFilter.getText());
      return amount;
    }
  }

  public String getCategoryFilterField(){
    return String.valueOf(categoryFilter.getSelectedItem());
  }
  public JButton getApplyFilterBtn() {
    return applyFiltersBtn;
  }

  public JButton getClearFilterBtn() {
    return clearFiltersBtn;
  }

  public void setCategoryFilterField(String category){
    if(category.equals("")){
      categoryFilter.setSelectedIndex(-1);
    }
  }

  public void setMinAmountFilterField(double minAmount){
    minAmountFilter.setValue(minAmount);
  }

  public void setMaxAmountFilterField(double maxAmount){
    maxAmountFilter.setValue(maxAmount);
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

class CustomTableCellRenderer extends DefaultTableCellRenderer {



  private List<Integer> filteredRowNumbers;


  private Color backgroundColor;

  //    public CustomTableCellRenderer(int filteredRowIndex, Color backgroundColor) {
  public CustomTableCellRenderer(List<Integer> filteredRowNumbers) {
    this.filteredRowNumbers = filteredRowNumbers;
  }

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

    if(filteredRowNumbers.contains(row)) {
      comp.setBackground(new Color(173, 255, 168));
    }else {
      comp.setBackground(Color.WHITE);
    }
    return comp;
  }

}
