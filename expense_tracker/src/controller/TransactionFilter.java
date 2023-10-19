package controller;

import java.util.List;
import model.Transaction;

/**
 * The TransactionFilter interface defines a method for filtering a list of transactions.
 */
public interface TransactionFilter {
    /**
     * Filters a list of transactions based on specific criteria.
     *
     * @param transactions The list of transactions to be filtered.
     * @return A filtered list of transactions based on the implemented criteria.
     */
    List<Transaction> filter(List<Transaction> transactions);
}
