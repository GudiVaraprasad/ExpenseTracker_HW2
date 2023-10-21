package model;

import controller.InputValidation;
import model.Transaction;
import model.TransactionFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles Transaction filtering by category.
 * Category are provided by the user through the input field.
 * Here, validation is performed to check if the category field is in the desired format or not.
 */
public class TransactionsFilterByCategory implements TransactionFilter {
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {

        List<Transaction> filteredTransactions = new ArrayList<Transaction>();

        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equals(this.category)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    private String category;

    /**
     * Method for filtering the transactions by category and checking for inputValidation
     * @param category
     */
    public TransactionsFilterByCategory(String category) {
        this.category = category;
        if(!InputValidation.isValidCategory(category)){
                throw new IllegalArgumentException("Invalid category entered");

        }
    }

}