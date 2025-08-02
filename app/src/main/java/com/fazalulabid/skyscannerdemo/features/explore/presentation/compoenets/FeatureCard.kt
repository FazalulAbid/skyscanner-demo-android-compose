package com.fazalulabid.skyscannerdemo.features.explore.presentation.compoenets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fazalulabid.skyscannerdemo.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeatureCardList(
    modifier: Modifier = Modifier,
    onExploreEverywhereClick: () -> Unit,
    onPerksClick: () -> Unit,
    onSavvySearchClick: () -> Unit,
    onHotHotelDealsClick: () -> Unit,
    onWhyChooseSkyscannerClick: () -> Unit,
) {
    val green = Color(0xFF00A698)
    val blue = Color(0xFF0061E3)

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                FeatureCard(
                    iconResId = R.drawable.ic_explore,
                    label = "Explore everywhere",
                    iconColor = green,
                    onClick = onExploreEverywhereClick
                )
            }

            item {
                FeatureCard(
                    iconResId = R.drawable.ic_perks,
                    label = "Perks",
                    iconColor = blue,
                    onClick = onPerksClick
                )
            }

            item {
                FeatureCard(
                    iconResId = R.drawable.ic_star,
                    label = "Savvy Search",
                    iconColor = blue,
                    onClick = onSavvySearchClick
                )
            }

            item {
                FeatureCard(
                    iconResId = R.drawable.ic_tag,
                    label = "Hot hotel deals",
                    iconColor = blue,
                    onClick = onHotHotelDealsClick
                )
            }

            item {
                FeatureCard(
                    iconResId = R.drawable.ic_help,
                    label = "Why choose Skyscanner?",
                    iconColor = blue,
                    onClick = onWhyChooseSkyscannerClick
                )
            }
        }
    }
}

@Composable
fun FeatureCard(
    iconResId: Int,
    label: String,
    iconColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val iconBackgroundColor = lerp(Color.White, iconColor, 0.2f)

    Column(
        modifier = modifier
            .size(width = 160.dp, height = 120.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(iconBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = label,
                modifier = Modifier.size(18.dp),
                colorFilter = ColorFilter.tint(iconColor)
            )
        }

        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
            lineHeight = 18.sp
        )
    }
}
