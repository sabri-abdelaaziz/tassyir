package com.wagdev.tassyir.earning_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wagdev.tassyir.earning_feature.presentation.Earns.util.RadioItem

@Composable
fun ClassificationComponent(
    modifier: Modifier = Modifier,
    itemOrder:List<RadioItem>,
    Selected:Int,
    onClick: (RadioItem) -> Unit
){
    Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically){
     itemOrder.forEachIndexed { index, s ->

         RadioButtonComponent(text = s.name, selected = index==Selected, onClick={onClick(s)})


     }
    }
}

