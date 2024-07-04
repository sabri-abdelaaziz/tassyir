package com.wagdev.tassyir.earning_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Earn(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val amount: Double,
    val date: Long,
    val description: String,
    val category :String
){
    companion object{
        val SOURCES=listOf<String>("Cash","Bank","Card")
    }
}
