package com.plcoding.cryptocurrencyappyt.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.cryptocurrencyappyt.feature_note.domain.util.NoteOrder
import com.plcoding.cryptocurrencyappyt.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onChange: (noteOrder: NoteOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                selected = noteOrder is NoteOrder.Title,
                onSelected = { onChange(NoteOrder.Title(noteOrder.orderType)) },
                text = "Title"
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                selected = noteOrder is NoteOrder.Color,
                onSelected = { onChange(NoteOrder.Color(noteOrder.orderType)) },
                text = "Color"
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                selected = noteOrder is NoteOrder.Date,
                onSelected = { onChange(NoteOrder.Date(noteOrder.orderType)) },
                text = "Date"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                selected = noteOrder.orderType is OrderType.Ascending,
                onSelected = { onChange(noteOrder.copy(OrderType.Ascending)) },
                text = "Ascending"
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                selected = noteOrder.orderType is OrderType.Descending,
                onSelected = { onChange(noteOrder.copy(OrderType.Descending)) },
                text = "Descending"
            )
        }
    }
}
