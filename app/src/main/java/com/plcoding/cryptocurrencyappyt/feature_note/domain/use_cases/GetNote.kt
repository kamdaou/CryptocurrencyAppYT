package com.plcoding.cryptocurrencyappyt.feature_note.domain.use_cases

import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note
import com.plcoding.cryptocurrencyappyt.feature_note.domain.repository.INoteRepository

class GetNote(private val repository: INoteRepository) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}
