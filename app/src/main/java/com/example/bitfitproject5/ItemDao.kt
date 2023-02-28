package com.example.bitfitproject5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM food_calorie_table")
    fun getAll(): Flow<List<ItemEntry>>

    @Insert
    fun insert(food : ItemEntry)

    @Query("DELETE FROM food_calorie_table")
    fun deleteAll()
}