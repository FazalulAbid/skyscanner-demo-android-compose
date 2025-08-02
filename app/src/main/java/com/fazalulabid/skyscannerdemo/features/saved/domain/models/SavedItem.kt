package com.fazalulabid.skyscannerdemo.features.saved.domain.models

data class SavedItem(
    val id: String,
    val imageUrl: String,
    val route: String,
    val outboundTime: String,
    val inboundTime: String,
    val outboundInfo: String,
    val inboundInfo: String,
    val outboundDuration: String,
    val inboundDuration: String,
    val dateRange: String,
    val travelersInfo: String,
    val priceAlertsEnabled: Boolean,
    val isSaved: Boolean = true
)