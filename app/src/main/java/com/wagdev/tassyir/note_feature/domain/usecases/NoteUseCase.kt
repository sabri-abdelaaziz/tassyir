package com.wagdev.tassyir.note_feature.domain.usecases


data class NoteUseCases (
    val getNotes :GetNotesUseCase,
    val deleteNote:DeleteNoteUseCase,
    val addNote:AddNoteUseCase,
    val getNote:GetNoteUseCase
    )