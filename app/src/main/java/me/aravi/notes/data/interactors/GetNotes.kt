package me.aravi.notes.data.interactors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.aravi.notes.beans.Notes
import me.aravi.notes.data.repositories.NotesRepo
import me.aravi.notes.utils.NoteOrder
import me.aravi.notes.utils.OrderType

class GetNotes(val repository: NotesRepo) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Notes>> {
        return repository.getEasyNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}