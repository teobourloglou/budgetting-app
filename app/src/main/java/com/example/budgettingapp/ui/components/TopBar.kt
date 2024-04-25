package com.example.budgettingapp.ui.components

import androidx.annotation.RestrictTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.budgettingapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBarWithDrawer(scope: CoroutineScope, drawerState: DrawerState, title: String) {
    TopBar(
        scope = scope,
        drawerState = drawerState,
        title = title
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scope: CoroutineScope, drawerState: DrawerState, title: String, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                open()
                            }
                        }
                    }
                ){
                    Icon(
                        imageVector = Icons.Outlined.FormatListBulleted,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.fillMaxSize(0.5F)
                    )
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.outline,
                    fontSize = 16.sp
                )
                IconButton(
                    onClick = { /*TODO*/ }
                ){
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.fillMaxSize(0.5F)
                    )
                }
            }
        },
    )
}