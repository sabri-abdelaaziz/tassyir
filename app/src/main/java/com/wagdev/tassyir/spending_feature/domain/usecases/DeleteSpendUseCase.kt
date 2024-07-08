package com.wagdev.tassyir.spending_feature.domain.usecases

import com.wagdev.tassyir.spending_feature.domain.model.Spend
import com.wagdev.tassyir.spending_feature.domain.repository.SpendRepository

class DeleteSpendUseCase(
    private val repository: SpendRepository
) {
    suspend operator fun invoke(spend: Spend){
        repository.deleteSpend(spend)
    }
}