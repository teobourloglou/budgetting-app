package com.example.budgettingapp.data.expense

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Upsert
    suspend fun upsertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * from expenses WHERE id = :id")
    fun getExpense(id: Int): Flow<Expense>

    @Query("SELECT * from expenses")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * from expenses ORDER BY date DESC")
    fun getAllExpensesByDate(): Flow<List<Expense>>
}