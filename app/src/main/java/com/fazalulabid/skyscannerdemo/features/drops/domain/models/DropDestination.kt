package com.fazalulabid.skyscannerdemo.features.drops.domain.models

data class DropDestination(
    val name: String,
    val country: String,
    val imageUrl: String,
    val priceDropAmount: String,
    val startDate: String,
    val endDate: String,
    val nights: Int,
    val originalPrice: String,
    val discountedPrice: String
)