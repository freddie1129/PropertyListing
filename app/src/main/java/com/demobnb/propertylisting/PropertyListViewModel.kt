package com.demobnb.propertylisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demobnb.propertylisting.model.DataSource

import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Resource
import com.demobnb.propertylisting.repo.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



data class UiState(
    val data: String = "Loading...",
    val isLoading: Boolean = false,
    val error: String? = null
)

data class PropertyListState(
    val properties: List<PropertySummary> = emptyList()
)

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    val propertyRepository: PropertyRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    private val _propertyListState = MutableStateFlow<List<PropertySummary>>(emptyList())
    val propertyListState: StateFlow<List<PropertySummary>> = _propertyListState


    init {
        loadData()
    }

    fun resetUiState() {
        _uiState.value = UiState()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            _propertyListState.value = emptyList()
            propertyRepository.fetchPropertiesList()
                .collect { resource ->
                    when (resource) {
                        is Resource.Error<*> -> {

                            _uiState.value = UiState(error = resource.cause.message.orEmpty())
                        }
                        is Resource.Loading<*> -> {
                            if (resource.source == DataSource.REMOTE && _propertyListState.value.isEmpty()) {
                                _uiState.value = UiState(isLoading = true)
                            }
                        }
                        is Resource.Success<List<PropertySummary>> -> {
                            _uiState.value = UiState(isLoading = false)
                            _propertyListState.value = resource.data.orEmpty()
                        }
                        }
                    }
                    }
    }
}