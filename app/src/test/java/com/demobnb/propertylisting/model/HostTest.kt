package com.demobnb.propertylisting.model

import com.demobnb.propertylisting.mock.MockData.avatarUrl
import com.demobnb.propertylisting.mock.MockData.faker
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class HostTest {

    @Test
    fun test_hostDurationMonths_six_month_ago() {
        val sixMonthsAgo = LocalDate.now().minusMonths(6)
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
            firstHostAt = sixMonthsAgo,
            introduction = faker.lorem.words()
        )
        assertEquals(6L, user.hostDurationMonths)
    }

    @Test
    fun test_hostDurationMonths_current_month() {
        val currentMonth = LocalDate.now()
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
            firstHostAt = currentMonth,
            introduction = faker.lorem.words()
        )
        assertEquals(0L, user.hostDurationMonths)
    }


}