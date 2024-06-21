package com.wagdev.tassyir.note_feature.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wagdev.tassyir.note_feature.data.local.model.Note


@Composable
fun NoteCard(
    index:Int,
    note: Note,
    onBookMarkChange:(Note)->Unit,
    onNoteClicked:(Long)->Unit,
    onDeletedNote:(Note)->Unit
) {
    val isEventIndex=index%2==0
    val shape=when{
        isEventIndex -> RoundedCornerShape(topStart = 50f, bottomEnd = 50f)
        else -> {
            RoundedCornerShape(topEnd = 50f, bottomStart = 50f)
        }
    }
    val icon=if (note.isBookMarked) Icons.Default.BookmarkRemove else Icons.Default.BookmarkAdded
    Card(
        modifier= Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = shape,
        onClick = {onNoteClicked(note.id)}
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(note.title, fontWeight = FontWeight.Bold, maxLines = 1, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(4.dp))
            Text(note.content, maxLines = 4, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodyMedium)
            Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = {
                    onDeletedNote(note)
                }){
                    Icon(Icons.Default.Delete,contentDescription = null)
                }
                IconButton(onClick = {
                    onBookMarkChange(note)
                }){
                    Icon(icon,contentDescription = null)
                }
            }
        }

    }
}
