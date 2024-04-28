package com.example.budgettingapp.data.expense

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(
//    version = 1,
//    entities = [Expense::class],
//)
//abstract class ExpenseDatabase : RoomDatabase() {
//    abstract fun dao() : ExpenseDao
//}

@Database(
    version = 2,
    entities = [Expense::class],
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun dao() : ExpenseDao
}

