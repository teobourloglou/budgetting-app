package com.example.budgettingapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.budgettingapp.ui.components.TopBarWithDrawer
import kotlinx.coroutines.CoroutineScope

@Composable
public fun ScreenContent(
    scope: CoroutineScope,
    drawerState: DrawerState,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { TopBarWithDrawer(scope = scope, drawerState = drawerState) }
    ) { innerPadding ->
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .padding(8.dp)
                .padding(innerPadding)
                .fillMaxHeight()
        ) {
            content()
        }
    }
}