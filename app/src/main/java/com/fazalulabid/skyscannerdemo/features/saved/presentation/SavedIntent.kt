package com.fazalulabid.skyscannerdemo.features.saved.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewIntent

sealed interface SavedIntent : ViewIntent {
    data object LoadSavedItems : SavedIntent
    data class RemoveItem(val itemId: String) : SavedIntent
    data class ToggleSaved(val itemId: String) : SavedIntent
    data class TogglePriceAlert(val itemId: String, val enabled: Boolean) : SavedIntent
}