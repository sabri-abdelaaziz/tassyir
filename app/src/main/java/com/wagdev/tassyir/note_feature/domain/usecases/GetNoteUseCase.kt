package com.wagdev.tassyir.note_feature.domain.usecases

import com.wagdev.tassyir.note_feature.domain.model.Note
import com.wagdev.tassyir.note_feature.domain.repository.NoteRepository


class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id:Int): Note?{
       return repository.getNoteById(id)
    }
}