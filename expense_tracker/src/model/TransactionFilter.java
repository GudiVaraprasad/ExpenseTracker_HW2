package model;
import model.Transaction;

import java.util.List;

public interface TransactionFilter {
    // Interface for the filter functionality to achieve reusability.
    public List<Transaction> filter(List<Transaction> transactions);

}