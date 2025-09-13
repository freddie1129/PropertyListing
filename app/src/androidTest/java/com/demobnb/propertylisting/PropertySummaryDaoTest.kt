package com.demobnb.propertylisting

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.demobnb.propertylisting.data.local.AppDatabase
import com.demobnb.propertylisting.data.local.PropertySummaryDao
import com.demobnb.propertylisting.mock.MockData
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * Instrumented test class for [PropertySummaryDao].
 * This class tests the database operations for the [PropertySummary] entity,
 */
@RunWith(AndroidJUnit4::class) // Important for instrumented tests
class PropertySummaryDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var propertySummaryDao: PropertySummaryDao

    // Sample data for tests
    private val properties = MockData.generateProperties(5)
    private val sampleProperty1 = properties[1]
    private val sampleProperty2 = properties[2]
    private val sampleProperty3 = properties[3]

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        propertySummaryDao = db.propertySummaryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetProperty() = runBlocking { // Use runBlocking for suspend DAO methods
        propertySummaryDao.insert(sampleProperty1)
        val allProperties = propertySummaryDao.getAll()
        assertEquals(1, allProperties.size)
        assertEquals(sampleProperty1, allProperties[0])
    }

    @Test
    @Throws(Exception::class)
    fun insertAllAndGetAllProperties() = runBlocking {
        val propertiesToInsert = listOf(sampleProperty1, sampleProperty2)
        propertySummaryDao.insertAll(propertiesToInsert)

        val retrievedProperties = propertySummaryDao.getAll()
        assertEquals(2, retrievedProperties.size)
        assertTrue(retrievedProperties.contains(sampleProperty1))
        assertTrue(retrievedProperties.contains(sampleProperty2))
        assertEquals(sampleProperty1, retrievedProperties.find { it.id == sampleProperty1.id })
        assertEquals(sampleProperty2, retrievedProperties.find { it.id == sampleProperty2.id })
    }

    @Test
    @Throws(Exception::class)
    fun deleteAllProperties() = runBlocking {
        propertySummaryDao.insert(sampleProperty1)
        propertySummaryDao.insert(sampleProperty2)

        propertySummaryDao.deleteAll()
        val allProperties = propertySummaryDao.getAll()
        assertTrue(allProperties.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun getAll_whenDbIsEmpty_returnsEmptyList() = runBlocking {
        val allProperties = propertySummaryDao.getAll()
        assertTrue(allProperties.isEmpty())
    }

}