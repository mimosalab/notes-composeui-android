package me.aravi.notes.data.repositories

import kotlinx.coroutines.flow.Flow
import me.aravi.notes.beans.Notes
import me.aravi.notes.data.databases.NotesDao

class NotesRepoImpl(val dao: NotesDao) : NotesRepo {

    override fun getEasyNotes(): Flow<List<Notes>> {
        return dao.getEasyNotes()
    }

    override suspend fun getEasyNotesById(id: Int): Notes? {
        return dao.getEasyNotesById(id)
    }

    override suspend fun insertEasyNotes(note: Notes) {
        dao.insertEasyNotes(note)
    }

    override suspend fun deleteEasyNotes(note: Notes) {
        dao.deleteEasyNotes(note)
    }
}