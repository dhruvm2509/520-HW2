# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.


## Apply Filter Feature Implementation
We've successfully introduced the filter feature with the following information:


The Expense Tracker App now includes a comprehensive filter feature, enabling users to refine their transaction data based on both the amount and category attributes. This feature promotes code modularity by encapsulating each filtering algorithm within reusable classes, allowing for easy integration of new filter strategies.

Users have the flexibility to apply filters based on either the "amount" or "category" of their transactions, but not both simultaneously. When a filter is applied and matches one or more transactions, these matching rows are highlighted in a vibrant green color (RGB code: [173, 255, 168]) for improved visibility.

It's worth noting that the input validation logic developed in Homework 1 for the "amount" and "category" fields has been extended to the filter functionality as well. This ensures that the user's filtering criteria align with the expected data types and constraints, providing a consistent and reliable experience.

Certainly, here's a section for your README.md file describing the implemented undo feature, based on the provided paragraph:

## Undo Feature Implementation

In line with good UI design principles, the Expense Tracker App now incorporates a user-friendly undo functionality. This feature enhances the overall user experience by allowing users to easily reverse any unintended actions.

The basic idea of the undo functionality within the app includes the following key aspects:

- **Remove Transactions:** Users have the ability to remove any transaction by simply selecting the corresponding row within the user interface. This intuitive action streamlines the process of managing their financial records.

- **Total Cost Update:** When a user removes a transaction, the total cost is automatically updated to reflect this change. This ensures that the application's financial summary accurately represents the latest state, providing users with a real-time overview of their expenses.

This undo feature not only simplifies the process of managing transactions but also empowers users to maintain accurate and up-to-date financial records with ease.