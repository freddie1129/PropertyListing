package com.demobnb.propertylisting.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.w3c.dom.Comment
import java.time.LocalDate

@Entity(tableName = "review")
data class Review(
    @PrimaryKey
    val id: Long,
                  val propertyId: Long,
                  val userId: Long,
                  val rating: Int,
                    val comment: String,
                    val createAt: LocalDate
) {

}
