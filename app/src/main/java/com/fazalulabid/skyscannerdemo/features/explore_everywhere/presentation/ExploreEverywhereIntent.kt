package com.fazalulabid.skyscannerdemo.features.explore_everywhere.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewIntent

sealed interface ExploreEverywhereIntent : ViewIntent {
    data object LoadDestinations : ExploreEverywhereIntent
    data class OnFilterSelected(val filter: String) : ExploreEverywhereIntent
}