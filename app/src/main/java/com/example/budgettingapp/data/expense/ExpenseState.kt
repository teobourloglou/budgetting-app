package com.example.budgettingapp.data.expense

data class ExpenseState(
    val expenses: List<Expense> = emptyList(),
    val label: String = "",
    val amount: String = ""
)
