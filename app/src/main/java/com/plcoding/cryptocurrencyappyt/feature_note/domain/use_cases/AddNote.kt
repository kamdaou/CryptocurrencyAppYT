package com.plcoding.cryptocurrencyappyt.feature_note.domain.use_cases

import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.InvalidNoteException
import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note
import com.plcoding.cryptocurrencyappyt.feature_note.domain.repository.INoteRepository

class AddNote(
    private val repository: INoteRepository
) {

    @kotlin.jvm.Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw(InvalidNoteException("The tittled of the note can't be blank"))
        }
        if (note.content.isBlank()) {
            throw(InvalidNoteException("The body of the note can't be blank"))
        }
        repository.insertNote(note)
    }
}