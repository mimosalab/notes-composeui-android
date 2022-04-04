package me.aravi.notes.data.interactors

import me.aravi.notes.beans.Notes
import me.aravi.notes.data.repositories.NotesRepo

class DeleteNotes(val repository: NotesRepo) {

    suspend operator fun invoke(easyNotes: Notes) {
        repository.deleteEasyNotes(easyNotes)
    }

}