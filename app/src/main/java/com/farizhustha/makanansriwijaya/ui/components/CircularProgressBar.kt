package com.farizhustha.makanansriwijaya.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.farizhustha.makanansriwijaya.ui.theme.Tertiary


@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    isDisplayed: Boolean
) {
    if (isDisplayed) {
        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Tertiary)
        }

    }
}