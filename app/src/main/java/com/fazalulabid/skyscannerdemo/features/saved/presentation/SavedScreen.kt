package com.fazalulabid.skyscannerdemo.features.saved.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fazalulabid.skyscannerdemo.features.saved.presentation.components.SavedCollapsedTopBar
import com.fazalulabid.skyscannerdemo.features.saved.presentation.components.SavedDropCard
import com.fazalulabid.skyscannerdemo.features.saved.presentation.components.SavedTopBar
import kotlin.math.roundToInt

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    viewModel: SavedViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyListState()
    val density = LocalDensity.current

    val thresholdPx = with(density) { (100.dp + 32.dp).toPx() }

    val showCollapsedToolbar by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0 ||
                    (listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset > thresholdPx)
        }
    }

    val toolbarAlpha by animateFloatAsState(
        targetValue = if (showCollapsedToolbar) 1f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "toolbarAlpha"
    )

    val toolbarOffset by animateFloatAsState(
        targetValue = if (showCollapsedToolbar) 0f else -60f,
        animationSpec = tween(durationMillis = 300),
        label = "toolbarOffset"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SavedTopBar()
            }

            item {
                Box(Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "Added yesterday",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
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
                    items = state.savedItems,
                    key = { it.id }
                ) { item ->
                    SavedDropCard(
                        item = item,
                        onSavedToggle = { itemId ->
                            viewModel.handleIntent(SavedIntent.ToggleSaved(itemId))
                        },
                        onPriceAlertToggle = { itemId, enabled ->
                            viewModel.handleIntent(SavedIntent.TogglePriceAlert(itemId, enabled))
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }

        SavedCollapsedTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(toolbarAlpha)
                .offset { IntOffset(0, toolbarOffset.roundToInt()) }
                .zIndex(1f)
        )
    }
}