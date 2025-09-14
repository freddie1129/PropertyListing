package com.demobnb.propertylisting.mock

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import io.github.serpro69.kfaker.faker
import java.time.LocalDate
import kotlin.random.Random

/**
 * A singleton object that provides mock data for various models in the application.
 * This is useful for testing, development, and previewing UI components without
 * relying on a live backend or database.
 *
 * It uses the `kfaker` library to generate realistic-looking fake data.
 */
object MockData {

    val faker = faker { }

    val imageUrl = "https://picsum.photos/200"

    val avatarUrl = "https://picsum.photos/id/65/200/200"

    val longSentence = Array(500) { faker.lorem.words() }.joinToString(" ")
    val shortSentence = Array(10) { faker.lorem.words() }.joinToString(" ")

    fun generateImageUrls(id: Long) =
        (id..id + 5).map { "https://picsum.photos/id/${it}/400/200" }.toList()

    fun generateReviews(count: Int) = (1..count).map {
        Review(
            id = it.toLong(),
            propertyId = it.toLong(),
            userId = it.toLong(),
            rating = 3,
            comment = Array(10) { faker.lorem.words() }.joinToString(" "),
            createAt = LocalDate.now().minusYears(it.toLong()),
        )
    }

    fun generateUser(id: Long) = User(
        id = id,
        firstName = faker.name.firstName(),
        lastName = faker.name.lastName(),
        avatar = avatarUrl,
        feature = faker.lorem.words(),
        address = faker.address.fullAddress(),
        reviewCount = faker.random.nextInt(1, 100),
        averageRate = faker.random.nextFloat() * 5,
        createdAt = LocalDate.now().minusYears(3),
        firstHostAt = LocalDate.now().minusYears(2),
        introduction = shortSentence
    )

    fun generateReview(id: Long, propertyId: Long, userId: Long) = Review(
        id = id,
        propertyId = propertyId,
        userId = userId,
        rating = (faker.random.nextFloat() * 5).toInt(),
        comment = faker.lorem.words(),
        createAt = LocalDate.now().minusYears(id),
    )


    fun generateProperties(count: Int): List<PropertySummary> {
        return (1..count).map {
            PropertySummary(
                id = it.toLong(),
                userId = it.toLong(),
                title = faker.house.rooms(),
                featureImage = imageUrl,
                extendedPrice = faker.random.nextFloat() * 100,
                checkInDate = LocalDate.now(),
                checkOutDate = LocalDate.now().plusDays(3),
                rate = Random.nextFloat() * 5f
            )
        }
    }


    val user = User(
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

    val userWith3YearHosting = User(
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

    val userWith6MonthHosting = User(
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
            user = Random.nextLong(),
            highlights = listOf(1, 2, 3, 4, 5),
            introduction = longSentence,
            longitude = 27.9769f,
            latitude = 153.3809f,
            neighborHighlights = faker.lorem.words(),
            availabilityStart = LocalDate.now(),
            availabilityEnd = LocalDate.now().plusDays(30),
            cancellationPolicy = 123L,
        )
    }


}

