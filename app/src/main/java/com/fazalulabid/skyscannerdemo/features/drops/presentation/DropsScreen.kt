package com.fazalulabid.skyscannerdemo.features.drops.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.fazalulabid.skyscannerdemo.features.drops.presentation.components.CollapsedToolbar
import com.fazalulabid.skyscannerdemo.features.drops.presentation.components.DropDestinationCard
import com.fazalulabid.skyscannerdemo.features.drops.presentation.components.LocationAndPersonCountSection
import com.fazalulabid.skyscannerdemo.features.drops.presentation.components.DropsTopBar
import kotlin.math.roundToInt

@Composable
fun DropsScreen(
    modifier: Modifier = Modifier,
    viewModel: DropsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyListState()
    val density = LocalDensity.current

    val thresholdPx = with(density) { (32.dp + 48.dp).toPx() }

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

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                DropsTopBar()
            }
            item {
                LocationAndPersonCountSection(
                    location = "Dubai",
                    userCount = 2,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Today's just-dropped prices",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Return flights that have dropped by at least 20%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
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
                    items = state.drops,
                    key = { destination -> "${destination.name}_${destination.startDate}" }
                ) { destination ->
                    DropDestinationCard(
                        destination = destination,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            if (!state.isLoading && state.drops.isNotEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 56.dp, start = 16.dp, end = 16.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "We're all out, drop by again later.",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            if (state.error != null) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.error ?: "",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }

        CollapsedToolbar(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(toolbarAlpha)
                .offset { IntOffset(0, toolbarOffset.roundToInt()) }
                .zIndex(1f)
        )
    }
}