package com.wagdev.tassyir.spending_feature.domain.usecases

import com.wagdev.tassyir.spending_feature.domain.model.Spend
import com.wagdev.tassyir.spending_feature.domain.repository.SpendRepository

class AddEditSpendUseCase(
    private val repository : SpendRepository
){
    suspend operator fun invoke(spend:Spend){
        if (spend.id == 0) {
            repository.insertSpend(spend)
        } else {
            repository.updateSpend(spend)
        }
    }
}