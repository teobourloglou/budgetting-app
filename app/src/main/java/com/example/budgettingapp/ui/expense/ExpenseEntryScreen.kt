package com.example.budgettingapp.ui.expense

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.budgettingapp.data.expense.Expense
import com.example.budgettingapp.data.expense.ExpenseEvent
import com.example.budgettingapp.data.expense.ExpenseState
import com.example.budgettingapp.data.expense.currentDate
import com.example.budgettingapp.ui.ScreenContent
import com.example.budgettingapp.ui.components.DrawerContent
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
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
            val date = remember { mutableStateOf(LocalDate.now()) }

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
                            contentDescription = "Label"
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
                TextField(
                    value = state.date,
                    onValueChange = {
                        onEvent(ExpenseEvent.SetDate(it))
                    },
                    textStyle = TextStyle.Default.copy(
                        fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                        fontSize = 16.sp
                    ),
                    label = {
                        Text(
                            text = "Date",
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
                              imageVector = Icons.Outlined.CalendarToday,
                              contentDescription = "Date"
                          )
                    },
                    placeholder = {
                        Text(text = "yyyy-MM-dd")
                    }
                )
//                CustomDatePicker(
//                    value = state.date,
//                    onValueChange = {
//                        date.value = it
//                        ExpenseEvent.SetDate(date.value.toString())
//                    }
//                )
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

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    value: String,
    onValueChange: (LocalDate) -> Unit
) {

    val open = remember { mutableStateOf(false)}

    if (open.value) {
        CalendarDialog(
            state = rememberUseCaseState(visible = true, true, onCloseRequest = { open.value = false } ),
            config = CalendarConfig(
                yearSelection = true,
                style = CalendarStyle.MONTH,
            ),
            selection = CalendarSelection.Date(
                selectedDate = LocalDate.parse(value, DateTimeFormatter.ISO_DATE)
            ) { newDate ->
                onValueChange(newDate)
            },
        )
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                open.value = true
            },
        enabled = false,
        value = value.format(DateTimeFormatter.ISO_DATE) ,
        onValueChange = {},
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            disabledBorderColor = MaterialTheme.colorScheme.outline,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.CalendarToday,
                contentDescription = "Date"
            )
        },
        textStyle = TextStyle.Default.copy(
            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
            fontSize = 16.sp
        ),
        label = {
            Text(
                text = "Date",
                modifier = Modifier.background(Color.Transparent),
                fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
            )
        }
    )
}