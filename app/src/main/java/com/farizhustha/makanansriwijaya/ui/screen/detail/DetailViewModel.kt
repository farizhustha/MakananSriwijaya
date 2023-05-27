package com.farizhustha.makanansriwijaya.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farizhustha.makanansriwijaya.data.FoodRepository
import com.farizhustha.makanansriwijaya.model.Food
import com.farizhustha.makanansriwijaya.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repo: FoodRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Food>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Food>>
        get() = _uiState

    fun getFoodById(id: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repo.getFoodById(id))
        }
    }
}