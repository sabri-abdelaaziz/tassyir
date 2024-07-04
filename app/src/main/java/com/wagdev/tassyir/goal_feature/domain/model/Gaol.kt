package com.wagdev.tassyir.goal_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val deadline:Long
)
