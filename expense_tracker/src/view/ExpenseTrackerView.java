package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;
  private JTextField filterMaxAmountField;
  private JTextField filterMinAmountField;
  private JTextField filterCategoryField;
  private JButton applyFilterBtn;
  private JButton resetFilterBtn;
  private List<Transaction> filteredTransactions;

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    // Create filter UI components
    JLabel filterMinAmountLabel = new JLabel("Select Min Amount:");
    JLabel filterMaxAmountLabel = new JLabel("Select Max Amount:");
    JLabel filterCategoryLabel = new JLabel("Select Category:");
    filterMinAmountField = new JFormattedTextField(format);
    filterMinAmountField.setColumns(10);
    filterMaxAmountField = new JFormattedTextField(format);
    filterMaxAmountField.setColumns(10);
    filterCategoryField = new JTextField(10);
    applyFilterBtn = new JButton("Apply Filter");
    resetFilterBtn = new JButton("Reset Filter");

    // Add filter components to UI
    JPanel filterPanel = new JPanel();
    filterPanel.add(filterMinAmountLabel);
    filterPanel.add(filterMinAmountField);
    filterPanel.add(filterMaxAmountLabel);
    filterPanel.add(filterMaxAmountField);
    filterPanel.add(filterCategoryLabel);
    filterPanel.add(filterCategoryField);

    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel); 
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
  
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);
    buttonPanel.add(applyFilterBtn);
    buttonPanel.add(resetFilterBtn);

    // Create a container panel for the input and filter panels
    JPanel containerPanel = new JPanel();
    containerPanel.setLayout(new BorderLayout());

    // Add the inputPanel and filterPanel to the container panel
    containerPanel.add(inputPanel, BorderLayout.NORTH);
    containerPanel.add(filterPanel, BorderLayout.SOUTH);

    // Add panels to frame
    add(containerPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
    add(buttonPanel, BorderLayout.SOUTH);
  
    // Set frame properties
    setSize(800, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);


    // Set up a custom cell renderer to highlight filtered rows
    transactionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                      boolean hasFocus, int row, int column) {
          Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

          // Check if the row is in the filtered transactions
          boolean isFiltered = isRowFiltered(table, row);
          if (isFiltered) {
              c.setBackground(new Color(173, 255, 168)); // Light green
          } else {
              c.setBackground(table.getBackground());
          }
          return c;
        }
    });
  }

  /**
   * Checks if a specific row in the JTable is included in the filtered transactions.
   *
   * @param table              The JTable to check for filtered rows.
   * @param row                The row number in the JTable to check.
   * @return true if the row is part of the filtered transactions, false otherwise.
   */
  private boolean isRowFiltered(JTable table, int row) {
    if (filteredTransactions == null) {
        return false;
    }

    int modelRow = table.convertRowIndexToModel(row); // Convert view row to model row
    Object amount = table.getValueAt(modelRow, 1); // Assuming 1 is the column index for the amount
    Object category = table.getValueAt(modelRow, 2); // Assuming 2 is the column index for the category
    Object timestamp = table.getValueAt(modelRow, 3); // Assuming 3 is the column index for the timestamp

    for (Transaction t : filteredTransactions) {
        if ((amount != null && amount.equals(t.getAmount())) &&
            (category != null && category.equals(t.getCategory())) &&
            (timestamp != null && timestamp.equals(t.getTimestamp()))) {
            return true;
        }
    }
    return false;
  }

  public void setFilteredTransactions(List<Transaction> filteredTransactions) {
    this.filteredTransactions = filteredTransactions;
  }

  public void removeFilteredTransactions() {
    this.filteredTransactions = null;
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost=0;
      // Calculate total cost
      for(Transaction t : transactions) {
        totalCost+=t.getAmount();
      }
      // Add rows from transactions list
      for(Transaction t : transactions) {
        model.addRow(new Object[]{rowNum+=1,t.getAmount(), t.getCategory(), t.getTimestamp()}); 
      }
        // Add total row
        Object[] totalRow = {"Total", null, null, totalCost};
        model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  
    }  
  

  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  public JButton getApplyFilterBtn() {
    return applyFilterBtn;
  }
  public JButton getResetFilterBtn() {
    return resetFilterBtn;
  }
  public DefaultTableModel getTableModel() {
    return model;
  }
  // Other view methods
    public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    }else {
    double amount = Double.parseDouble(amountField.getText());
    return amount;
    }
  }

  public double getFilterMaxAmountField() {
    if(filterMaxAmountField.getText().isEmpty()) {
      return 0;
    }else {
    double maxAmount = Double.parseDouble(filterMaxAmountField.getText());
    return maxAmount;
    }
  }

  public double getFilterMinAmountField() {
    if(filterMinAmountField.getText().isEmpty()) {
      return 0;
    }else {
    double minAmount = Double.parseDouble(filterMinAmountField.getText());
    return minAmount;
    }
  }

  public String getFilterCategoryField() {
    return filterCategoryField.getText();
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}