package com.example.budgettingapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
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
import com.example.budgettingapp.ui.components.DrawerContent
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    modifier: Modifier = Modifier,
    state: ExpenseState,
    onEvent: (ExpenseEvent) -> Unit
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
            title = stringResource(R.string.overview),
            modifier = modifier
        ) {

            var modalHidden by remember { mutableStateOf(false) }
            var selected by remember { mutableIntStateOf(0) }
            var amountInteger by remember { mutableStateOf(totalAmount(state)[0].toInt().toString()) }
            var amountDecimal by remember { mutableStateOf(((totalAmount(state)[0] - totalAmount(state)[0].toInt()) * 100).toInt().toString()) }

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 2.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 100.dp)
                    .height(IntrinsicSize.Max)
            ) {
                if (modalHidden) {
                    Text(
                        text = "$",
                        textAlign = TextAlign.Right,
                        fontSize = 36.sp,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    Text(
                        text = amountInteger,
                        fontSize = 110.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 110.sp,
                        modifier = modifier
                    )
                    Text(
                        text = amountDecimal,
                        textAlign = TextAlign.Right,
                        fontSize = 36.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                } else {
                    Text(
                        text = "Show Total Expenses",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                selected = 0
                                amountInteger = totalAmount(state)[0].toInt().toString()
                                amountDecimal = (totalAmount(state)[0] - totalAmount(state)[0].toInt()).toString()
                                modalHidden = true
                            },
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                        lineHeight = 40.sp
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 15.dp,
                    alignment = Alignment.Bottom
                ),

                modifier = modifier
                    .padding(8.dp)
            ) {
                Row (
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 10.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(horizontal = 5.dp)

                ) {
                    Button(
                        onClick = {
                            selected = 0
                            amountInteger = totalAmount(state)[0].toInt().toString()
                            amountDecimal = (totalAmount(state)[0] - totalAmount(state)[0].toInt()).toString()
                        },
                        colors = if (selected == 0) ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary) else ButtonDefaults.buttonColors(
                            MaterialTheme.colorScheme.secondary),
                        modifier = modifier.weight(1f)
                    ) {
                        Text(
                            stringResource(R.string.week),
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                            softWrap = false,
                            color = if (selected == 0) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    Button(
                        onClick = {
                            selected = 1
                            amountInteger = totalAmount(state)[1].toInt().toString()
                            amountDecimal = (totalAmount(state)[1] - totalAmount(state)[1].toInt()).toString()
                        },
                        colors = if (selected == 1) ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary) else ButtonDefaults.buttonColors(
                            MaterialTheme.colorScheme.secondary),
                        modifier = modifier.weight(1f)
                    ) {
                        Text(
                            stringResource(R.string.month),
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                            softWrap = false,
                            color = if (selected == 1) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onSecondary
                        )
                    }
                    Button(
                        onClick = {
                            selected = 2
                            amountInteger = totalAmount(state)[2].toInt().toString()
                            amountDecimal = (totalAmount(state)[2] - totalAmount(state)[2].toInt()).toString()
                        },
                        colors = if (selected == 2) ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary) else ButtonDefaults.buttonColors(
                            MaterialTheme.colorScheme.secondary),
                        modifier = modifier.weight(1f)
                    ) {
                        Text(
                            stringResource(R.string.year),
                            fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                            softWrap = false,
                            color = if (selected == 2) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
                ActionGroup(navController = navController)
            }
        }
    }
}


@Composable
fun ActionGroup(modifier: Modifier = Modifier, navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterHorizontally
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
                .aspectRatio(1f)
        ) {
            Text(
                text = "TO DO CHARTS",
                softWrap = false,
                fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 15.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
                .height(IntrinsicSize.Max)
        ) {
            Button(
                onClick = {
                    navController.navigate(route = Screen.ExpenseEntry.route)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                Text (
                    text = stringResource(R.string.add_expense),
                    softWrap = false,
                    fontFamily = MaterialTheme.typography.labelSmall.fontFamily,
                )
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
            ) {

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun totalAmount(
    state: ExpenseState
): Array<Double> {
    val currentDate = LocalDate.now()

    val startOfWeek = currentDate.minusDays(6)
    val endOfWeek = currentDate

    val startOfMonth = currentDate.withDayOfMonth(1)
    val endOfMonth = startOfMonth.plusMonths(1).minusDays(1)

    val startOfYear = currentDate.withDayOfYear(1)
    val endOfYear = startOfYear.plusYears(1).minusDays(1)

    val weekSum = calculateExpensesSum(state.expenses, startOfWeek, endOfWeek)
    val monthSum = calculateExpensesSum(state.expenses, startOfMonth, endOfMonth)
    val yearSum = calculateExpensesSum(state.expenses, startOfYear, endOfYear)

    return arrayOf(weekSum, monthSum, yearSum)
}

@RequiresApi(Build.VERSION_CODES.O)
fun calculateExpensesSum(expenses: List<Expense>, startDate: LocalDate, endDate: LocalDate): Double {
    return expenses.asSequence()
        .filter { it.date != null && it.amount != null }
        .filter { isValidDate(it.date) }
        .mapNotNull { expense ->
            val expenseDate = parseLocalDate(expense.date)
            if (expenseDate != null && expenseDate in startDate..endDate) {
                expense.amount.toDoubleOrNull()
            } else {
                null
            }
        }
        .sum()
}


@RequiresApi(Build.VERSION_CODES.O)
fun isValidDate(dateString: String): Boolean {
    return try {
        LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun parseLocalDate(dateString: String?): LocalDate? {
    return if (dateString != null) {
        try {
            LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
        } catch (e: DateTimeParseException) {
            null
        }
    } else {
        null
    }
}
