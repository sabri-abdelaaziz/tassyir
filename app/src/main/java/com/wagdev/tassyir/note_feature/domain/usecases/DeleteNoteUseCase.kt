package com.wagdev.tassyir.note_feature.domain.usecases

import com.wagdev.tassyir.note_feature.domain.model.Note
import com.wagdev.tassyir.note_feature.domain.repository.NoteRepository


class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}