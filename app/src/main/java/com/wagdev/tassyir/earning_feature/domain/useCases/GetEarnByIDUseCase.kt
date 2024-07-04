package com.wagdev.tassyir.earning_feature.domain.useCases

import com.wagdev.tassyir.earning_feature.domain.model.Earn
import com.wagdev.tassyir.earning_feature.domain.repository.EarnRepository

class GetEarnByIDUseCase(
    private val repository:EarnRepository
) {
    operator suspend fun invoke(id: Int): Earn? = repository.getEarnById(id)
}