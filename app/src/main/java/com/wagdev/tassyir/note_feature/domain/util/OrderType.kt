package com.wagdev.tassyir.note_feature.domain.util

sealed class OrderType {
    object Ascending:OrderType()
    object Descending:OrderType()
}