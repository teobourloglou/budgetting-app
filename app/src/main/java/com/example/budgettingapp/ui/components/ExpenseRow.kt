package com.example.budgettingapp.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgettingapp.data.expense.Expense
import com.example.budgettingapp.data.expense.ExpenseEvent
import com.example.budgettingapp.ui.theme.BudgettingAppTheme

@Composable
fun ExpenseRow(expense: Expense, onEvent: (ExpenseEvent) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        ) {
            Text(
                text = expense.amount,
                color = MaterialTheme.colorScheme.outline,
                fontSize = 20.sp
            )
            Text(
                text = expense.label,
                color = MaterialTheme.colorScheme.outline,
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
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    BudgettingAppTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ExpenseRow(expense = Expense(1, "Nothing Design", "5000.00")) {
                
            }
        }
    }
}