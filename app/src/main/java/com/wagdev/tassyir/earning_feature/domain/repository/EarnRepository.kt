package com.wagdev.tassyir.earning_feature.domain.repository

import com.wagdev.tassyir.earning_feature.domain.model.Earn
import kotlinx.coroutines.flow.Flow

abstract class EarnRepository {

    abstract fun getEarnings(): Flow<List<Earn>>
    abstract suspend fun getEarnByKeyword(keyword: String): Flow<List<Earn>>
    abstract suspend fun getEarnById(id: Int): Earn?
    abstract suspend fun getEarnByTitle(title: String): Earn?
    abstract suspend fun getEarnByDescription(description: String): Earn?
    abstract suspend fun getEarnByAmount(amount: Double): Earn?
    abstract suspend fun getEarnByCategory(category: String): Earn?

    abstract suspend fun getEarn(id:Int): Earn?
    abstract suspend fun deleteEarn(Earn:Earn)
    abstract suspend fun insertEarn(Earn: Earn)

}