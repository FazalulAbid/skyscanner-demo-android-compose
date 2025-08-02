package com.fazalulabid.skyscannerdemo.features.explore.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fazalulabid.skyscannerdemo.R
import com.fazalulabid.skyscannerdemo.features.explore.presentation.compoenets.FeatureCardList
import com.fazalulabid.skyscannerdemo.features.explore.presentation.compoenets.ImageCardSection
import com.fazalulabid.skyscannerdemo.features.explore.presentation.compoenets.TravelOptionsSection

@Composable
fun ExploreScreen(
    onNavigateToExploreEverywhere: () -> Unit,
    onNavigateToBookingSite: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val travelOptions = listOf(
        Pair(R.drawable.ic_flight, R.string.flights),
        Pair(R.drawable.ic_hotel, R.string.hotels),
        Pair(R.drawable.ic_car, R.string.car_hire)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
        ) {
            Box(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_name_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.height(38.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Spacer(Modifier.height(24.dp))

            TravelOptionsSection(
                options = travelOptions,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(24.dp))

            FeatureCardList(
                onExploreEverywhereClick = onNavigateToExploreEverywhere,
                onPerksClick = { },
                onSavvySearchClick = { },
                onHotHotelDealsClick = { },
                onWhyChooseSkyscannerClick = { }
            )

            Spacer(Modifier.height(24.dp))

            ImageCardSection(items = state.imageCards)
        }
    }
}