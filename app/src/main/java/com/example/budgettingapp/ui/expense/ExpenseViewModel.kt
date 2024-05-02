package com.example.budgettingapp.ui.expense

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgettingapp.data.expense.Category
import com.example.budgettingapp.data.expense.Expense
import com.example.budgettingapp.data.expense.ExpenseDao
import com.example.budgettingapp.data.expense.ExpenseEvent
import com.example.budgettingapp.data.expense.ExpenseState
import com.example.budgettingapp.data.expense.Method
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExpenseViewModel(
    private val dao: ExpenseDao
): ViewModel() {
    @RequiresApi(Build.VERSION_CODES.O)
    private val _state = MutableStateFlow(ExpenseState())
    private val _expenses = dao.getAllExpensesByDate().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _categories = dao.getAllCategories().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _methods = dao.getAllMethods().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    @RequiresApi(Build.VERSION_CODES.O)
    val state = combine(_state, _expenses, _categories, _methods) { state, expenses, categories, methods ->
        state.copy(
            expenses = expenses,
            categories = categories,
            methods = methods
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExpenseState())
    @RequiresApi(Build.VERSION_CODES.O)
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
                val date = state.value.date
                val categoryId = state.value.categoryId

                if (label.isBlank() || amount.isBlank()) {
                    return
                }

                val expense = Expense(
                    label = label,
                    amount = amount,
                    date = date,
                    categoryId = categoryId
                )
                viewModelScope.launch {
                    dao.upsertExpense(expense)
                }
                _state.update { it.copy(
                    label = "",
                    amount = "",
                    date = LocalDate.now().toString()
                ) }
            }

            is ExpenseEvent.UpdateExpense -> {
                viewModelScope.launch {
                    dao.upsertExpense(event.expense)
                }
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

            is ExpenseEvent.SetDate -> {
                _state.update { it.copy(
                    date = event.date
                ) }
            }

            is ExpenseEvent.DeleteCategory -> {
                viewModelScope.launch {
                    dao.deleteCategory(event.category)
                }
            }

            ExpenseEvent.SaveCategory -> {
                val name = state.value.name

                if (name.isBlank()) {
                    return
                }

                val category = Category(
                    name = name
                )
                viewModelScope.launch {
                    dao.upsertCategory(category)
                }
                _state.update { it.copy(
                    name = ""
                ) }
            }

            is ExpenseEvent.UpdateCategory -> {
                viewModelScope.launch {
                    dao.upsertCategory(event.category)
                }
            }

            is ExpenseEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                ) }
            }

            is ExpenseEvent.SetCategoryId -> {
                _state.update { it.copy(
                    categoryId = event.categoryId
                ) }
            }

            ExpenseEvent.SaveMethod -> {
                val payment = state.value.payment

                if (payment.isBlank()) {
                    return
                }

                val method = Method(
                    payment = payment
                )
                viewModelScope.launch {
                    dao.upsertMethod(method)
                }
                _state.update { it.copy(
                    payment = ""
                ) }
            }

            is ExpenseEvent.UpdateMethod -> {
                viewModelScope.launch {
                    dao.upsertMethod(event.method)
                }
            }

            is ExpenseEvent.SetPayment -> {
                _state.update { it.copy(
                    payment = event.payment
                ) }
            }

            is ExpenseEvent.SetMethodId -> {
                _state.update { it.copy(
                    methodId = event.methodId
                ) }
            }

            is ExpenseEvent.DeleteMethod -> {
                viewModelScope.launch {
                    dao.deleteMethod(event.method)
                }
            }
        }
    }
}