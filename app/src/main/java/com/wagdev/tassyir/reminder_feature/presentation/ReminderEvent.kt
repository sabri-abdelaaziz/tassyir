package com.wagdev.tassyir.reminder_feature.presentation

import com.wagdev.tassyir.reminder_feature.domain.model.Reminder

sealed class ReminderEvent {
    object getAllReminders:ReminderEvent()
    data class addEditReminder(val reminder: Reminder):ReminderEvent()
    data class getReminderById(val id:Int):ReminderEvent()
    data class deleteReminder(val reminder:Reminder):ReminderEvent()
    data class insertMessage(val message:String):ReminderEvent()
    data class chooseDate(val date:Long):ReminderEvent()

    data class ToggleLoading(val isLoading: Boolean) : ReminderEvent()


}