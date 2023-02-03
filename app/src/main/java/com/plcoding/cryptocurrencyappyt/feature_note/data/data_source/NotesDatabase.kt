package com.plcoding.cryptocurrencyappyt.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase() {
    abstract val noteDao: NotesDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}
