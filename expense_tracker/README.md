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