package com.example.budgettingapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(amount: Int, amountCents: Int, navController: NavController, modifier: Modifier = Modifier) {
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
            var selected by remember { mutableIntStateOf(0) }
            MoneyAmount(
                amount = amount,
                amountCents = amountCents,
                modifier = Modifier
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 15.dp,
                    alignment = Alignment.Bottom
                ),
                modifier = modifier
                    .padding(8.dp)
            ) {
                TimeSelector(
                    selected = selected,
                    onTimeSelected = { newSelected -> selected = newSelected },
                    modifier = modifier
                )
                ActionGroup(navController = navController)
            }
        }
    }
}

@Composable
fun MoneyAmount(amount: Int, amountCents: Int, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(
            space = 2.dp,
            alignment = Alignment.CenterHorizontally
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 100.dp)
    ) {
        Text(
            text = "$",
            textAlign = TextAlign.Right,
            fontSize = 36.sp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(top = 12.dp)
        )
        Text(
            text = "$amount",
            fontSize = 110.sp,
            textAlign = TextAlign.Center,
            lineHeight = 110.sp,
            modifier = modifier
        )
        Text(
            text = ".$amountCents",
            textAlign = TextAlign.Right,
            fontSize = 36.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}


@Composable
fun TimeSelector(modifier: Modifier = Modifier, selected : Int, onTimeSelected: (Int) -> Unit) {
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
            onClick = { onTimeSelected(0) },
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
            onClick = { onTimeSelected(1) },
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
            onClick = { onTimeSelected(2) },
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
                    navController.navigate(route = Screen.AddExpense.route)
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

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreview() {
    BudgettingAppTheme {
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Home(
                amount = 300,
                amountCents = 0,
                navController = rememberNavController()
            )
        }
    }
}