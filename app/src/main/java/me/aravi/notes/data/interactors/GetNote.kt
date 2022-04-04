package me.aravi.notes.data.interactors

import me.aravi.notes.beans.Notes
import me.aravi.notes.data.repositories.NotesRepo

class GetNote(val repository: NotesRepo) {

    suspend operator fun invoke(id: Int): Notes? {
        return repository.getEasyNotesById(id)
    }
}