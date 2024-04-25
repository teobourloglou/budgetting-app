package com.example.budgettingapp

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Expenses: Screen("expense_screen")
    object AddExpense: Screen("add_expense_screen")
}