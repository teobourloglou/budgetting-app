package com.example.budgettingapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * from expenses WHERE id = :id")
    fun getExpense(id: Int): Flow<Expense>

    @Query("SELECT * from expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>
}