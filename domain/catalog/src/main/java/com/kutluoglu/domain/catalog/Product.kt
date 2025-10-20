package com.kutluoglu.domain.catalog

data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val brand: String,
    val category: String,
    val description: String,
    val thumbnail: String
)
