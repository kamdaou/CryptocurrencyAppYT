package com.plcoding.cryptocurrencyappyt.feature_note.data.repository

import com.plcoding.cryptocurrencyappyt.feature_note.data.data_source.NotesDao
import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note
import com.plcoding.cryptocurrencyappyt.feature_note.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val dao: NotesDao): INoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNote(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}
