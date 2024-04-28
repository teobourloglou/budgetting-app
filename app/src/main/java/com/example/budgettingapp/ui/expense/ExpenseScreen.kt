package com.example.budgettingapp.ui.expense

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.budgettingapp.R
import com.example.budgettingapp.Screen
import com.example.budgettingapp.data.expense.Expense
import com.example.budgettingapp.data.expense.ExpenseEvent
import com.example.budgettingapp.data.expense.ExpenseState
import com.example.budgettingapp.ui.ScreenContent
import com.example.budgettingapp.ui.components.DrawerContent


@Composable
fun Expenses(
    navController: NavController,
    state: ExpenseState,
    onEvent: (ExpenseEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val showDialog = remember { mutableStateOf(false) }

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
                        border = BorderStroke(1.5.dp, colorScheme.secondary),
                        colors = ButtonDefaults.buttonColors(colorScheme.background),
                        modifier = modifier.weight(1f)
                    ) {
                        Text(
                            text = ".......",
                            color = colorScheme.outline,
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate(route = Screen.ExpenseEntry.route)
                        },
                        colors = ButtonDefaults.buttonColors(colorScheme.tertiary),
                        modifier = modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(R.string.add_expense),
                            color = colorScheme.onTertiary,
                            softWrap = false,
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                            fontSize = 12.sp
                        )
                    }
                }
                var expenseId by remember { mutableIntStateOf(0) }
                var previousDate by remember { mutableStateOf("") }

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

                            if (previousDate != expense.date) {
                                Row() {
                                    Text(expense.date)
                                }
                            }
                            previousDate = expense.date

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .background(
                                        color = colorScheme.secondary,
                                        shape = RoundedCornerShape(15.dp)
                                    )
                                    .padding(5.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        expenseId = expense.id
                                        showDialog.value = true
                                    }
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = expense.amount,
                                        color = colorScheme.outline,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        text = expense.label,
                                        color = colorScheme.outline,
                                        fontSize = 14.sp,
                                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily
                                    )
                                    Text(
                                        text = expense.date,
                                        color = colorScheme.outline,
                                        fontSize = 14.sp,
                                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily
                                    )
                                }
                                IconButton(onClick = {
                                    onEvent(ExpenseEvent.DeleteExpense(expense))
                                }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Clear,
                                        contentDescription = "Delete expense",
                                        tint = colorScheme.outline,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }

                    }
                }
                if (showDialog.value) {
                    var label by remember { mutableStateOf("") }
                    var amount by remember { mutableStateOf("") }
                    var date by remember { mutableStateOf("") }

                    Dialog(onDismissRequest = { /*TODO*/ }) {
                        Card() {
                            Text(text = expenseId.toString())
                            TextField(
                                value = label,
                                onValueChange = { label = it },
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
                                    focusedLabelColor = colorScheme.tertiary,
                                    focusedIndicatorColor = colorScheme.tertiary,
                                    cursorColor = colorScheme.secondary,
                                    focusedContainerColor = colorScheme.secondary,
                                    unfocusedContainerColor = colorScheme.background,
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                singleLine = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Label,
                                        contentDescription = "Label"
                                    )
                                }
                            )
                            TextField(
                                value = amount,
                                onValueChange = { amount = it },
                                label = {
                                    Text(
                                        text = "Amount",
                                        modifier = modifier.background(Color.Transparent),
                                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                                    )
                                },
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
                            TextField(
                                value = date,
                                onValueChange = {date = it },
                                label = {
                                    Text(
                                        text = "Date",
                                        modifier = modifier.background(Color.Transparent),
                                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                                    )
                                },
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
                            //                            CustomDatePicker(
                            //                                value = expense.date
                            //                                onValueChange = {
                            //                                    state.date = it
                            //                                    ExpenseEvent.SetDate(state.date.toString())
                            //                                }
                            //                            )
                            Button(onClick = {
                                val newExpense = Expense(expenseId, label, amount, date)
                                onEvent(ExpenseEvent.UpdateExpense(newExpense))
                                showDialog.value = false
                            }) {
                                Text("Save")
                            }
                        }
                    }
                }
            }
        }
    }
}