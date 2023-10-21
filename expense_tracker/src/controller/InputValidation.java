/**
 * packkkkage
 */
package controller;

import java.util.Arrays;

/**
 * Performs Input validation for the amount and category fields.
 * For the filters, MinAmount, MaxAmount and categoryFilter are input validated.
 * amount, minAmount and maxAmount are validated to be in the range of 0 to 1000.
 * category is validated to be only a string and no digits or special characters.
 */
public class InputValidation {

  /**
   * Method for Validating the amount field
   * Returns true if the amount is in the desired format
   * @param amount
   * @return booleanValue
   */
  public static boolean isValidAmount(double amount) {
    
    // Check range
    if(amount >1000) {
      return false;
    }
    if (amount < 0){
      return false;
    }
    if (amount == 0){
      return false;
    }
    return true;
  }

  /**
   * Method for Validating the category field
   * Returns true if the category is in the desired format
   * @param category
   * @return booleanValue
   */
  public static boolean isValidCategory(String category) {

    if(category == null) {
      return false; 
    }
  
    if(category.trim().isEmpty()) {
      return false;
    }

    if(!category.matches("[a-zA-Z]+")) {
      return false;
    }

    String[] validWords = {"food", "travel", "bills", "entertainment", "other"};

    if(!Arrays.asList(validWords).contains(category.toLowerCase())) {
      // invalid word  
      return false;
    }
  
    return true;
  
  }

}