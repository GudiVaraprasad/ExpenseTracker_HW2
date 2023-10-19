package model;

import model.Transaction;
import model.TransactionFilter;

import java.util.List;

public class TransactionsFilterByCategory implements TransactionFilter {
    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        return null;
    }

    private String category;

    public TransactionsFilterByCategory(String category) {
        this.category = category;
    }

}