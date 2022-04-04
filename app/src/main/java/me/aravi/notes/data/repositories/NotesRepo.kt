package me.aravi.notes.data.repositories

import kotlinx.coroutines.flow.Flow
import me.aravi.notes.beans.Notes

interface NotesRepo {
    fun getEasyNotes(): Flow<List<Notes>>

    suspend fun getEasyNotesById(id: Int): Notes?

    suspend fun insertEasyNotes(note: Notes)

    suspend fun deleteEasyNotes(note: Notes)
}