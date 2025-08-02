package com.fazalulabid.skyscannerdemo.features.explore_everywhere.presentation

import com.fazalulabid.skyscannerdemo.core.base.BaseViewModel
import com.fazalulabid.skyscannerdemo.features.explore_everywhere.domain.models.Destination
import com.fazalulabid.skyscannerdemo.features.explore_everywhere.domain.models.DestinationCategory

class ExploreEverywhereViewModel :
    BaseViewModel<ExploreEverywhereViewState, ExploreEverywhereIntent>(
        initialState = ExploreEverywhereViewState()
    ) {

    private val dummyDestinations = listOf(
        Destination(
            id = "1",
            country = "Thailand",
            info = "Flights • Direct",
            price = "₹28,450",
            priceValue = 28450,
            imageUrl = "https://images.unsplash.com/photo-1552465011-b4e21bf6e79a?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.BEACH,
            isDirect = true
        ),
        Destination(
            id = "2",
            country = "Japan",
            info = "Flights • 1 stop",
            price = "₹45,200",
            priceValue = 45200,
            imageUrl = "https://images.unsplash.com/photo-1540959733332-eab4deabeeaf?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.ART_CULTURE,
            isDirect = false
        ),
        Destination(
            id = "3",
            country = "France",
            info = "Flights • Direct",
            price = "₹52,800",
            priceValue = 52800,
            imageUrl = "https://images.unsplash.com/photo-1502602898536-47ad22581b52?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.ART_CULTURE,
            isDirect = true
        ),
        Destination(
            id = "4",
            country = "Maldives",
            info = "Flights • 1 stop",
            price = "₹35,600",
            priceValue = 35600,
            imageUrl = "https://images.unsplash.com/photo-1573843981267-be1999ff37cd?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.BEACH,
            isDirect = false
        ),
        Destination(
            id = "5",
            country = "Italy",
            info = "Flights • Direct",
            price = "₹48,900",
            priceValue = 48900,
            imageUrl = "https://images.unsplash.com/photo-1552832230-c0197dd311b5?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.GREAT_FOOD,
            isDirect = true
        ),
        Destination(
            id = "6",
            country = "Nepal",
            info = "Flights • Direct",
            price = "₹15,200",
            priceValue = 15200,
            imageUrl = "https://images.unsplash.com/photo-1605640840605-14ac1855827b?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.OUTDOOR_ADVENTURES,
            isDirect = true
        ),
        Destination(
            id = "7",
            country = "Spain",
            info = "Flights • 1 stop",
            price = "₹42,300",
            priceValue = 42300,
            imageUrl = "https://images.unsplash.com/photo-1558642452-9d2a7deb7f62?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.NIGHTLIFE,
            isDirect = false
        ),
        Destination(
            id = "8",
            country = "Sri Lanka",
            info = "Flights • Direct",
            price = "₹12,800",
            priceValue = 12800,
            imageUrl = "https://images.unsplash.com/photo-1566073771259-6a8506099945?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.CHEAPEST,
            isDirect = true
        ),
        Destination(
            id = "9",
            country = "UAE",
            info = "Flights • Direct",
            price = "₹22,400",
            priceValue = 22400,
            imageUrl = "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.SUGGESTIONS,
            isDirect = true
        ),
        Destination(
            id = "10",
            country = "Indonesia",
            info = "Flights • 1 stop",
            price = "₹32,100",
            priceValue = 32100,
            imageUrl = "https://images.unsplash.com/photo-1537953773345-d172ccf13cf1?ixlib=rb-4.0.3&auto=format&fit=crop&w=2096&q=80",
            category = DestinationCategory.BEACH,
            isDirect = false
        )
    )

    init {
        handleIntent(ExploreEverywhereIntent.LoadDestinations)
    }

    override fun handleIntent(intent: ExploreEverywhereIntent) {
        when (intent) {
            is ExploreEverywhereIntent.LoadDestinations -> {
                updateState {
                    copy(
                        isLoading = true,
                        destinations = dummyDestinations,
                        filteredDestinations = filterDestinations(dummyDestinations, selectedFilter)
                    )
                }
                updateState { copy(isLoading = false) }
            }

            is ExploreEverywhereIntent.OnFilterSelected -> {
                val filteredList = filterDestinations(currentState.destinations, intent.filter)
                updateState {
                    copy(
                        selectedFilter = intent.filter,
                        filteredDestinations = filteredList
                    )
                }
            }
        }
    }

    private fun filterDestinations(
        destinations: List<Destination>,
        filter: String
    ): List<Destination> {
        return when (filter) {
            "Cheapest flights" -> destinations.sortedBy { it.priceValue }
            "Direct flights" -> destinations.filter { it.isDirect }
            "Suggestions for you" -> destinations.filter { it.category == DestinationCategory.SUGGESTIONS }
            "Beach" -> destinations.filter { it.category == DestinationCategory.BEACH }
            "Art and culture" -> destinations.filter { it.category == DestinationCategory.ART_CULTURE }
            "Great food" -> destinations.filter { it.category == DestinationCategory.GREAT_FOOD }
            "Outdoor adventures" -> destinations.filter { it.category == DestinationCategory.OUTDOOR_ADVENTURES }
            "Nightlife" -> destinations.filter { it.category == DestinationCategory.NIGHTLIFE }
            else -> destinations
        }
    }
}