package com.fazalulabid.skyscannerdemo.features.saved.presentation

import com.fazalulabid.skyscannerdemo.core.base.BaseViewModel
import com.fazalulabid.skyscannerdemo.features.saved.domain.models.SavedItem

class SavedViewModel : BaseViewModel<SavedViewState, SavedIntent>(
    initialState = SavedViewState()
) {

    init {
        handleIntent(SavedIntent.LoadSavedItems)
    }

    override fun handleIntent(intent: SavedIntent) {
        when (intent) {
            is SavedIntent.LoadSavedItems -> {
                updateState { copy(isLoading = true) }
                val dummyItems = createDummyItems()
                updateState { copy(isLoading = false, savedItems = dummyItems) }
            }

            is SavedIntent.RemoveItem -> {
                val updatedList = currentState.savedItems.filter { it.id != intent.itemId }
                updateState { copy(savedItems = updatedList) }
            }

            is SavedIntent.ToggleSaved -> {
                val item = currentState.savedItems.find { it.id == intent.itemId }
                if (item?.isSaved == true) {
                    val updatedList = currentState.savedItems.filter { it.id != intent.itemId }
                    updateState { copy(savedItems = updatedList) }
                }
            }

            is SavedIntent.TogglePriceAlert -> {
                val updatedList = currentState.savedItems.map { item ->
                    if (item.id == intent.itemId) {
                        item.copy(priceAlertsEnabled = intent.enabled)
                    } else {
                        item
                    }
                }
                updateState { copy(savedItems = updatedList) }
            }
        }
    }

    private fun createDummyItems(): List<SavedItem> {
        return listOf(
            SavedItem(
                id = "1",
                imageUrl = "https://images.unsplash.com/photo-1582407947304-fd86f028f716?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2096&q=80",
                route = "Dubai to Bucharest",
                outboundTime = "21:20 — 01:35+1",
                inboundTime = "14:30 — 20:35",
                outboundInfo = "DXB–OTP, Wizz Air Malta",
                inboundInfo = "OTP–DXB, Wizz Air Malta",
                outboundDuration = "5h 15m",
                inboundDuration = "5h 5m",
                dateRange = "4 – 7 Sept",
                travelersInfo = "1 adult, Economy",
                priceAlertsEnabled = true,
                isSaved = true
            ),
            SavedItem(
                id = "2",
                imageUrl = "https://images.unsplash.com/photo-1539650116574-75c0c6d73c6e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                route = "London to Paris",
                outboundTime = "08:15 — 11:30",
                inboundTime = "18:45 — 19:55",
                outboundInfo = "LHR–CDG, British Airways",
                inboundInfo = "CDG–LHR, Air France",
                outboundDuration = "1h 15m",
                inboundDuration = "1h 10m",
                dateRange = "12 – 15 Oct",
                travelersInfo = "2 adults, Business",
                priceAlertsEnabled = false,
                isSaved = true
            ),
            SavedItem(
                id = "3",
                imageUrl = "https://images.unsplash.com/photo-1513475382585-d06e58bcb0e0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                route = "New York to Tokyo",
                outboundTime = "13:45 — 17:20+1",
                inboundTime = "19:30 — 22:15",
                outboundInfo = "JFK–NRT, Japan Airlines",
                inboundInfo = "NRT–JFK, United Airlines",
                outboundDuration = "13h 35m",
                inboundDuration = "12h 45m",
                dateRange = "20 – 28 Nov",
                travelersInfo = "1 adult, Economy",
                priceAlertsEnabled = true,
                isSaved = true
            ),
            SavedItem(
                id = "4",
                imageUrl = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                route = "Sydney to Singapore",
                outboundTime = "22:10 — 04:25+1",
                inboundTime = "16:40 — 06:15+1",
                outboundInfo = "SYD–SIN, Singapore Airlines",
                inboundInfo = "SIN–SYD, Jetstar Airways",
                outboundDuration = "8h 15m",
                inboundDuration = "7h 35m",
                dateRange = "5 – 12 Dec",
                travelersInfo = "3 adults, 1 child, Economy",
                priceAlertsEnabled = false,
                isSaved = true
            ),
            SavedItem(
                id = "5",
                imageUrl = "https://images.unsplash.com/photo-1580159670574-6d8b3d49a17b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2070&q=80",
                route = "Istanbul to Barcelona",
                outboundTime = "06:30 — 09:45",
                inboundTime = "11:20 — 16:35",
                outboundInfo = "IST–BCN, Turkish Airlines",
                inboundInfo = "BCN–IST, Pegasus Airlines",
                outboundDuration = "4h 15m",
                inboundDuration = "4h 15m",
                dateRange = "18 – 22 Jan",
                travelersInfo = "2 adults, Economy",
                priceAlertsEnabled = true,
                isSaved = true
            )
        )
    }
}