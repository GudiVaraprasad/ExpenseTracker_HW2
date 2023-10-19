package model;

import java.util.List;

public class TransactionsFilterByAmount implements TransactionFilter{
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return null;
    }

    private double minAmount;
    private double maxAmount;

    public TransactionsFilterByAmount(double minAmount, double maxAmount) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }
}