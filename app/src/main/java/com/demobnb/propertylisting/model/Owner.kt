package com.demobnb.propertylisting.model

import java.time.LocalDate


data class Owner(val id: Long,
                 val name: String,
                 val avatar: String,
                 val feature: String,
                 val address: String,
                 val reviewCount: Int,
                 val averageRate: Float,
                 val createdAt: LocalDate,
                 val introduction: String
    )
