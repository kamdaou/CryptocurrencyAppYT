package com.plcoding.cryptocurrencyappyt.feature_note.domain.repository

import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {

    fun getNotes(): Flow<List<Note>>

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id: Int): Note?
}