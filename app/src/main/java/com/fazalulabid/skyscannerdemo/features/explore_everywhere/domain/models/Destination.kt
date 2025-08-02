package com.fazalulabid.skyscannerdemo.features.explore_everywhere.domain.models

data class Destination(
    val id: String,
    val country: String,
    val info: String,
    val price: String,
    val imageUrl: String,
    val category: DestinationCategory,
    val isDirect: Boolean = false,
    val priceValue: Int
)

enum class DestinationCategory {
    CHEAPEST,
    DIRECT,
    SUGGESTIONS,
    BEACH,
    ART_CULTURE,
    GREAT_FOOD,
    OUTDOOR_ADVENTURES,
    NIGHTLIFE
}