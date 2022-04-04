package me.aravi.notes.data.interactors

data class NotesInteract(
    val getEasyNotes: GetNotes,
    val deleteEasyNotes: DeleteNotes,
    val addEasyNotes: AddNotes,
    val getEasyNote: GetNote
)