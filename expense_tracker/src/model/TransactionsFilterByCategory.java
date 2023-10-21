package model;

import controller.InputValidation;
import model.Transaction;
import model.TransactionFilter;

import java.util.ArrayList;
import java.util.List;

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

    public TransactionsFilterByCategory(String category) {
        this.category = category;
        if(!InputValidation.isValidCategory(category)){
                throw new IllegalArgumentException("Invalid category entered");

        }
    }

}