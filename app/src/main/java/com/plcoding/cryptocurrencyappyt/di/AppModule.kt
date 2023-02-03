package com.plcoding.cryptocurrencyappyt.di

import android.app.Application
import androidx.room.Room
import com.plcoding.cryptocurrencyappyt.feature_note.data.data_source.NotesDatabase
import com.plcoding.cryptocurrencyappyt.feature_note.data.repository.NoteRepositoryImpl
import com.plcoding.cryptocurrencyappyt.feature_note.domain.repository.INoteRepository
import com.plcoding.cryptocurrencyappyt.feature_note.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideDatabase(application: Application): NotesDatabase {
        return Room.databaseBuilder(
            application,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        )
            .build()
    }

    @Provides
    fun provideRepository(database: NotesDatabase): INoteRepository {
        return NoteRepositoryImpl(database.noteDao)
    }

    @Provides
    fun provideNoteUseCases(repository: INoteRepository): NoteUseCases {
        return NoteUseCases(
            GetNotes(repository),
            DeleteNote(repository),
            AddNote(repository),
            GetNote(repository)
        )
    }
}
