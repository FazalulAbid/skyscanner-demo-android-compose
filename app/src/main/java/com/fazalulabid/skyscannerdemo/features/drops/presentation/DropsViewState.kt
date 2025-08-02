package com.fazalulabid.skyscannerdemo.features.drops.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewState
import com.fazalulabid.skyscannerdemo.features.drops.domain.models.DropDestination

data class DropsViewState(
    val isLoading: Boolean = false,
    val drops: List<DropDestination> = emptyList(),
    val error: String? = null
) : ViewState