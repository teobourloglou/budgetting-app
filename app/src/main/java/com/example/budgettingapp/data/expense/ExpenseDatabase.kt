package com.example.budgettingapp.data.expense

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

//@Database(
//    version = 4,
//    entities = [
//        Expense::class,
//        Category::class
//   ],
//    autoMigrations = [
//        AutoMigration (from = 3, to = 4)
//    ]
//)

@Database(
    version = 5,
    entities = [
        Expense::class,
        Category::class,
        Method::class
   ],
    autoMigrations = [
        AutoMigration (from = 4, to = 5)
    ]
)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun dao() : ExpenseDao
}