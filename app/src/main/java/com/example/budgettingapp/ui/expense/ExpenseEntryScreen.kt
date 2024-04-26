package com.example.budgettingapp.ui.expense

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.budgettingapp.R
import com.example.budgettingapp.Screen
import com.example.budgettingapp.data.expense.ExpenseEvent
import com.example.budgettingapp.data.expense.ExpenseState
import com.example.budgettingapp.ui.ScreenContent
import com.example.budgettingapp.ui.components.DrawerContent

@Composable
fun ExpenseEntry(
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
            title = stringResource(R.string.add_expense),
            modifier = modifier.padding(5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 40.dp,
                    alignment = Alignment.Bottom
                ),
                modifier = modifier
                    .padding(
                        top = 100.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
            )
            {
                TextField(
                    value = state.label,
                    onValueChange = {
                        onEvent(ExpenseEvent.SetLabel(it))
                    },
                    textStyle = TextStyle.Default.copy(
                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                        fontSize = 16.sp
                    ),
                    label = {
                        Text(
                            text = "Label",
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                            modifier = modifier.background(Color.Transparent)
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                        cursorColor = MaterialTheme.colorScheme.secondary,
                        focusedContainerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Label,
                            contentDescription = "Amount"
                        )
                    }
                )
                TextField(
                    value = state.amount,
                    onValueChange = {
                        onEvent(ExpenseEvent.SetAmount(it))
                    },
                    textStyle = TextStyle.Default.copy(
                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                        fontSize = 16.sp
                    ),
                    label = {
                        Text(
                            text = "Amount",
                            modifier = modifier.background(Color.Transparent),
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                        focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                        cursorColor = MaterialTheme.colorScheme.secondary,
                        focusedContainerColor = MaterialTheme.colorScheme.secondary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = {
                          Icon(
                              imageVector = Icons.Outlined.Payments,
                              contentDescription = "Amount"
                          )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        space = 5.dp,
                        alignment = Alignment.Bottom
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.weight(2f)
                ) {
                    Button(
                        onClick = {
                            onEvent(ExpenseEvent.SaveExpense).also {
                                navController.navigate(route = Screen.Expenses.route)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Save",
                            color = Color.Black,
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate(route = Screen.Expenses.route)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Cancel",
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun AddExpensePreview() {
//    BudgettingAppTheme {
//        Surface (
//            color = MaterialTheme.colorScheme.background,
//            modifier = Modifier.fillMaxSize()
//        ) {
//            ExpenseEntry(navController = rememberNavController())
//        }
//    }
//}