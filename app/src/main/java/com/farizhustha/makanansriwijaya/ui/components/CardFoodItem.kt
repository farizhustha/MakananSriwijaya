package com.farizhustha.makanansriwijaya.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.farizhustha.makanansriwijaya.ui.theme.Primary
import com.farizhustha.makanansriwijaya.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardFoodItem(
    modifier: Modifier = Modifier,
    photoUrl: String,
    name: String,
    origin: String,
    onClick: () -> Unit

) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            Modifier.padding(8.dp), horizontalAlignment = Alignment.End
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                color = Primary,
                fontSize = 13.sp
            )
            Text(
                text = origin,
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp,
                color = Secondary
            )
        }
    }
}