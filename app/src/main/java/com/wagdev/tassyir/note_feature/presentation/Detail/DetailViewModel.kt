package com.wagdev.tassyir.note_feature.presentation.Detail

package com.wagdev.tasiyyir.notes_feauture.presentation.Detail

import android.icu.text.CaseMap.Title
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.wagdev.tasiyyir.notes_feauture.data.local.model.Note
import com.wagdev.tasiyyir.notes_feauture.domain.usecases.AddUseCases
import com.wagdev.tasiyyir.notes_feauture.domain.usecases.AddUseCases_Factory
import com.wagdev.tasiyyir.notes_feauture.domain.usecases.GetNoteByIdUc
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*


class DetailViewModel @AssistedInject constructor(
    private val addUseCases: AddUseCases,
    private val getNoteByIdUc: GetNoteByIdUc,
    @Assisted private val noteId:Long
) :ViewModel(){
    var state by mutableStateOf(DetailState())
        private set
    val isFromNotBlank:Boolean
        get()=state.title.isNotEmpty() && state.content.isNotEmpty()
    val note :Note
        get()=state.run {
            Note(
                id,
                title,
                content,
                createdDate
            )
        }

    private fun initialise(){
        val isUpdatingNote=noteId!=-1L
        state=state.copy(
            isUpdatingNote=isUpdatingNote
        )
        if (isUpdatingNote){
            getNoteById()
        }

    }

    private fun getNoteById()=viewModelScope.launch {
        getNoteByIdUc(noteId).collectLatest {
                note->
            state=state.copy(
                id=state.id,
                title=state.title,
                content = state.content,
                createdDate = state.createdDate,
                isBookMark = state.isBookMark
            )
        }
    }

    fun onTileChange(title: String){
        state= state.copy(
            title = title
        )

    }
    fun onContentChange(content: String){
        state= state.copy(
            content = content
        )

    }
    fun onBookMarkChange(book: Boolean){
        state= state.copy(
            isBookMark = book
        )

    }
    fun addOrUpdateNote()=viewModelScope.launch {
        addUseCases(note=note)
    }

}
@Suppress("UNCHECKED_CAST")
class DetailedViewModelFactory(
    private val noteId: Long,
    private val assistedFactory: DetailedAssistedFactory
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(noteId) as T
    }

}

data class DetailState(
    val id:Long=0,
    val title: String="",
    val content:String="",
    val isBookMark:Boolean=false,
    val createdDate: Date =Date(),
    val isUpdatingNote:Boolean=false
)

@AssistedFactory
interface DetailedAssistedFactory{
    fun create(noteId: Long):DetailViewModel
}