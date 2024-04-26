package com.example.budgettingapp.ui.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgettingapp.data.expense.Expense
import com.example.budgettingapp.data.expense.ExpenseDao
import com.example.budgettingapp.data.expense.ExpenseEvent
import com.example.budgettingapp.data.expense.ExpenseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExpenseViewModel(
    private val dao: ExpenseDao
): ViewModel() {
    private val _state = MutableStateFlow(ExpenseState())
    private val _expenses = dao.getAllExpenses().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val state = combine(_state, _expenses) { state, expenses ->
        state.copy(
            expenses = expenses
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExpenseState())
    fun onEvent(event: ExpenseEvent) {
        when(event) {
            is ExpenseEvent.DeleteExpense -> {
                viewModelScope.launch {
                    dao.deleteExpense(event.expense)
                }
            }

            ExpenseEvent.SaveExpense -> {
                val label = state.value.label
                val amount = state.value.amount

                if (label.isBlank() || amount.isBlank()) {
                    return
                }

                val expense = Expense(
                    label = label,
                    amount = amount
                )
                viewModelScope.launch {
                    dao.upsertExpense(expense)
                }
                _state.update { it.copy(
                    label = "",
                    amount = ""
                ) }
            }

            is ExpenseEvent.SetLabel -> {
                _state.update { it.copy(
                    label = event.label
                ) }
            }

            is ExpenseEvent.SetAmount -> {
                _state.update { it.copy(
                    amount = event.amount
                ) }
            }
        }
    }
}