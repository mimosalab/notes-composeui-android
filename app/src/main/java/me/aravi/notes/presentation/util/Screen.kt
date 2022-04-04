package me.aravi.notes.presentation.util

sealed class Screen(val route: String) {
    object NotesScreen : Screen("notes_screen")
    object NoteEditorScreen : Screen("note_editor_screen")
}
