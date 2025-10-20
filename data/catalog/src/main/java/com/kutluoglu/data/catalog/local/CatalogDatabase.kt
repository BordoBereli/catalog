package com.kutluoglu.data.catalog.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
	entities = [ProductEntity::class],
	version = 3,
	exportSchema = false
)
abstract class CatalogDatabase : RoomDatabase() {
	abstract fun productDao(): ProductDao
}