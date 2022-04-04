package me.aravi.notes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.aravi.notes.beans.Notes
import me.aravi.notes.data.interactors.NotesInteract
import me.aravi.notes.utils.NoteOrder
import me.aravi.notes.utils.OrderType
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val interactor: NotesInteract
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val stateEasy: State<NotesState> = _state

    private var recentlyDeletedNote: Notes? = null

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (stateEasy.value.noteOrder::class == event.noteOrder::class &&
                    stateEasy.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    interactor.deleteEasyNotes(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    interactor.addEasyNotes(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = stateEasy.value.copy(
                    isOrderSectionVisible = !stateEasy.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = interactor.getEasyNotes(noteOrder)
            .onEach { notes ->
                _state.value = stateEasy.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}