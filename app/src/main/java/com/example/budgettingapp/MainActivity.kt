@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.budgettingapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.budgettingapp.data.expense.ExpenseDatabase
import com.example.budgettingapp.ui.expense.ExpenseViewModel
import com.example.budgettingapp.ui.theme.BudgettingAppTheme


class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ExpenseDatabase::class.java,
            "expenses.db"
        ).build()
    }

    private val viewModel by viewModels<ExpenseViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ExpenseViewModel(db.dao()) as T
                }
            }
        }
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgettingAppTheme {
                val state by viewModel.state.collectAsState()
                navController = rememberNavController()

                SetupNavGraph(
                    navController = navController,
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}