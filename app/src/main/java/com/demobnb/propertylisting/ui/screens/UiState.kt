package com.demobnb.propertylisting.ui.screens

data class UiState(
    val data: String = "Loading...",
    val isLoading: Boolean = false,
    val error: String? = null
)