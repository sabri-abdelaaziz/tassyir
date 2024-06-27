package com.wagdev.tassyir.task_feature.domain.util

import com.wagdev.tassyir.note_feature.domain.util.OrderType

sealed class  TaskOrder(val orderType: TaskOrderType) {
    class Title(orderType: TaskOrderType):TaskOrder(orderType)
    class Color(orderType: TaskOrderType):TaskOrder(orderType)
    class Date(orderType: TaskOrderType):TaskOrder(orderType)

    fun copy(taskOrderType: TaskOrderType):TaskOrder {
        return when(this){
            is Title-> Title(orderType)
            is Date-> Date(orderType)
            is Color-> Color(orderType)
        }
    }

}