package com.example.budgettingapp.data.expense

sealed interface ExpenseEvent {
    object SaveExpense: ExpenseEvent
    data class SetLabel(val label: String): ExpenseEvent
    data class SetAmount(val amount: String): ExpenseEvent
    data class SetDate(val date: String): ExpenseEvent
    data class SetCategoryId(val categoryId: Int): ExpenseEvent
    data class SetMethodId(val methodId: Int): ExpenseEvent
    data class DeleteExpense(val expense: Expense): ExpenseEvent
    data class UpdateExpense(val expense: Expense): ExpenseEvent

    object SaveCategory: ExpenseEvent
    data class SetName(val name: String): ExpenseEvent
    data class DeleteCategory(val category: Category): ExpenseEvent
    data class UpdateCategory(val category: Category): ExpenseEvent

    object SaveMethod: ExpenseEvent
    data class SetPayment(val payment: String): ExpenseEvent
    data class DeleteMethod(val method: Method): ExpenseEvent
    data class UpdateMethod(val method: Method): ExpenseEvent
}