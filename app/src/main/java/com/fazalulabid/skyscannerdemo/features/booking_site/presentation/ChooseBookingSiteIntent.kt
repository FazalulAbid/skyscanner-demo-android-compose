package com.fazalulabid.skyscannerdemo.features.booking_site.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewIntent

sealed interface ChooseBookingSiteIntent : ViewIntent {
    data object LoadBookingSites : ChooseBookingSiteIntent
    data class SelectBookingSite(val site: String) : ChooseBookingSiteIntent
}