package com.kutluoglu.data.catalog.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kutluoglu.domain.catalog.Product

@Entity(tableName = "products")
data class ProductEntity(
	@PrimaryKey val id: String,
	val name: String,
	val priceCents: Double,
	val brand: String,
	val category: String,
	val description: String,
	val thumbnail: String
)

fun ProductEntity.toProduct() = Product(
    id = id.toInt(),
	title = name,
	price = priceCents,
    brand = brand,
    category = category,
    description = description,
    thumbnail = thumbnail
)