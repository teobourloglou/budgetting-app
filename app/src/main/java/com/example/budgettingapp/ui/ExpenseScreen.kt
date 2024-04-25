package com.example.budgettingapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.budgettingapp.R
import com.example.budgettingapp.Screen
import com.example.budgettingapp.ui.components.DrawerContent
import com.example.budgettingapp.ui.theme.BudgettingAppTheme


@Composable
fun Expenses(navController: NavController, modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent(navController) }
    ) {
        ScreenContent(
            scope = scope,
            drawerState = drawerState,
            title = "",
            modifier = modifier.padding(5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 15.dp,
                    alignment = Alignment.Bottom
                ),
                modifier = modifier
                    .padding(8.dp)
            )
            {
                Text(
                    text = stringResource(R.string.expenses),
                    fontSize = 26.sp,
                    modifier = modifier.padding(bottom = 10.dp)
                )
                Row (
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 10.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    modifier = modifier.fillMaxWidth()

                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
                        modifier = modifier.weight(1f)
                    ) {
                        Text(
                            text = ".......",
                            color = MaterialTheme.colorScheme.outline,
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate(route = Screen.AddExpense.route)
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                        modifier = modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.add_expense),
                            color = MaterialTheme.colorScheme.onTertiary,
                            softWrap = false,
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                            fontSize = 12.sp
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        space = 15.dp,
                        alignment = Alignment.CenterVertically
                    ),
                    modifier = modifier.fillMaxSize()
                ) {
                    Text(
                        text = "No data yet",
                        modifier = modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ExpensesPreview() {
    BudgettingAppTheme {
        Surface (
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            Expenses(navController = rememberNavController())
        }
    }
}