package com.wagdev.tassyir.note_feature.presentation.notes

import com.wagdev.tassyir.note_feature.domain.model.Note
import com.wagdev.tassyir.note_feature.domain.util.NoteOrder


sealed class NoteEvent {
    data class Order(val noteOrder: NoteOrder):NoteEvent()
    data class DeleteNote(val note: Note): NoteEvent()
    object RestoreNote:NoteEvent()
    object ToggleOrderSection :NoteEvent()
}