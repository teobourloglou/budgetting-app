package com.example.budgettingapp.data.expense

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ExpenseState @RequiresApi(Build.VERSION_CODES.O) constructor(
    val expenses: List<Expense> = emptyList(),
    val categories: List<Category> = emptyList(),
    val label: String = "",
    val amount: String = "",
    val date: String = currentDate(),
    val name: String = "",
    val categoryId: Int = 0
)

@RequiresApi(Build.VERSION_CODES.O)
fun currentDate(): String {
    val date = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
    return date.toString()
}