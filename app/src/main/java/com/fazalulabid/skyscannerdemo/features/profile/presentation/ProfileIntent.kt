package com.fazalulabid.skyscannerdemo.features.profile.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewIntent

sealed interface ProfileIntent : ViewIntent {
    data object LoadProfile : ProfileIntent
    data object Logout : ProfileIntent
    data class UpdateProfile(val name: String, val email: String) : ProfileIntent
}