package com.fazalulabid.skyscannerdemo.features.explore.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewState
import com.fazalulabid.skyscannerdemo.features.explore.domain.models.ImageCardItem

data class ExploreViewState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val imageCards: List<ImageCardItem> = emptyList(),
) : ViewState