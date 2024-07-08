package com.wagdev.tassyir.spending_feature.domain.usecases

import com.wagdev.tassyir.spending_feature.domain.model.Spend
import com.wagdev.tassyir.spending_feature.domain.repository.SpendRepository

class GetSpendByIdUseCase(
    private val repository: SpendRepository
) {
    operator suspend fun invoke(id: Int): Spend? {
        return repository.getSpendById(id)
    }
}