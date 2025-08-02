package com.fazalulabid.skyscannerdemo.features.explore_everywhere.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewState
import com.fazalulabid.skyscannerdemo.features.explore_everywhere.domain.models.Destination

data class ExploreEverywhereViewState(
    val isLoading: Boolean = false,
    val destinations: List<Destination> = emptyList(),
    val filteredDestinations: List<Destination> = emptyList(),
    val selectedFilter: String = "Cheapest flights",
    val filterChips: List<String> = listOf(
        "Cheapest flights",
        "Direct flights",
        "Suggestions for you",
        "Beach",
        "Art and culture",
        "Great food",
        "Outdoor adventures",
        "Nightlife"
    ),
    val error: String? = null
) : ViewState