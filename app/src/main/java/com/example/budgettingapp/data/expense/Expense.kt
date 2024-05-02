package com.example.budgettingapp.data.expense

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "expenses", foreignKeys = [
    ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"], onDelete = ForeignKey.SET_NULL, onUpdate = ForeignKey.CASCADE),
    ForeignKey(entity = Method::class, parentColumns = ["id"], childColumns = ["methodId"], onDelete = ForeignKey.SET_NULL, onUpdate = ForeignKey.CASCADE)
])
data class Expense (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    val amount: String,
    @ColumnInfo(defaultValue = "")
    val date: String,
    val categoryId: Int? = null,
    val methodId: Int? = null
)

@Entity(tableName = "categories")
data class Category (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)

@Entity(tableName = "methods")
data class Method (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val payment: String
)