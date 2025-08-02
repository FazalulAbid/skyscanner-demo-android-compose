package com.fazalulabid.skyscannerdemo.features.booking_site.presentation

import com.fazalulabid.skyscannerdemo.core.base.BaseViewModel

class ChooseBookingSiteViewModel :
    BaseViewModel<ChooseBookingSiteViewState, ChooseBookingSiteIntent>(
        initialState = ChooseBookingSiteViewState()
    ) {

    override fun handleIntent(intent: ChooseBookingSiteIntent) {
        when (intent) {
            is ChooseBookingSiteIntent.LoadBookingSites -> {
                updateState { copy(isLoading = true) }
                val sites = listOf("Expedia", "Booking.com", "Kayak", "Priceline", "Skyscanner")
                updateState { copy(isLoading = false, bookingSites = sites) }
            }

            is ChooseBookingSiteIntent.SelectBookingSite -> {
                updateState { copy(selectedSite = intent.site) }
            }
        }
    }
}