package com.fazalulabid.skyscannerdemo.core.navigation

import com.fazalulabid.skyscannerdemo.R

sealed class Screen(val route: String) {
    data object Explore : Screen("explore_nav")
    data object Drops : Screen("drops")
    data object Saved : Screen("saved")
    data object Profile : Screen("profile")

    data object ExploreMain : Screen("explore_main")
    data object ExploreEverywhere : Screen("explore_everywhere")

    data object ChooseBookingSite : Screen("choose_booking_site")
}

enum class BottomNavDestination(
    val screen: Screen,
    val labelResId: Int,
    val unSelectedIcon: Int,
    val selectedIcon: Int,
    val contentDescriptionResId: Int
) {
    EXPLORE(
        screen = Screen.Explore,
        labelResId = R.string.explore,
        unSelectedIcon = R.drawable.ic_search,
        selectedIcon = R.drawable.ic_search_filled,
        contentDescriptionResId = R.string.explore
    ),
    DROPS(
        screen = Screen.Drops,
        labelResId = R.string.drops,
        unSelectedIcon = R.drawable.ic_drops,
        selectedIcon = R.drawable.ic_drops_filled,
        contentDescriptionResId = R.string.drops
    ),
    SAVED(
        screen = Screen.Saved,
        labelResId = R.string.saved,
        unSelectedIcon = R.drawable.ic_heart,
        selectedIcon = R.drawable.ic_heart_filled,
        contentDescriptionResId = R.string.saved
    ),
    PROFILE(
        screen = Screen.Profile,
        labelResId = R.string.profile,
        unSelectedIcon = R.drawable.ic_profile,
        selectedIcon = R.drawable.ic_profile_filled,
        contentDescriptionResId = R.string.profile
    )
}