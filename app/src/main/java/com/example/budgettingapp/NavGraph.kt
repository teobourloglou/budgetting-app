package com.example.budgettingapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            Home(300, 0, navController)
        }
        composable(
            route = Screen.Expenses.route
        ) {
            Expenses(navController)
        }
    }
}