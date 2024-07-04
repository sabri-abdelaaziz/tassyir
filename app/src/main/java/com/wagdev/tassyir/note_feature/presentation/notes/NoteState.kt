package com.wagdev.tassyir.note_feature.presentation.notes

import com.wagdev.tassyir.note_feature.domain.model.Note
import com.wagdev.tassyir.note_feature.domain.util.NoteOrder
import com.wagdev.tassyir.note_feature.domain.util.OrderType


data class NoteState(
    val notes:List<Note> = emptyList<Note>(),
    val noteOrder: NoteOrder =NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible:Boolean=true

)
