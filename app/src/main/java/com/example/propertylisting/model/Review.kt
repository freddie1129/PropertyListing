package com.example.propertylisting.model

import javax.inject.Inject

data class Review(val id: Long,
                  val propertyId: Long,
                  val rating: Int,)
