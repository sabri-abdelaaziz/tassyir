package com.wagdev.tassyir.note_feature.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wagdev.tassyir.note_feature.domain.model.Note
import com.wagdev.tassyir.note_feature.domain.usecases.NoteUseCases
import com.wagdev.tassyir.note_feature.domain.util.NoteOrder
import com.wagdev.tassyir.note_feature.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
   private val noteUseCases: NoteUseCases
): ViewModel(){
private val _state= mutableStateOf<NoteState>(NoteState())
   val state: State<NoteState> =_state
   private var recentlyDeleted: Note? =null
   private var getNotesJob: Job?=null

   init {
       getNotes(NoteOrder.Date(OrderType.Descending))
   }

   fun onEvent(event: NoteEvent){
      when(event){
         is NoteEvent.DeleteNote->{
            viewModelScope.launch {
               noteUseCases.deleteNote(event.note)
               recentlyDeleted=event.note
            }

         }
         is NoteEvent.Order ->{
           if(state.value.noteOrder::class==event.noteOrder::class && state.value.noteOrder.orderType==event.noteOrder.orderType){
              return
           }
            getNotes(event.noteOrder)
         }
         is NoteEvent.RestoreNote ->{
            viewModelScope.launch{
               noteUseCases.addNote(recentlyDeleted?:return@launch)
               recentlyDeleted=null
            }
         }
         is NoteEvent.ToggleOrderSection->{
         _state.value=state.value.copy(
            isOrderSectionVisible = !state.value.isOrderSectionVisible
         )
         }

         NoteEvent.RestoreNote -> TODO()
      }
   }

   fun getNotes(noteOrder: NoteOrder){
      getNotesJob?.cancel()
      getNotesJob=noteUseCases.getNotes(noteOrder).onEach {notes->
         _state.value=state.value.copy(
         notes =notes,noteOrder=noteOrder
         )

      }.launchIn(viewModelScope)
   }

}