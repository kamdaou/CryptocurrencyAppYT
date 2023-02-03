package com.plcoding.cryptocurrencyappyt.feature_note.presentation.notes

import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note
import com.plcoding.cryptocurrencyappyt.feature_note.domain.util.NoteOrder

sealed class NoteEvent {

    class DeleteNote(val note: Note): NoteEvent()

    object RestoreNote: NoteEvent()

    object ToggleOrderSection: NoteEvent()

    class OrderNotes(val noteOrder: NoteOrder): NoteEvent()
}
