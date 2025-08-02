package com.fazalulabid.skyscannerdemo.features.booking_site.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewState

data class ChooseBookingSiteViewState(
    val isLoading: Boolean = false,
    val bookingSites: List<String> = emptyList(),
    val selectedSite: String? = null,
    val error: String? = null
) : ViewState