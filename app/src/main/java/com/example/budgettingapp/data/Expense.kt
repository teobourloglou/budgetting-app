package com.example.budgettingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "expenses")
data class Expense (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    val amount: Double,
    val date: Date
)