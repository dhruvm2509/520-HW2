package controller;

import view.ExpenseTrackerView;

import java.util.List;



import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }
  
  // Other controller methods
   // Other controller methods

  /**
   * Applies a filter to the transactions based on specified criteria.
   * Filters by amount and/or category.
   *
   * @param filterMinAmount The minimum amount for filtering. Set to 0 for no minimum.
   * @param filterMaxAmount The maximum amount for filtering. Set to 0 for no maximum.
   * @param filterCategory  The category used for filtering transactions. Set to an empty string for no category filtering.
   * @return True if the filter was applied successfully; false if invalid filter values were entered.
   */
  public boolean applyFilter(double filterMinAmount, double filterMaxAmount, String filterCategory) {
    List<Transaction> transactions = model.getTransactions();

    // Check for valid filter values
    if (filterMinAmount != 0 && !InputValidation.isValidAmount(filterMinAmount)) {
      return false;
    }
    if (filterMaxAmount != 0 && !InputValidation.isValidAmount(filterMaxAmount)) {
      return false;
    }
    if (!filterCategory.isBlank() && !InputValidation.isValidCategory(filterCategory)) {
      return false;
    }
    // Create transaction filters
    TransactionFilter transactionAmountFilter = new AmountFilter(filterMinAmount, filterMaxAmount);
    TransactionFilter transactionCategoryFilter = new CategoryFilter(filterCategory);
    // Apply filters in sequence
    List<Transaction> filteredAmountTransactions = transactionAmountFilter.filter(transactions);
    List<Transaction> filteredTransactions = transactionCategoryFilter.filter(filteredAmountTransactions);

    // Pass the filteredTransactions to the view to highlight the filtered rows
    view.setFilteredTransactions(filteredTransactions);

    // Refresh the table
    refresh();

    return true;
  }
}