package com.wagdev.tassyir.note_feature.presentation.Home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.wagdev.tasiyyir.notes_feauture.common.ScreenViewState
import com.wagdev.tasiyyir.notes_feauture.data.local.model.Note
import com.wagdev.tasiyyir.notes_feauture.presentation.NoteCard



@Composable
fun HomeScreen(
    modifier: Modifier=Modifier,
    state: HomeState,
    onBookMarkChange: (Note) -> Unit,
    onDeletedNote: (Note) -> Unit,
    onNoteClicked: (Long) -> Unit
) {
    when(state.notes){
        is ScreenViewState.Success->{
            val notes=state.notes.data
            HomeDetail(notes = notes,
                modifier=modifier,
                onBookMarkChange =onBookMarkChange ,
                onNoteClicked =onNoteClicked ,
                onDeletedNote =onDeletedNote )
        }
        is ScreenViewState.Loading-> CircularProgressIndicator()
        else->Text("error your notes isnoteavailable",color=MaterialTheme.colorScheme.error)
    }

}


@Composable
private fun HomeDetail(
    notes:List<Note>,
    modifier: Modifier=Modifier,
    onBookMarkChange:(Note)->Unit,
    onNoteClicked:(Long)->Unit,
    onDeletedNote:(Note)->Unit
) {

    LazyVerticalStaggeredGrid(columns =StaggeredGridCells.Fixed(2) , contentPadding = PaddingValues(4.dp), modifier = modifier){
        itemsIndexed(notes){
                index,note->

            NoteCard(index = index,
                note = note,
                onBookMarkChange =onBookMarkChange ,
                onNoteClicked =onNoteClicked ,
                onDeletedNote = onDeletedNote)
        }
    }

}


