package com.kutluoglu.data.catalog.remote

import com.kutluoglu.data.catalog.dto.Product
import com.kutluoglu.data.catalog.dto.ProductDto
import com.kutluoglu.data.catalog.dto.Products
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApi {
	@GET("products")
	suspend fun getProducts(): Products

	@GET("products/{id}")
	suspend fun getProductById(@Path("id") id: Int): Product
}