package com.wagdev.tassyir.earning_feature.domain.useCases

data class EarnUseCases(
    val getEarnUseCase: GetEarnsUseCase,
    val deleteEarnUseCase: DeleteEarnUseCase,
    val insertEarnUseCase: AddEditEarnUseCase,
    val getEarnByIDUseCase: GetEarnByIDUseCase
)
