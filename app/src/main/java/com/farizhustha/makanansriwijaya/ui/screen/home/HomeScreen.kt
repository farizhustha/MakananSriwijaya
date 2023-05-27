package com.farizhustha.makanansriwijaya.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.farizhustha.makanansriwijaya.R
import com.farizhustha.makanansriwijaya.model.Food
import com.farizhustha.makanansriwijaya.ui.ViewModelFactory
import com.farizhustha.makanansriwijaya.ui.common.UiState
import com.farizhustha.makanansriwijaya.ui.components.CardFoodItem
import com.farizhustha.makanansriwijaya.ui.components.CircularProgressBar
import com.farizhustha.makanansriwijaya.ui.components.SearchBar
import com.farizhustha.makanansriwijaya.ui.theme.Primary

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory()
    ),
    navigateToDetail: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
            .padding(30.dp, 30.dp, 30.dp, 0.dp)
    ) {
        val query by viewModel.query

        Banner(query = query) { newQuery ->
            viewModel.search(newQuery)
        }

        Spacer(modifier = Modifier.height(30.dp))

        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllFood()
                    CircularProgressBar(isDisplayed = true)
                }
                is UiState.Success -> {
                    HomeContent(listFood = uiState.data, navigateToDetail = navigateToDetail)
                }
                is UiState.Error -> {}
            }
        }

    }

}

@Composable
fun Banner(
    query: String,
    onQueryChange: (String) -> Unit

) {
    Text(
        stringResource(R.string.title_home_screen),
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(14.dp))

    SearchBar(value = query, onValueChange = onQueryChange)
}


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    listFood: List<Food>,
    navigateToDetail: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 30.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        items(items = listFood, key = { it.id }) { item ->
            CardFoodItem(
                photoUrl = item.photoUrl,
                name = item.name,
                origin = item.origin
            ) {
                navigateToDetail(item.id)
            }
        }
    }
}

