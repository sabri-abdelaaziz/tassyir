package com.wagdev.tassyir.earning_feature.domain.useCases

import com.wagdev.tassyir.earning_feature.domain.model.Earn
import com.wagdev.tassyir.earning_feature.domain.repository.EarnRepository
import kotlinx.coroutines.flow.Flow

class GetEarnsUseCase (
    private val repository: EarnRepository
){
    suspend operator fun invoke(): Flow<List<Earn>> {
        return repository.getEarnings()
    }

}