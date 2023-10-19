import javax.swing.JOptionPane;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();
      
      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);
      
      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });

    view.getApplyFilterBtn().addActionListener(e -> {
      // Get filter parameters from the view
      double filterMaxAmount = view.getFilterMaxAmountField();
      double filterMinAmount = view.getFilterMinAmountField();
      String filterCategory = view.getFilterCategoryField();

     // Call the controller to apply the filter
      boolean filtered = controller.applyFilter(filterMinAmount, filterMaxAmount, filterCategory);

      // Show an error message if the filter is invalid
      if (!filtered) {  
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered for filtering");
        view.toFront();
      }
  });

    // Handle reset filter button clicks
    view.getResetFilterBtn().addActionListener(e -> {
      view.removeFilteredTransactions();
      controller.refresh();
  });
  }

}