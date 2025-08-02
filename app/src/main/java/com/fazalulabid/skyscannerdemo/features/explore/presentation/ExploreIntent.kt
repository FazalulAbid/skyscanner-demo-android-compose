package com.fazalulabid.skyscannerdemo.features.explore.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewIntent

sealed interface ExploreIntent : ViewIntent {
    data object OnRefresh : ExploreIntent
}