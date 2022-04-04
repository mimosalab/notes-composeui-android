package me.aravi.notes.presentation.editor

import androidx.compose.ui.focus.FocusState

sealed class NoteEditorState {
    data class EnteredTitle(val value: String) : NoteEditorState()
    data class ChangeTitleFocus(val focusState: FocusState) : NoteEditorState()
    data class EnteredContent(val value: String) : NoteEditorState()
    data class ChangeContentFocus(val focusState: FocusState) : NoteEditorState()
    data class ChangeColor(val color: Int) : NoteEditorState()
    object SaveNote : NoteEditorState()
}

