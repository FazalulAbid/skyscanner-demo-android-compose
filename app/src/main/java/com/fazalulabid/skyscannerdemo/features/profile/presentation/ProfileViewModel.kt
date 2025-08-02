package com.fazalulabid.skyscannerdemo.features.profile.presentation

import com.fazalulabid.skyscannerdemo.core.base.BaseViewModel

class ProfileViewModel : BaseViewModel<ProfileViewState, ProfileIntent>(
    initialState = ProfileViewState()
) {

    override fun handleIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.LoadProfile -> {
                updateState { copy(isLoading = true) }
                // Simulate loading profile
                updateState {
                    copy(
                        isLoading = false,
                        userName = "John Doe",
                        email = "john@example.com",
                        isLoggedIn = true
                    )
                }
            }
            is ProfileIntent.Logout -> {
                updateState {
                    copy(
                        userName = "",
                        email = "",
                        isLoggedIn = false
                    )
                }
            }
            is ProfileIntent.UpdateProfile -> {
                updateState {
                    copy(
                        userName = intent.name,
                        email = intent.email
                    )
                }
            }
        }
    }
}