package com.wagdev.tassyir.spending_feature.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.wagdev.tassyir.spending_feature.domain.model.Spend
import kotlinx.coroutines.flow.Flow

@Dao
interface SpendDao{
    @Query("SELECT * FROM spend")
    fun getSpends(): Flow<List<Spend>>
    @Query("SELECT * FROM spend WHERE month = :month")
    suspend fun getSpendByMonth(month: Int): List<Spend>
    @Query("SELECT * FROM spend WHERE year = :year")
    suspend fun getSpendByYear(year: Int): List<Spend>
    @Query("SELECT * FROM spend WHERE day = :day")
    suspend fun getSpendByDay(day: Int): List<Spend>
    @Query("SELECT * FROM spend WHERE id = :id")
    suspend fun getSpendById(id: Int): Spend?
    @Query("SELECT * FROM spend WHERE name = :name")
    suspend fun getSpendByNames(name: String): List<Spend>
    @Query("SELECT * FROM spend WHERE category = :category")
    suspend fun getSpendByCategory(category: String): List<Spend>
    @Query("SELECT * FROM spend WHERE type = :type")
    suspend fun getSpendByType(type: String): List<Spend>
    @Upsert
    suspend fun insertSpend(spend: Spend)
    @Delete
    suspend fun deleteSpend(spend: Spend)
   @Update
    suspend fun updateSpend(spend: Spend)

@Query("DELETE FROM spend WHERE id = :id")
suspend fun deleteSpendById(id: Int)


}