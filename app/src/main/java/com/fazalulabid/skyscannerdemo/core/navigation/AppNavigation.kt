package com.fazalulabid.skyscannerdemo.core.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.fazalulabid.skyscannerdemo.core.ui.components.BottomNavigationBar
import com.fazalulabid.skyscannerdemo.features.booking_site.presentation.ChooseBookingSiteScreen
import com.fazalulabid.skyscannerdemo.features.drops.presentation.DropsScreen
import com.fazalulabid.skyscannerdemo.features.explore.presentation.ExploreScreen
import com.fazalulabid.skyscannerdemo.features.explore_everywhere.presentation.ExploreEverywhereScreen
import com.fazalulabid.skyscannerdemo.features.profile.presentation.ProfileScreen
import com.fazalulabid.skyscannerdemo.features.saved.presentation.SavedScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val layoutDirection = LocalLayoutDirection.current

    val showBottomBar by remember(currentDestination?.route) {
        derivedStateOf {
            when (currentDestination?.route) {
                Screen.ChooseBookingSite.route -> false
                else -> true
            }
        }
    }

    val animatedBottomPadding by animateDpAsState(
        targetValue = if (showBottomBar) 80.dp else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "bottomPadding"
    )

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 300)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = 300)
                )
            ) {
                BottomNavigationBar(
                    navController = navController,
                    currentDestination = currentDestination
                )
            }
        }
    ) { paddingValues ->
        val customPaddingValues = PaddingValues(
            start = paddingValues.calculateStartPadding(layoutDirection),
            top = paddingValues.calculateTopPadding(),
            end = paddingValues.calculateEndPadding(layoutDirection),
            bottom = animatedBottomPadding
        )

        NavHost(
            navController = navController,
            startDestination = Screen.Explore.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(customPaddingValues)
        ) {
            navigation(
                startDestination = Screen.ExploreMain.route,
                route = Screen.Explore.route
            ) {
                composable(Screen.ExploreMain.route) {
                    ExploreScreen(
                        onNavigateToExploreEverywhere = {
                            navController.navigate(Screen.ExploreEverywhere.route)
                        },
                        onNavigateToBookingSite = {
                            navController.navigate(Screen.ChooseBookingSite.route)
                        }
                    )
                }

                composable(Screen.ExploreEverywhere.route) {
                    ExploreEverywhereScreen(
                        onBackPressed = {
                            navController.popBackStack()
                        },
                        onSelectDestination = {
                            navController.navigate(Screen.ChooseBookingSite.route)
                        }
                    )
                }
            }

            composable(Screen.Drops.route) {
                DropsScreen()
            }

            composable(Screen.Saved.route) {
                SavedScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(Screen.ChooseBookingSite.route) {
                ChooseBookingSiteScreen(
                    onBackPressed = {
                        navController.popBackStack()
                    },
                    onBookingSiteSelected = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}