package com.plcoding.cryptocurrencyappyt.feature_note.domain.use_cases

import com.plcoding.cryptocurrencyappyt.feature_note.domain.model.Note
import com.plcoding.cryptocurrencyappyt.feature_note.domain.repository.INoteRepository
import com.plcoding.cryptocurrencyappyt.feature_note.domain.util.NoteOrder
import com.plcoding.cryptocurrencyappyt.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(private val noteRepository: INoteRepository) {

    operator fun invoke(noteOrder: NoteOrder): Flow<List<Note>> {
        return noteRepository.getNotes().map { notes ->
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> {
                            notes.sortedBy { note ->
                                note.title
                            }
                        }
                        is NoteOrder.Color -> {
                            notes.sortedBy { note ->
                                note.color
                            }
                        }
                        is NoteOrder.Date -> {
                            notes.sortedBy { note ->
                                note.timestamp
                            }
                        }
                    }
                }
                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> {
                            notes.sortedByDescending { note ->
                                note.title.lowercase()
                            }
                        }
                        is NoteOrder.Color -> {
                            notes.sortedByDescending { note ->
                                note.color
                            }
                        }
                        is NoteOrder.Date -> {
                            notes.sortedByDescending { note ->
                                note.timestamp
                            }
                        }
                    }
                }
            }
        }
    }
}
