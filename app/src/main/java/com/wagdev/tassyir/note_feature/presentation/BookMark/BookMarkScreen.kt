package com.wagdev.tassyir.note_feature.presentation.BookMark

package com.wagdev.tasiyyir.notes_feauture.presentation.bookmark

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wagdev.tasiyyir.notes_feauture.common.ScreenViewState
import com.wagdev.tasiyyir.notes_feauture.data.local.model.Note
import com.wagdev.tasiyyir.notes_feauture.presentation.NoteCard

@Composable
fun BookMarkScreen(
    modifier: Modifier=Modifier,
    state: BookMarkState,
    onBookMarkChange:(Note)->Unit,
    onNoteClicked:(Long)->Unit,
    onDeletedNote:(Note)->Unit
) {

    when(state.notes){
        is ScreenViewState.Loading->{
            CircularProgressIndicator()
        }
        is ScreenViewState.Success->{
            val notes=state.notes.data
            LazyColumn(contentPadding = PaddingValues(4.dp), modifier = Modifier){
                itemsIndexed(notes){item:Int,note:Note->
                    NoteCard(

                        index=item,
                        note=note,
                        onBookMarkChange=onBookMarkChange,
                        onDeletedNote = onDeletedNote,
                        onNoteClicked =onNoteClicked

                    )

                }

            }
        }

        else-> Text("error your notes isnoteavailable",color= MaterialTheme.colorScheme.error)

    }

}