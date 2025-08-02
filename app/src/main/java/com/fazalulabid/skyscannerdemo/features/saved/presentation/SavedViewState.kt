package com.fazalulabid.skyscannerdemo.features.saved.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewState
import com.fazalulabid.skyscannerdemo.features.saved.domain.models.SavedItem

data class SavedViewState(
    val isLoading: Boolean = false,
    val savedItems: List<SavedItem> = emptyList(),
    val error: String? = null
) : ViewState