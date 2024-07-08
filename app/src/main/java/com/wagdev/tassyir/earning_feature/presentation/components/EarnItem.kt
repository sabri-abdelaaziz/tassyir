package com.wagdev.tassyir.earning_feature.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.wagdev.tassyir.earning_feature.domain.model.Earn

@Composable
fun EarnItem(
    modifier: Modifier = Modifier,
    earn: Earn,
    onEdit: (Earn) -> Unit,
    onDelete: (Earn) -> Unit,
    showEdit: (b:Boolean) -> Unit
) {

    Row(
        modifier = modifier.clickable {
            onEdit(earn)
                                      showEdit(true)},
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(earn.name,style= TextStyle(fontSize = 20.sp, color = Color.Black))
        Text(earn.amount.toString())
        IconButton(onClick = {
            onEdit(earn)
            showEdit(true)}) {
            Icon(Icons.Default.Edit,contentDescription = null)
        }
        IconButton(onClick = { onDelete(earn) }) {
            Icon(Icons.Default.Delete,contentDescription = null,tint = androidx.compose.ui.graphics.Color.Red)
        }

    }

}