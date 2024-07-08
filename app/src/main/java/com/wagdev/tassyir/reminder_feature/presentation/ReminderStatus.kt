package com.wagdev.tassyir.reminder_feature.presentation

import com.wagdev.tassyir.reminder_feature.domain.model.Reminder

data class ReminderStatus(
    val reminders:List<Reminder> = emptyList(),
    val reminder:Reminder? = Reminder(id=0,message="",date=0),
    val isLoading:Boolean = false,
    val error:String? = null
)