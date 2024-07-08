package com.wagdev.tassyir.reminder_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reminder(
    @PrimaryKey val id :Int,
    val message: String = "",
    val date: Long = 0L
)