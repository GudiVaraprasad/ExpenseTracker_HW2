package model;

import controller.InputValidation;
import view.ExpenseTrackerView;

import javax.swing.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public TransactionsFilterByAmount(double minAmount, double maxAmount) {

        this.minAmount = minAmount;
        this.maxAmount = maxAmount;

        if(!InputValidation.isValidAmount(minAmount) || !InputValidation.isValidAmount(maxAmount)){
            JOptionPane.showMessageDialog(null, "Invalid amount entered in the Filter.");
        }
    }
}