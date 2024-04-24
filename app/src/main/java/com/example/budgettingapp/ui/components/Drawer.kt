package com.example.budgettingapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budgettingapp.Screen
import com.example.budgettingapp.ui.Home
import com.example.budgettingapp.ui.theme.BudgettingAppTheme

@Composable
fun DrawerContent(navController: NavController) {
    ModalDrawerSheet {
        Text(
            "Menu",
            fontSize = 26.sp,
            modifier = Modifier
                .padding(26.dp)
                .fillMaxWidth()

        )
        NavigationDrawerItem(
            label = {
                Text(
                    text = "Home",
                    fontFamily = FontFamily.Default
                )
            },
            modifier = Modifier.padding(10.dp),
            selected = false,
            onClick = {
                navController.navigate(route = Screen.Home.route)
            }
        )
        NavigationDrawerItem(
            label = {
                Text(
                    text = "Expenses",
                    fontFamily = FontFamily.Default
                )
            },
            modifier = Modifier.padding(10.dp),
            selected = false,
            onClick = {
                navController.navigate(route = Screen.Expenses.route)
            }
        )
    }
}