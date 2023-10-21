package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class that handles all the transactions in the Expense Tracker App
 */
public class Transaction {

  private double amount;
  private String category;
  private String timestamp;

  /**
   * Constructor for the Transaction class
   * @param amount
   * @param category
   */
  public Transaction(double amount, String category) {
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  /**
   * Getter method for amount
   * @return amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Setter method for amount
   * @param amount
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * Getter method for category
   * @return category
   */
  public String getCategory() {
    return category;
  }

  /**
   * Setter method for category
   * @param category
   */
  public void setCategory(String category) {
    this.category = category; 
  }

  /**
   * Getter function to generate timestamp
   * @return timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * Method to generate current timestamp
   * @return timestamp
   */
  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

}