package com.example.budgettingapp.data.expense

sealed interface ExpenseEvent {
    object SaveExpense: ExpenseEvent

    data class SetLabel(val label: String): ExpenseEvent
    data class SetAmount(val amount: String): ExpenseEvent

    data class DeleteExpense(val expense: Expense): ExpenseEvent
}