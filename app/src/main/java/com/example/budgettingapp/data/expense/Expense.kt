package com.example.budgettingapp.data.expense

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "expenses", foreignKeys = [
    ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
])
data class Expense (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    val amount: String,
    @ColumnInfo(defaultValue = "")
    val date: String,
    val categoryId: Int? = null
)

@Entity(tableName = "categories")
data class Category (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)