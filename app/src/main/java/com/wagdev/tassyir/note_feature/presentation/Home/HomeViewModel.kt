package com.wagdev.tassyir.note_feature.presentation.Home

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tasiyyir.notes_feauture.common.ScreenViewState
import com.wagdev.tasiyyir.notes_feauture.data.local.model.Note
import com.wagdev.tasiyyir.notes_feauture.domain.usecases.DeleteUseCase
import com.wagdev.tasiyyir.notes_feauture.domain.usecases.GetAllNotesUseCase
import com.wagdev.tasiyyir.notes_feauture.domain.usecases.UpdateUc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllNotes: GetAllNotesUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val updateUc: UpdateUc
):ViewModel() {

    init {
        getAlNotes()
    }
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private fun getAlNotes() {
        getAllNotes().onEach {
            _state.value = HomeState(notes = ScreenViewState.Success(it))
        }.catch {
            _state.value = HomeState(notes = ScreenViewState.Error(it.message))
        }.launchIn(
            viewModelScope
        )
    }

    fun deleteNote(note: Note) =
        viewModelScope.launch {
            deleteUseCase(note)
        }
    fun onBookMarkChange(note: Note){
        viewModelScope.launch {
            deleteUseCase(note.copy(isBookMarked = !note.isBookMarked))
        }
    }
}

data class HomeState(
    val notes: ScreenViewState<List<Note>> = ScreenViewState.Loading
)