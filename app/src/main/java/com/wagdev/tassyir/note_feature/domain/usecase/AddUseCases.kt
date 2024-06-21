package com.wagdev.tassyir.note_feature.domain.usecase


import com.wagdev.tasiyyir.notes_feauture.domain.repository.Repository
import com.wagdev.tasiyyir.notes_feauture.data.local.model.Note
import javax.inject.Inject

class AddUseCases  @Inject  constructor(
    private val repository: Repository
){
    suspend operator fun invoke(note: Note)=repository.insert(note)
}