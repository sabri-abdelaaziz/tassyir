package com.wagdev.tassyir.note_feature.domain.usecases

import com.wagdev.tassyir.note_feature.domain.model.InvalidNoteException
import com.wagdev.tassyir.note_feature.domain.model.Note
import com.wagdev.tassyir.note_feature.domain.repository.NoteRepository


class AddNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note){
        if(note.title.isBlank()){
            throw InvalidNoteException("the title of note cannot be empty!")
        }
        if(note.content.isBlank()){
            throw InvalidNoteException("the content of note cannot be empty!")
        }
        repository.insertNote(note)
    }
}