package model;

import controller.InputValidation;
import view.ExpenseTrackerView;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Handles Transaction filtering by amount.
 * Minimum and Maximum amounts are provided by the user through the input fields.
 * Here, validation is performed to check if the minAmount and maxAmount are in the range.
 */
public class TransactionsFilterByAmount implements TransactionFilter {
    @Override
    // overriding method for the interface.
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filteredTransactions = new ArrayList<Transaction>();

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() >= this.minAmount && transaction.getAmount() <= this.maxAmount) {
                filteredTransactions.add(transaction); // adding transaction
            }
        }
        return filteredTransactions;
    }

    private double minAmount;
    private double maxAmount;

    /**
     * Method for filtering transactions by amount.
     * Takes minimum amount and maximum amount and checks for inputValidation
     * @param minAmount
     * @param maxAmount
     */
    public TransactionsFilterByAmount(double minAmount, double maxAmount) {

        this.minAmount = minAmount;
        this.maxAmount = maxAmount;

        if(!InputValidation.isValidAmount(minAmount) || !InputValidation.isValidAmount(maxAmount)){
            JOptionPane.showMessageDialog(null, "Invalid amount entered in the Filter.");
        }
    }
}