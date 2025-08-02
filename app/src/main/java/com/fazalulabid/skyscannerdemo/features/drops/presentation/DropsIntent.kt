package com.fazalulabid.skyscannerdemo.features.drops.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewIntent

sealed interface DropsIntent : ViewIntent {
    data object LoadDrops : DropsIntent
    data object RefreshDrops : DropsIntent
}