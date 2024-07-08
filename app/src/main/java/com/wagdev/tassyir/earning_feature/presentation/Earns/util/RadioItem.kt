package com.wagdev.tassyir.earning_feature.presentation.Earns.util

import com.wagdev.tassyir.earning_feature.presentation.Earns.EarnEvent

data class RadioItem(
    val name: String,
    val use:EarnEvent,
    val num:Int
)