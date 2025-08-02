package com.fazalulabid.skyscannerdemo.features.profile.presentation

import com.fazalulabid.skyscannerdemo.core.base.ViewState

data class ProfileViewState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val email: String = "",
    val isLoggedIn: Boolean = false,
    val error: String? = null
) : ViewState