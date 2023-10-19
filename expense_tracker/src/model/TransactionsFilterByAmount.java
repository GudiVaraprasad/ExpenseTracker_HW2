package model;

import java.util.ArrayList;
import java.util.List;

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
    }
}