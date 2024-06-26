package com.wagdev.tassyir.note_feature.presentation.notes.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRadioButton(
    text: String,
    checked :Boolean,
    onSelect: () -> Unit,
    modifier: Modifier=Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 3.dp)
    ) {
        RadioButton(
            selected = checked,
            onClick = onSelect ,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.titleLarge
        )
    }
}