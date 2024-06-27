package com.wagdev.tassyir.task_feature.presentation.tasks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wagdev.tassyir.note_feature.domain.util.NoteOrder
import com.wagdev.tassyir.note_feature.domain.util.OrderType
import com.wagdev.tassyir.note_feature.presentation.notes.components.DefaultRadioButton
import com.wagdev.tassyir.task_feature.domain.util.TaskOrder
import com.wagdev.tassyir.task_feature.domain.util.TaskOrderType


@Composable
fun TaskOrderSection(
    taskOrder: TaskOrder = TaskOrder.Date(orderType = TaskOrderType.Descending),
    modifier: Modifier = Modifier,
    onOrderChange: (TaskOrder) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                checked = taskOrder is TaskOrder.Title,
                onSelect = { onOrderChange(TaskOrder.Title(taskOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Date",
                checked = taskOrder is TaskOrder.Date,
                onSelect = { onOrderChange(TaskOrder.Date(taskOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Color",
                checked = taskOrder is TaskOrder.Color,
                onSelect = { onOrderChange(TaskOrder.Color(taskOrder.orderType)) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                checked = taskOrder.orderType is TaskOrderType.Ascending,
                onSelect = { onOrderChange(taskOrder.copy(taskOrderType = TaskOrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(
                text = "Descending",
                checked = taskOrder.orderType is TaskOrderType.Descending,
                onSelect = { onOrderChange(taskOrder.copy(taskOrderType = TaskOrderType.Descending)) }
            )
        }
    }
}
