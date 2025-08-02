package com.fazalulabid.skyscannerdemo.features.booking_site.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChooseBookingSiteScreen(
    onBackPressed: () -> Unit,
    onBookingSiteSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Text("Choose Booking Site")

            val bookingSites =
                listOf("SkyScanner", "SkyScanner", "SkyScanner", "SkyScanner", "SkyScanner")

            bookingSites.forEach { site ->
                Button(
                    onClick = onBookingSiteSelected
                ) {
                    Text(site)
                }
            }

            Button(onClick = onBackPressed) {
                Text("Back")
            }
        }
    }
}