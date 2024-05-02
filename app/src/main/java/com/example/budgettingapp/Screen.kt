package com.example.budgettingapp

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Expenses: Screen("expense_screen")
    object ExpenseEntry: Screen("expense_entry_screen")
    object Categories: Screen("category_screen")
    object CategoryEntry: Screen("category_entry_screen")
    object Methods: Screen("method_screen")
    object MethodEntry: Screen("method_entry_screen")
}