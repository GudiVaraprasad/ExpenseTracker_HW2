# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction.

## Expense Tracker

Expense Tracker is an application that helps you manage all the daily transactions.

# Features

## Amount

The user can input amount in the amount input field.

## Category

Users can select category for which they spent the amount.

## Input Validation

The input fields are validated.

The amount can be between 0 to 1000.
The category can only be a string and not a digit or a special character.

## Filtering

### Based on Amount

It is now possible to filter the transactions based on the minimum and the maximum amount that the user provides.

### Based on Category

User can filter the transactions based on the category. Filtering based on category has been made very convenient. User just needs to select a category from the dropdown and click on 'Apply Filters' button.

## Compile

To compile the code from terminal, use the following command:

```
cd src
javac ExpenseTrackerApp.java
java ExpenseTrackerApp
```

You should be able to view the GUI of the project upon successful compilation.

## Java Version

This code is compiled with `openjdk 17.0.7 2023-04-18`. Please update your JDK accordingly if you face any incompatibility issue.
