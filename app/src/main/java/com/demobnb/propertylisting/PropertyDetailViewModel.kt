package com.demobnb.propertylisting

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demobnb.propertylisting.model.DataSource
import com.demobnb.propertylisting.model.PropertyDetail

import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Resource
import com.demobnb.propertylisting.repo.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject





@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    val propertyRepository: PropertyRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    private val _propertyDetailState = MutableStateFlow<PropertyDetail?>(null)
    val propertyDetailState: StateFlow<PropertyDetail?> = _propertyDetailState


    private val propertyId: Long = checkNotNull(savedStateHandle["itemId"])

    init {
        loadData(id = propertyId)
    }

    fun resetUiState() {
        _uiState.value = UiState()
    }

    fun loadData(id: Long) {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            _propertyDetailState.value = null
            propertyRepository.fetchPropertyDetails(id)
                .collect { resource ->
                    when (resource) {
                        is Resource.Error<*> -> {

                            _uiState.value = UiState(error = resource.cause.message.orEmpty())
                        }
                        is Resource.Loading<*> -> {
                            if (resource.source == DataSource.REMOTE && _propertyDetailState.value == null) {
                                _uiState.value = UiState(isLoading = true)
                            }
                        }
                        is Resource.Success<PropertyDetail> -> {
                            _uiState.value = UiState(isLoading = false)
                            _propertyDetailState.value = resource.data
                        }
                        }
                    }
                    }
    }
}