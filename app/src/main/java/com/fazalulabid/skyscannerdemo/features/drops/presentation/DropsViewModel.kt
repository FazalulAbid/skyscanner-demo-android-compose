package com.fazalulabid.skyscannerdemo.features.drops.presentation

import com.fazalulabid.skyscannerdemo.core.base.BaseViewModel
import com.fazalulabid.skyscannerdemo.features.drops.domain.models.DropDestination

class DropsViewModel : BaseViewModel<DropsViewState, DropsIntent>(
    initialState = DropsViewState()
) {

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        updateState {
            copy(
                isLoading = false,
                drops = getDummyDropDestinations()
            )
        }
    }

    override fun handleIntent(intent: DropsIntent) {
        when (intent) {
            is DropsIntent.LoadDrops -> {
                updateState { copy(isLoading = true, error = null) }
                loadInitialData()
            }

            is DropsIntent.RefreshDrops -> {
                updateState { copy(isLoading = true, error = null) }
                loadInitialData()
            }
        }
    }

    private fun getDummyDropDestinations(): List<DropDestination> {
        return listOf(
            DropDestination(
                name = "Thiruvananthapuram",
                country = "India",
                imageUrl = "https://images.unsplash.com/photo-1582407947304-fd86f028f716?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2096&q=80",
                priceDropAmount = "42,436",
                startDate = "Wed, 3 Sep",
                endDate = "Mon, 8 Sep",
                nights = 5,
                originalPrice = "76,993",
                discountedPrice = "34,548"
            ),
            DropDestination(
                name = "Kochi",
                country = "India",
                imageUrl = "https://images.unsplash.com/photo-1578662996442-48f60103fc96?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                priceDropAmount = "28,150",
                startDate = "Thu, 11 Sep",
                endDate = "Sun, 15 Sep",
                nights = 4,
                originalPrice = "65,280",
                discountedPrice = "37,130"
            ),
            DropDestination(
                name = "Mumbai",
                country = "India",
                imageUrl = "https://images.unsplash.com/photo-1570168007204-dfb528c6958f?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2062&q=80",
                priceDropAmount = "35,920",
                startDate = "Fri, 19 Sep",
                endDate = "Wed, 24 Sep",
                nights = 5,
                originalPrice = "82,450",
                discountedPrice = "46,530"
            ),
            DropDestination(
                name = "Goa",
                country = "India",
                imageUrl = "https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2074&q=80",
                priceDropAmount = "31,680",
                startDate = "Sat, 27 Sep",
                endDate = "Thu, 2 Oct",
                nights = 5,
                originalPrice = "71,200",
                discountedPrice = "39,520"
            ),
            DropDestination(
                name = "Bangalore",
                country = "India",
                imageUrl = "https://images.unsplash.com/photo-1596176530529-78163bd65052?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2074&q=80",
                priceDropAmount = "25,890",
                startDate = "Mon, 6 Oct",
                endDate = "Fri, 10 Oct",
                nights = 4,
                originalPrice = "58,320",
                discountedPrice = "32,430"
            )
        )
    }
}