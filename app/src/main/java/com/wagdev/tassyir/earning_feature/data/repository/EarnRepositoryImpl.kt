package com.wagdev.tassyir.earning_feature.data.repository

import com.wagdev.tassyir.earning_feature.data.local.EarnDao
import com.wagdev.tassyir.earning_feature.domain.model.Earn
import com.wagdev.tassyir.earning_feature.domain.repository.EarnRepository
import kotlinx.coroutines.flow.Flow

class EarnRepositoryImpl(
    private val earnDao: EarnDao
): EarnRepository() {
    override fun getEarnings(): Flow<List<Earn>> {
       return earnDao.getEarning()
    }

    override suspend fun getEarnById(id: Int): Earn? {
        return earnDao.getEarnById(id)
    }

    override suspend fun getEarnByTitle(title: String): Earn? {
        return earnDao.getEarnByTitle(title)
    }

    override suspend fun getEarnByDescription(description: String): Earn? {
        return earnDao.getEarnByDescription(description)
    }

    override suspend fun getEarnByAmount(amount: Double): Earn? {
        return earnDao.getEarnByAmount(amount)
    }

    override suspend fun getEarnByCategory(category: String): Earn? {
        return earnDao.getEarnByCategory(category)
    }

    override suspend fun getEarnByKeyword(keyword: String):Flow<List<Earn>> {
       return earnDao.getEarnByKeyword(keyword=keyword)
    }

    override suspend fun getEarn(id: Int): Earn? {
       return earnDao.getEarnById(id)
    }

    override suspend fun deleteEarn(Earn: Earn) {
        earnDao.deleteEarn(Earn)
    }

    override suspend fun insertEarn(Earn: Earn) {
        earnDao.insertEarn(Earn)
    }
}