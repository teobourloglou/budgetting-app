package com.example.budgettingapp.ui.components

import androidx.compose.material3.Divider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.budgettingapp.Screen

@Composable
fun DrawerContent(navController: NavController) {
    ModalDrawerSheet {
        Text("Drawer Title")
        Divider()
        NavigationDrawerItem(
            label = { Text(text = "Home") },
            selected = false,
            onClick = {
                navController.navigate(route = Screen.Home.route)
            }
        )
        Divider()
        NavigationDrawerItem(
            label = { Text(text = "Expenses") },
            selected = false,
            onClick = {
                navController.navigate(route = Screen.Expenses.route)
            }
        )
    }
}