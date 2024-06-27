package com.wagdev.tassyir.note_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wagdev.noteapp.ui.theme.BabyBlue
import com.wagdev.noteapp.ui.theme.LightGreen
import com.wagdev.noteapp.ui.theme.RedOrange
import com.wagdev.noteapp.ui.theme.RedPink
import com.wagdev.noteapp.ui.theme.Violet
import java.sql.Timestamp


@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id :Int? = null,
    val title :String,
    val content :String,
    val color: Int ,
    val timestamp: Long,
){
    companion object{
        val noteColors =listOf(RedOrange,LightGreen,Violet,BabyBlue,RedPink)
    }
}

class InvalidNoteException(message:String):Exception(message)
