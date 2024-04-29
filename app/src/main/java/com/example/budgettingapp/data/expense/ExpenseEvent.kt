package com.example.budgettingapp.data.expense

import com.example.budgettingapp.data.category.Category

sealed interface ExpenseEvent {
    object SaveExpense: ExpenseEvent
    data class SetLabel(val label: String): ExpenseEvent
    data class SetAmount(val amount: String): ExpenseEvent
    data class SetDate(val date: String): ExpenseEvent
    data class DeleteExpense(val expense: Expense): ExpenseEvent
    data class UpdateExpense(val expense: Expense): ExpenseEvent

    object SaveCategory: ExpenseEvent
    data class SetName(val name: String): ExpenseEvent
    data class DeleteCategory(val category: Category): ExpenseEvent
    data class UpdateCategory(val category: Category): ExpenseEvent
}