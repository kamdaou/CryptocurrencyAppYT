package com.plcoding.cryptocurrencyappyt.feature_note.data.data_source

import androidx.room.*
import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNote(id: Int): Note?
}
