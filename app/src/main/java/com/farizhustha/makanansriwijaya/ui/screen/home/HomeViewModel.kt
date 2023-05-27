package com.farizhustha.makanansriwijaya.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farizhustha.makanansriwijaya.data.FoodRepository
import com.farizhustha.makanansriwijaya.model.Food
import com.farizhustha.makanansriwijaya.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: FoodRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Food>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Food>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        val newData = repo.searchFood(newQuery)
        _uiState.value = UiState.Success(newData)
    }

    fun getAllFood() {
        viewModelScope.launch {
            repo.getAllFood()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { food ->
                    _uiState.value = UiState.Success(food)
                }
        }
    }
}