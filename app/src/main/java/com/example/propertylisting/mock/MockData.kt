package com.example.propertylisting.mock

import com.example.propertylisting.di.Local
import com.example.propertylisting.model.Owner
import com.example.propertylisting.model.PropertyDetail
import com.example.propertylisting.model.PropertySummary
import io.github.serpro69.kfaker.faker
import java.time.LocalDate
import kotlin.random.Random

object MockData {

    val faker = faker {  }

    val imageUrl = "https://picsum.photos/200"




    fun generateProperties(count: Int): List<PropertySummary> {
       return (1..count).map {
            PropertySummary(
                id = it.toLong(),
                title = faker.house.rooms(),
                featureImage = imageUrl,
                extendedPrice = faker.random.nextFloat() * 100,
                checkInDate = LocalDate.now(),
                checkOutDate = LocalDate.now().plusDays(3),
                rate = Random.nextFloat() * 5f
            )
        }
    }


    val owner = Owner(
        id = 12L,
        name = faker.name.firstName(),
        avatar = imageUrl,
        feature = "SupperHost",
        address = faker.address.fullAddress(),
        reviewCount = 123,
        averageRate = 4.37f,
        createdAt = LocalDate.now().minusYears(5),
        introduction = faker.lorem.words()
    )

    fun generatePropertyDetail(id: Long): PropertyDetail {
        return PropertyDetail(
            id = id,
            title = "Property $id",
            featureImages = listOf(imageUrl, imageUrl),
            address = faker.address.fullAddress(),
            averageRate = 4.5f,
            reviewCount = 100,
            owner = owner,
            introduction = faker.lorem.words(),
            amenities = listOf(),
            longitude = 123.456f,
            latitude = 123.456f,
            neighborHighlights = faker.lorem.words(),
            availabilityStart = LocalDate.now(),
            availabilityEnd = LocalDate.now().plusDays(30),
            cancellationPolicy = 123L,
            reviews = listOf()
        )
    }





    }

