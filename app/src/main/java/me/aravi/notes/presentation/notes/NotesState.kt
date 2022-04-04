package me.aravi.notes.presentation.notes

import me.aravi.notes.beans.Notes
import me.aravi.notes.utils.NoteOrder
import me.aravi.notes.utils.OrderType

data class NotesState(
    val notes: List<Notes> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
