package com.wagdev.tassyir.spending_feature.presentation.spends


import com.wagdev.tassyir.spending_feature.domain.model.Spend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.time.LocalDate

data class SpendState(
    val spedList: Flow<List<Spend>> = emptyFlow(),
    val spend: Spend? =Spend(
        id = 0,
        name = "",
        category = "",
        type = "",
        amount = 0.0,
        day = LocalDate.now().dayOfMonth,
        month = LocalDate.now().monthValue,
     year=LocalDate.now().year
    ),
    var isLoading: Boolean = false

)