package com.demobnb.propertylisting.model

import java.time.LocalDate

data class User(val id: Long,
    val name: String,
    val avatar: String,
    val createdAt: LocalDate
)
