package com.plcoding.cryptocurrencyappyt.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.plcoding.cryptocurrencyappyt.ui.theme.ColorPrimary
import com.plcoding.cryptocurrencyappyt.ui.theme.DarkGray
import com.plcoding.cryptocurrencyappyt.ui.theme.MediumGray

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey
    val id: Int? = null
) {
    companion object {
        val noteColors = listOf(DarkGray, MediumGray, ColorPrimary)
    }
}

class InvalidNoteException(message: String): Exception(message)
