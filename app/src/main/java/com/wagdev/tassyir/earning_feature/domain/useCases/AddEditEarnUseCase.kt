package com.wagdev.tassyir.earning_feature.domain.useCases

import com.wagdev.tassyir.earning_feature.domain.model.Earn
import com.wagdev.tassyir.earning_feature.domain.repository.EarnRepository

class AddEditEarnUseCase(
    private val repository : EarnRepository
) {
    suspend operator fun invoke(earn : Earn) {
        repository.insertEarn(earn)
    }

}