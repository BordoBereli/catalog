package com.kutluoglu.data.catalog.dto

import com.kutluoglu.data.catalog.local.ProductEntity
import com.kutluoglu.domain.catalog.Product
import kotlin.String

data class ProductDto(
	val id: Int,
	val title: String,
	val price: Double,
	val brand: String,
	val category: String,
	val description: String,
	val thumbnail: String
)

fun ProductDto.toProduct()= Product(
	id,
	title,
	price,
	brand,
	category,
	description,
	thumbnail
)

fun ProductDto.toProductEntity() = ProductEntity(
	id.toString(),
	title,
	price,
	brand,
	category,
	description,
	thumbnail
)