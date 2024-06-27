package com.wagdev.tassyir.task_feature.domain.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String,
    val description: String,
    val status: Boolean=false,
    val color: Int ,
    val dueDate:Long=System.currentTimeMillis()+3600*602*24
)
