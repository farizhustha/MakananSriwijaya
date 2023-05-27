package com.farizhustha.makanansriwijaya.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.farizhustha.makanansriwijaya.ui.ViewModelFactory
import com.farizhustha.makanansriwijaya.ui.common.UiState
import com.farizhustha.makanansriwijaya.ui.theme.Primary
import com.farizhustha.makanansriwijaya.ui.theme.Secondary
import com.farizhustha.makanansriwijaya.ui.theme.Tertiary

@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory()
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFoodById(id)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.photoUrl,
                    data.name,
                    data.origin,
                    data.description,
                    navigateBack

                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    photoUrl: String,
    name: String,
    origin: String,
    description: String,
    onBackClick: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        Banner(photoUrl = photoUrl, onBackClick = onBackClick)
        Body(name = name, origin = origin, description = description)
    }
}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
    photoUrl: String,
    onBackClick: () -> Unit
) {
    Box(modifier) {
        AsyncImage(
            model = photoUrl, contentDescription = "Food Photo",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentScale = ContentScale.Crop
        )
        Icon(imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = "Back Button",
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Primary)
                .padding(5.dp)
                .clickable { onBackClick() })
    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier,
    name: String,
    origin: String,
    description: String
) {
    Column(
        modifier = modifier
            .offset(y = (-40).dp)
            .clip(RoundedCornerShape(40.dp, 40.dp))
            .background(Color.White)
            .padding(16.dp, 40.dp, 16.dp, 16.dp)
    ) {
        Text(
            name,
            fontSize = 24.sp,
            color = Primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            origin,
            fontSize = 10.sp,
            color = Secondary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            description,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = Tertiary
        )
    }

}