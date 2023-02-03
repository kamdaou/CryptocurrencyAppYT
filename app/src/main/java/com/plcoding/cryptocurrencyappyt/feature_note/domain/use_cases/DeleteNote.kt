package com.plcoding.cryptocurrencyappyt.feature_note.domain.use_cases

import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note
import com.plcoding.cryptocurrencyappyt.feature_note.domain.repository.INoteRepository

class DeleteNote(val repository: INoteRepository) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}
