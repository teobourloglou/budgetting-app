package com.example.budgettingapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.budgettingapp.ui.AddExpense
import com.example.budgettingapp.ui.Expenses
import com.example.budgettingapp.ui.Home

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            Home(
                amount = 300,
                amountCents = 0,
                navController = navController
            )
        }
        composable(
            route = Screen.Expenses.route
        ) {
            Expenses(navController)
        }
        composable(
            route = Screen.AddExpense.route
        ) {
            AddExpense(navController)
        }
    }
}