package com.wagdev.tassyir.spending_feature.domain.repository

import com.wagdev.tassyir.spending_feature.domain.model.Spend
import kotlinx.coroutines.flow.Flow

interface SpendRepository {
    fun getAllSpends(): Flow<List<Spend>>
    suspend fun insertSpend(spend: Spend)
    suspend fun deleteSpend(spend: Spend)

    suspend fun updateSpend(spend: Spend)
    suspend fun getSpendById(id: Int): Spend?
}