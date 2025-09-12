package com.demobnb.propertylisting.model

data class PropertyPhoto(val id: Long,
    val title: String,
    val subtitle: String,
    val urls: List<String>)
