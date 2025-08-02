package com.fazalulabid.skyscannerdemo.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.fazalulabid.skyscannerdemo.core.navigation.BottomNavDestination
import com.fazalulabid.skyscannerdemo.core.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    currentDestination: NavDestination?
) {
    Column {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )

        NavigationBar(
            windowInsets = NavigationBarDefaults.windowInsets,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            tonalElevation = 0.dp
        ) {
            BottomNavDestination.entries.forEach { destination ->
                val isSelected = when (destination.screen) {
                    Screen.Explore -> {
                        currentDestination?.route == Screen.ExploreMain.route ||
                                currentDestination?.route == Screen.ExploreEverywhere.route ||
                                currentDestination?.route == Screen.Explore.route
                    }

                    else -> currentDestination?.route == destination.screen.route
                }

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(destination.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = if (isSelected) destination.selectedIcon else destination.unSelectedIcon
                            ),
                            contentDescription = stringResource(destination.contentDescriptionResId)
                        )
                    },
                    label = {
                        Text(stringResource(destination.labelResId))
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }
}