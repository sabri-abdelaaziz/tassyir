package com.wagdev.tassyir.spending_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Spend(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    val category: String,
    val type: String,
    var amount: Double,
    val day: Int = LocalDate.now().dayOfMonth,
    val month: Int = LocalDate.now().monthValue,
    val year: Int = LocalDate.now().year
)