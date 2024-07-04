package com.wagdev.tassyir.earning_feature.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.wagdev.tassyir.earning_feature.domain.model.Earn
import kotlinx.coroutines.flow.Flow

@Dao
interface EarnDao {

    @Query("SELECT * FROM earn")
    fun getEarning(): Flow<List<Earn>>
    @Query("SELECT * FROM earn WHERE id = :id")
    suspend fun getEarnById(id: Int): Earn?
    @Query("SELECT * FROM earn WHERE name = :name")
    suspend fun getEarnByTitle(name: String): Earn?
    @Query("SELECT * FROM earn WHERE date = :date")
    suspend fun getEarnByDate(date: String): Earn?
    @Query("SELECT * FROM earn WHERE description = :description")
    suspend fun getEarnByDescription(description: String): Earn?
    @Query("SELECT * FROM earn WHERE amount = :amount")
    suspend fun getEarnByAmount(amount: Double): Earn?
    @Query("SELECT * FROM earn WHERE category = :category")
    suspend fun getEarnByCategory(category: String): Earn?
    @Query("SELECT * FROM earn WHERE name LIKE '%' || :keyword || '%' OR description LIKE '%' || :keyword || '%'")
    fun getEarnByKeyword(keyword: String): Flow<List<Earn>>
    @Upsert
    suspend fun insertEarn(earn: Earn)
    @Delete
    suspend fun deleteEarn(earn: Earn)

}