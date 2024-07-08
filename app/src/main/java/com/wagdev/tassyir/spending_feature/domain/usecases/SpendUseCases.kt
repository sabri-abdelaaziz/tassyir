package com.wagdev.tassyir.spending_feature.domain.usecases


data class SpendUseCase(
    val addEditeSpend: AddEditSpendUseCase,
    val deleteSpendUseCase:DeleteSpendUseCase,
    val getSpendByIdUseCase:GetSpendByIdUseCase,
    val getSpendsUseCase:GetSpendsUseCase
)