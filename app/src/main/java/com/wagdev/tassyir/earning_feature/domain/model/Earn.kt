package com.wagdev.tassyir.earning_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Earn(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    var name: String,
    var amount: Double=0.0,
    val date: Long=System.currentTimeMillis(),
    val description: String="",
    val category :String="cash"
){
    companion object{
        val SOURCES=listOf<String>("Cash","Bank","Card")
    }
}
