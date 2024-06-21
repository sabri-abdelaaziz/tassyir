package com.wagdev.tassyir.note_feature.presentation.BookMark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tassyir.note_feature.common.ScreenViewState
import com.wagdev.tassyir.note_feature.data.local.model.Note
import com.wagdev.tassyir.note_feature.domain.usecases.DeleteUseCase
import com.wagdev.tassyir.note_feature.domain.usecases.FilterBookMarkUc
import com.wagdev.tassyir.note_feature.domain.usecases.UpdateUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val updateNoteUsecase:UpdateUc,
    private val filterBookMark:FilterBookMarkUc,
    private val deleteUseCase:DeleteUseCase
):ViewModel(){
    private val _state:MutableStateFlow<BookMarkState> =MutableStateFlow(BookMarkState())
    val state:StateFlow<BookMarkState> =_state.asStateFlow()

    private  fun getBookMarkNotes(){
        filterBookMark().onEach {
            _state.value=BookMarkState(
                notes = ScreenViewState.Success(it)
            )
        }.catch {
            _state.value=BookMarkState(notes = ScreenViewState.Error(it.message))
        }.launchIn(
            viewModelScope
        )
    }

    fun onBookMarkChange(note:Note){
        viewModelScope.launch {
            updateNoteUsecase(
                note.copy(
                    isBookMarked = !note.isBookMarked
                )
            )
        }
    }

    fun onDeleteNote(note: Note){
        viewModelScope.launch {
            onDeleteNote(note)
        }
    }


}
data class BookMarkState(
    val notes:ScreenViewState<List<Note>> = ScreenViewState.Loading
)