package me.aravi.notes.data.interactors

import me.aravi.notes.beans.InvalidNoteException
import me.aravi.notes.beans.Notes
import me.aravi.notes.data.repositories.NotesRepo

class AddNotes(val repository: NotesRepo) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(easyNotes: Notes) {
        if (easyNotes.title.isEmpty()) {
            throw InvalidNoteException("Please enter Title!")
        }
        if (easyNotes.content.isEmpty()) {
            throw InvalidNoteException("Please enter Content!")
        }
        repository.insertEasyNotes(easyNotes)
    }

}