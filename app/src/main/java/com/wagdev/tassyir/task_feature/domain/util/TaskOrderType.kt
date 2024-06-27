package com.wagdev.tassyir.task_feature.domain.util

sealed class TaskOrderType {
    object Ascending:TaskOrderType()
    object Descending:TaskOrderType()
}