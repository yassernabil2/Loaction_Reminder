package com.udacity.project4.locationreminders.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Unit test the DAO
@SmallTest
class RemindersDaoTest {
    // COMPLETE: Add testing implementation to the RemindersDao.kt

    //Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: RemindersDatabase

    @Before
    fun initDb() {
        //Using an in-memory database so that the information stored here disappears when the
        //process is killed.
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RemindersDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun getReminders() = runBlockingTest {
        //GIVEN - insert a reminder
        val reminder = ReminderDTO(
            "title",
            "description",
            "location",
            (-360..360).random().toDouble(),
            (-360..360).random().toDouble()
        )
        database.reminderDao().saveReminder(reminder)

        //WHEN - Get reminders from the database
        val reminders = database.reminderDao().getReminders()

        //THEN - There is only 1 reminder in the database
        assertThat(reminders.size, `is`(1))
        assertThat(reminders[0].id, `is`(reminder.id))
        assertThat(reminders[0].title, `is`(reminder.title))
        assertThat(reminders[0].description, `is`(reminder.description))
        assertThat(reminders[0].location, `is`(reminder.location))
        assertThat(reminders[0].latitude, `is`(reminder.latitude))
        assertThat(reminders[0].longitude, `is`(reminder.longitude))
    }

    @Test
    fun insertReminder_GetById() = runBlockingTest {
        //Given - insert a reminder
        val reminder = ReminderDTO(
            "title",
            "description",
            "location",
            (-360..360).random().toDouble(),
            (-360..360).random().toDouble()
        )
        database.reminderDao().saveReminder(reminder)

        //WHEN - Get the reminder by id from the database
        val loaded = database.reminderDao().getReminderById(reminder.id)

        //Then - The loaded data contains the expected values
        assertThat<ReminderDTO>(loaded as ReminderDTO, notNullValue())
        assertThat(loaded.id, `is`(reminder.id))
        assertThat(loaded.title, `is`(reminder.title))
        assertThat(loaded.description, `is`(reminder.description))
        assertThat(loaded.location, `is`(reminder.location))
        assertThat(loaded.latitude, `is`(reminder.latitude))
        assertThat(loaded.longitude, `is`(reminder.longitude))
    }

    //WHEN - Get the reminder by id from database and not found
    @Test
    fun getReminderByIdNotFound() = runBlockingTest {
        //GIVEN - a random reminder id
        val reminderId = UUID.randomUUID().toString()
        //WHEN - Get the reminder by id from the database.
        val loaded = database.reminderDao().getReminderById(reminderId)
        //THEN - The loaded data should be null.
        assertNull(loaded)
    }
    @Test
    fun deleteReminders() = runBlockingTest {
        //GIVEN - Many reminders are added
        val remindersList = listOf<ReminderDTO>(
            ReminderDTO(
                "title",
                "description",
                "location",
                (-360..360).random().toDouble(),
                (-360..360).random().toDouble()
            ),
            ReminderDTO(
                "title",
                "description",
                "location",
                (-360..360).random().toDouble(),
                (-360..360).random().toDouble()
            ),
            ReminderDTO(
                "title",
                "description",
                "location",
                (-360..360).random().toDouble(),
                (-360..360).random().toDouble()
            ),
            ReminderDTO(
                "title",
                "description",
                "location",
                (-360..360).random().toDouble(),
                (-360..360).random().toDouble()
            )
        )

        remindersList.forEach {
            database.reminderDao().saveReminder(it)
        }

        //WHEN - All reminder are deleted
        database.reminderDao().deleteAllReminders()

        //THEN - No reminder should remain
        val reminders = database.reminderDao().getReminders()
        assertThat(reminders.isEmpty(), `is`(true))
    }
}