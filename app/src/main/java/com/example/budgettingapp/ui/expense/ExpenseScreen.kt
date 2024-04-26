package com.example.budgettingapp.ui.expense

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.budgettingapp.R
import com.example.budgettingapp.Screen
import com.example.budgettingapp.data.expense.Expense
import com.example.budgettingapp.data.expense.ExpenseEvent
import com.example.budgettingapp.data.expense.ExpenseState
import com.example.budgettingapp.ui.ScreenContent
import com.example.budgettingapp.ui.components.DrawerContent
import com.example.budgettingapp.ui.components.ExpenseRow


@Composable
fun Expenses(
    navController: NavController,
    state: ExpenseState,
    onEvent: (ExpenseEvent) -> Unit,
    modifier: Modifier = Modifier
) {
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
                            navController.navigate(route = Screen.ExpenseEntry.route)
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
                if (state.expenses.isEmpty()) {
                    Text(
                        text = "No data yet",
                        modifier = modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(
                            space = 15.dp,
                            alignment = Alignment.Top
                        ),
                        modifier = modifier.fillMaxSize()
                    ) {
                        items(state.expenses) { expense ->
                            ExpenseRow(expense = expense) {}
                        }
                    }
                }
            }
        }
    }
}