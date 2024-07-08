package com.wagdev.tassyir.spending_feature.presentation.spends

import com.wagdev.tassyir.spending_feature.domain.model.Spend

sealed class SpendEvent {
    data class GetSpendById(val id :Int):SpendEvent()
    data class DeleteSpend(val spend: Spend):SpendEvent()
    data class AddEditSpend(val spend:Spend):SpendEvent()
    object ChangeDialogStatus:SpendEvent()
    object GetSpendList : SpendEvent()

}