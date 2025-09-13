package com.demobnb.propertylisting.model

import org.w3c.dom.Comment
import java.time.LocalDate

data class Review(val id: Long,
                  val propertyId: Long,
                  val userId: Long,
                  val rating: Int,
                    val comment: String,
                    val createAt: LocalDate
) {

}
