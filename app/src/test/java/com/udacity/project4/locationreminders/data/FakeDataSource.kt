package com.udacity.project4.locationreminders.data

import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result

// Use FakeDataSource that acts as a test double to the LocalDataSource which is ReminderDataSource
class FakeDataSource : ReminderDataSource {
    // DONE: Create a fake data source to act as a double to the real data source

    var reminderServiceData: LinkedHashMap<String, ReminderDTO> = LinkedHashMap()

    // Boolean flag for testing errors, I need to artificially cause the error situation.
    // When set to false the test doubles functions as normal, which means by default an error is not returned.
    private var shouldReturnError = false

    // Function for changing flag errors
    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    // Return the reminders
    override suspend fun getReminders(): Result<List<ReminderDTO>> {
        if (shouldReturnError) {
            return Result.Error("Reminders can't be retrieved")
        }
        return Result.Success(reminderServiceData.values.toList())
    }

    // Save the reminder
    override suspend fun saveReminder(reminder: ReminderDTO) {
        reminderServiceData.values.add(reminder)
    }

    // Return the reminder with the id
    override suspend fun getReminder(id: String): Result<ReminderDTO> {
        if (shouldReturnError) {
            return Result.Error("Reminder can't be retrieved")
        }

        // if reminder found with this id
        reminderServiceData[id]?.let {
            return Result.Success(it)
        }

        // if reminder not found with this id
        return Result.Error("Reminder not found")
    }

    // Delete all the reminders
    override suspend fun deleteAllReminders() {
        reminderServiceData.clear()
    }
}