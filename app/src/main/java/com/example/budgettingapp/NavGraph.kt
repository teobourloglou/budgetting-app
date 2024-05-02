package com.example.budgettingapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.budgettingapp.data.expense.ExpenseEvent
import com.example.budgettingapp.data.expense.ExpenseState
import com.example.budgettingapp.ui.expense.Expenses
import com.example.budgettingapp.ui.Home
import com.example.budgettingapp.ui.category.Categories
import com.example.budgettingapp.ui.category.CategoryEntry
import com.example.budgettingapp.ui.expense.ExpenseEntry
import com.example.budgettingapp.ui.method.MethodEntry
import com.example.budgettingapp.ui.method.Methods

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    state: ExpenseState,
    onEvent: (ExpenseEvent) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            Home(
                navController = navController,
                state = state,
                onEvent = onEvent
            )
        }
        composable(
            route = Screen.Expenses.route
        ) {
            Expenses(
                navController,
                state,
                onEvent
            )
        }
        composable(
            route = Screen.ExpenseEntry.route
        ) {
            ExpenseEntry(
                navController,
                state,
                onEvent,
            )
        }
        composable(
            route = Screen.Categories.route
        ) {
            Categories(
                navController,
                state,
                onEvent
            )
        }
        composable(
            route = Screen.CategoryEntry.route
        ) {
            CategoryEntry(
                navController,
                state,
                onEvent,
            )
        }
        composable(
            route = Screen.Methods.route
        ) {
            Methods(
                navController,
                state,
                onEvent
            )
        }
        composable(
            route = Screen.MethodEntry.route
        ) {
            MethodEntry(
                navController,
                state,
                onEvent,
            )
        }
    }
}