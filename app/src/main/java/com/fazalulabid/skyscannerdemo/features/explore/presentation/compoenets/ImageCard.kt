package com.fazalulabid.skyscannerdemo.features.explore.presentation.compoenets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.fazalulabid.skyscannerdemo.features.explore.domain.models.ImageCardItem

@Composable
fun ImageCard(
    item: ImageCardItem,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.8f)
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White
                )
            )
        }
    }
}

@Composable
fun ImageCardSection(
    items: List<ImageCardItem>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.forEach { item ->
            ImageCard(item = item)
        }
    }
}