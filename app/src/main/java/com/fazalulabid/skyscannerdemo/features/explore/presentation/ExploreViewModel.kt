package com.fazalulabid.skyscannerdemo.features.explore.presentation

import androidx.compose.ui.graphics.Color
import com.fazalulabid.skyscannerdemo.core.base.BaseViewModel
import com.fazalulabid.skyscannerdemo.features.explore.domain.models.ImageCardItem

class ExploreViewModel : BaseViewModel<ExploreViewState, ExploreIntent>(
    initialState = ExploreViewState()
) {

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        val green = Color(0xFF00A698)
        val blue = Color(0xFF0061E3)

        val imageCards = listOf(
            ImageCardItem(
                "https://images.unsplash.com/photo-1525183995014-bd94c0750cd5?q=80&w=735&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "Ready, set, summer",
                "Explore the world for less"
            ),
            ImageCardItem(
                "https://images.unsplash.com/photo-1506929562872-bb421503ef21?q=80&w=768&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "Beach Vibes",
                "Discover pristine beaches"
            ),
            ImageCardItem(
                "https://images.unsplash.com/photo-1504803298389-9d46a460774a?q=80&w=688&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "Explore Mountains",
                "Adventure awaits in the peaks"
            )
        )

        updateState {
            copy(
                imageCards = imageCards,
            )
        }
    }

    override fun handleIntent(intent: ExploreIntent) {
        when (intent) {
            is ExploreIntent.OnRefresh -> {
                updateState { copy(isLoading = true, error = null) }
                loadInitialData()
                updateState { copy(isLoading = false) }
            }
        }
    }
}