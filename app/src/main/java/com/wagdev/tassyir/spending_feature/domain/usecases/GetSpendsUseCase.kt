package com.wagdev.tassyir.spending_feature.domain.usecases

import com.wagdev.tassyir.spending_feature.domain.model.Spend
import com.wagdev.tassyir.spending_feature.domain.repository.SpendRepository
import kotlinx.coroutines.flow.Flow

class GetSpendsUseCase(
    private val repository:SpendRepository
) {
    suspend operator fun invoke(): Flow<List<Spend>> {
        return repository.getAllSpends()
    }
}