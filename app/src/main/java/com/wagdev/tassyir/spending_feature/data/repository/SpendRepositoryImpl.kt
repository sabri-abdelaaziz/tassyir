package com.wagdev.tassyir.spending_feature.data.repository

import com.wagdev.tassyir.spending_feature.data.db.SpendDao
import com.wagdev.tassyir.spending_feature.domain.model.Spend
import com.wagdev.tassyir.spending_feature.domain.repository.SpendRepository
import kotlinx.coroutines.flow.Flow

class SpendRepositoryImpl (
    private val spendDao: SpendDao
): SpendRepository {
    override fun getAllSpends(): Flow<List<Spend>> {
       return spendDao.getSpends()
    }

    override suspend fun insertSpend(spend: Spend) {
        spendDao.insertSpend(spend)
    }

    override suspend fun deleteSpend(spend: Spend) {
        spendDao.deleteSpend(spend)
    }

    override suspend fun updateSpend(spend: Spend) {
        spendDao.updateSpend(spend)
    }

    override suspend fun getSpendById(id: Int): Spend? {
        return spendDao.getSpendById(id)
    }




}