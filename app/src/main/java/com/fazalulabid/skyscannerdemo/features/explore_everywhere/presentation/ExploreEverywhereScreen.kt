package com.fazalulabid.skyscannerdemo.features.explore_everywhere.presentation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fazalulabid.skyscannerdemo.features.explore_everywhere.domain.models.Destination
import com.fazalulabid.skyscannerdemo.features.explore_everywhere.presentation.components.ChipRow
import com.fazalulabid.skyscannerdemo.features.explore_everywhere.presentation.components.ExploreEveryWhereTopBar
import com.fazalulabid.skyscannerdemo.features.explore_everywhere.presentation.components.ExploreEverywhereCard

@Composable
fun ExploreEverywhereScreen(
    onBackPressed: () -> Unit,
    onSelectDestination: (Destination) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExploreEverywhereViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(vertical = 16.dp)
        ) {
            Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                ExploreEveryWhereTopBar(
                    from = "Mumbai",
                    to = "Everywhere",
                    fromDate = "Anytime",
                    toDate = "Anytime",
                    onBackClick = onBackPressed
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Explore Everywhere",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                item {
                    ChipRow(
                        chipItems = state.filterChips,
                        selectedChip = state.selectedFilter,
                        onChipSelected = { filter ->
                            viewModel.handleIntent(ExploreEverywhereIntent.OnFilterSelected(filter))
                        }
                    )
                }

                if (state.isLoading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                } else {
                    items(
                        items = state.filteredDestinations,
                        key = { it.id }
                    ) { destination ->
                        androidx.compose.animation.AnimatedVisibility(
                            visible = true,
                            enter = androidx.compose.animation.fadeIn(tween(300)) +
                                    androidx.compose.animation.slideInVertically(tween(300)),
                            exit = androidx.compose.animation.fadeOut(tween(300)) +
                                    androidx.compose.animation.slideOutVertically(tween(300))
                        ) {
                            ExploreEverywhereCard(
                                destination = destination,
                                onDestinationClick = { destination ->
                                    onSelectDestination(destination)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}