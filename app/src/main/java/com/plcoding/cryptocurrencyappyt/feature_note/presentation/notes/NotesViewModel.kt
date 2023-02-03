package com.plcoding.cryptocurrencyappyt.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note
import com.plcoding.cryptocurrencyappyt.feature_note.domain.use_cases.NoteUseCases
import com.plcoding.cryptocurrencyappyt.feature_note.domain.util.NoteOrder
import com.plcoding.cryptocurrencyappyt.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {
    
    private val _noteState = mutableStateOf<NotesState>(NotesState())
    val noteState: State<NotesState> = _noteState

    private var recentlyDeletedNote: Note? = null

    private var notesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NoteEvent.OrderNotes -> {
                if (
                    noteState.value.noteOrder::class == event.noteOrder::class
                    && noteState.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }
                getNotes(event.noteOrder)
            }

            is NoteEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            is NoteEvent.ToggleOrderSection -> {
                _noteState.value =
                    noteState.value.copy(
                        isOrderSectionVisible = !noteState.value.isOrderSectionVisible
                    )
            }
        }
    }
    private fun getNotes(noteOrder: NoteOrder) {
        notesJob?.cancel()
        notesJob = noteUseCases.getNotes(noteOrder).onEach { notes ->
            _noteState.value = noteState.value.copy(
                notes = notes,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }
}
