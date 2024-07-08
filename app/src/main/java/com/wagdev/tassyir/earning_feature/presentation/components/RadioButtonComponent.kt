package com.wagdev.tassyir.earning_feature.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RadioButtonComponent(
    text:String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(modifier =modifier) {
        Text(text ,style = MaterialTheme.typography.bodySmall)
        RadioButton(selected =selected , onClick)
    }


}