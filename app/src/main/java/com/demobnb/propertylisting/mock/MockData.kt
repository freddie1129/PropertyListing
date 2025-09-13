package com.demobnb.propertylisting.mock

import com.demobnb.propertylisting.model.Host
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import io.github.serpro69.kfaker.faker
import java.time.LocalDate
import kotlin.random.Random

object MockData {

    val faker = faker {  }

    val imageUrl = "https://picsum.photos/200"

    val avatarUrl = "https://picsum.photos/id/65/200/200"

    val longSentence = Array(500) { faker.lorem.words() }.joinToString(" ")
    val shortSentence = Array(10) { faker.lorem.words() }.joinToString(" ")

    fun generateImageUrls(id: Long) = (id..id + 5).map { "https://picsum.photos/id/${it}/400/200" }.toList()

    fun generateReviews(count: Int) = (1..count).map {
        Review(
            id = it.toLong(),
            propertyId = it.toLong(),
            userId = it.toLong(),
            rating = 3,
            comment = "A good place to stay",
            createAt = LocalDate.now().minusYears(it.toLong()),
        )
    }

    fun generateUser(id: Long) = User(
        id = faker.random.nextLong(),
        name = faker.name.firstName(),
        avatar = avatarUrl,
        createdAt = LocalDate.now().minusYears(1)
    )
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


    val host = Host(
        id = 12L,
        firstName = faker.name.firstName(),
        lastName = faker.name.lastName(),
        avatar = avatarUrl,
        feature = "SupperHost",
        address = faker.address.fullAddress(),
        reviewCount = 123,
        averageRate = 4.37f,
        createdAt = LocalDate.now().minusYears(5),
        firstHostAt = LocalDate.now().minusYears(3),
        introduction = faker.lorem.words()
    )

    val hostWith3YearHosting = Host(
        id = 12L,
        firstName = faker.name.firstName(),
        lastName = faker.name.lastName(),
        avatar = avatarUrl,
        feature = "SupperHost",
        address = faker.address.fullAddress(),
        reviewCount = 123,
        averageRate = 4.37f,
        createdAt = LocalDate.now().minusYears(5),
        firstHostAt = LocalDate.now().minusYears(3),
        introduction = faker.lorem.words()
    )

    val hostWith6MonthHosting = Host(
        id = 12L,
        firstName = faker.name.firstName(),
        lastName = faker.name.lastName(),
        avatar = avatarUrl,
        feature = "SupperHost",
        address = faker.address.fullAddress(),
        reviewCount = 123,
        averageRate = 4.37f,
        createdAt = LocalDate.now().minusYears(5),
        firstHostAt = LocalDate.now().minusMonths(6),
        introduction = faker.lorem.words()
    )

    fun generatePropertyDetail(id: Long): PropertyDetail {
        return PropertyDetail(
            id = id,
            title = faker.house.rooms(),
            guestCount = Random.nextInt(1, 10),
            bedroomCount = Random.nextInt(1, 3),
            bedCount = Random.nextInt(1, 3),
            bathCount = Random.nextInt(1, 3),
            featureImages = generateImageUrls(id),
            address = faker.address.fullAddress(),
            averageRate = Random.nextFloat() * 5f,
            reviewStandout = "Guest favourite",
            reviewCount = 100,
            host = host,
            highlights = listOf(1, 2, 3, 4, 5),
            introduction =  longSentence,
            facilities = listOf(),
            longitude = 27.9769f,
            latitude = 153.3809f,
            neighborHighlights = faker.lorem.words(),
            availabilityStart = LocalDate.now(),
            availabilityEnd = LocalDate.now().plusDays(30),
            cancellationPolicy = 123L,
            reviews = listOf()
        )
    }





    }

