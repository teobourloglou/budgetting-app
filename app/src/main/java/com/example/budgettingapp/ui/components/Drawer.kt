package com.example.budgettingapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.budgettingapp.Screen
@Composable
fun DrawerContent(navController: NavController) {
    ModalDrawerSheet (
        drawerContainerColor = Color.Black,
        drawerTonalElevation = 0.dp
    ) {
        Text(
            text = "Menu",
            fontSize = 26.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
        )
        NavigationDrawerItem(
            label = {
                Text(
                    text = "Home",
                    fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                )
            },
            selected = false,
            onClick = {
                navController.navigate(route = Screen.Home.route)
            },
            modifier = Modifier.padding(10.dp),
        )
        NavigationDrawerItem(
            label = {
                Text(
                    text = "Expenses",
                    fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                )
            },
            selected = false,
            onClick = {
                navController.navigate(route = Screen.Expenses.route)
            },
            modifier = Modifier.padding(10.dp)
        )
        NavigationDrawerItem(
            label = {
                Text(
                    text = "Categories",
                    fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                )
            },
            selected = false,
            onClick = {
                navController.navigate(route = Screen.Categories.route)
            },
            modifier = Modifier.padding(10.dp)
        )
    }
}