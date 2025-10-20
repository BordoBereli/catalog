package com.kutluoglu.data.catalog.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
	@Query("SELECT * FROM products")
	suspend fun getAll(): List<ProductEntity>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun upsertAll(items: List<ProductEntity>)

	@Query("DELETE FROM products")
	suspend fun clear()
}